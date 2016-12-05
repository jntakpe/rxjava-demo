package com.github.jntakpe.reactiveapp.service;

import com.github.jntakpe.reactiveapp.repository.ClientRepository;
import com.github.jntakpe.reactiveapp.repository.CompteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Publication des méthodes permettant la gestion des {@link com.github.jntakpe.reactiveapp.domain.Client}
 *
 * @author jntakpe
 */
@Service
public class ClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    private final CompteRepository compteRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, CompteRepository compteRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
    }

    /**
     * Calcule le solde total d'un client et de ces mandataires.
     * Dans un premier temps récupère les mandataires.
     * Puis pour chaque mandataire récupère le solde des comptes courants et épargnes.
     *
     * @param login login du client
     * @return solde total
     */
    public BigDecimal soldeTotalByLogin(String login) {
        LOGGER.info("Calcul du solde total du client {}", login);
        // TODO to implement
        return null;
    }

}
