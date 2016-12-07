package com.github.jntakpe.reactiveapp.service;

import com.github.jntakpe.reactiveapp.domain.Client;
import com.github.jntakpe.reactiveapp.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * Publication des méthodes permettant la gestion des {@link com.github.jntakpe.reactiveapp.domain.Client}
 *
 * @author jntakpe
 */
@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    private final CompteService compteService;

    @Autowired
    public ClientService(ClientRepository clientRepository, CompteService compteService) {
        this.clientRepository = clientRepository;
        this.compteService = compteService;
    }

    /**
     * Calcule le solde total d'un client et de ces mandataires.
     * Dans un premier temps récupère les mandataires.
     * Puis pour chaque mandataire récupère le solde des comptes courants et épargnes.
     *
     * @param login login du client
     * @return solde total
     */
    public BigDecimal soldeTotalByLogin(String login) throws ExecutionException, InterruptedException {
        LOGGER.info("Calcul du solde total du client {}", login);
        return clientRepository.findByLogin(login)
                .map(Client::getMandats)
                .flatMap(Observable::from)
                .flatMap(m -> Observable.merge(
                        compteService.soldeTotalCompteEpargneByMandat(m),
                        compteService.soldeTotalCompteCourantByMandat(m)
                ))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .toBlocking()
                .single();
    }

}
