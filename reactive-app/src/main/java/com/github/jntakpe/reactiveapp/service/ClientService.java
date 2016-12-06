package com.github.jntakpe.reactiveapp.service;

import com.github.jntakpe.reactiveapp.domain.Client;
import com.github.jntakpe.reactiveapp.exceptions.ClientNotFoundException;
import com.github.jntakpe.reactiveapp.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
        Client client;
        try {
            client = clientRepository.findByLogin(login);
        } catch (HttpClientErrorException e) {
            throw new ClientNotFoundException(String.format("Impossible de trouver le client %s", login));
        }
        List<CompletableFuture<BigDecimal>> fs = new ArrayList<>();
        client.getMandats().forEach(m -> {
            fs.add(CompletableFuture.supplyAsync(() -> compteService.soldeTotalCompteCourantByMandat(m)));
            fs.add(CompletableFuture.supplyAsync(() -> compteService.soldeTotalCompteEpargneByMandat(m)));
        });
        return CompletableFuture.allOf(fs.toArray(new CompletableFuture[fs.size()]))
                .thenApply(f -> fs.stream().map(CompletableFuture::join).collect(Collectors.toList()))
                .thenApply(soldes -> soldes.stream().reduce(BigDecimal.ZERO, BigDecimal::add))
                .get();
    }

}
