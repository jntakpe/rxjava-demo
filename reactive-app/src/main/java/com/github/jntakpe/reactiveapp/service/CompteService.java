package com.github.jntakpe.reactiveapp.service;

import com.github.jntakpe.reactiveapp.domain.Compte;
import com.github.jntakpe.reactiveapp.repository.CompteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Publication des méthodes permettant la gestion des {@link Compte}
 *
 * @author jntakpe
 */
@Service
public class CompteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompteService.class);

    private final CompteRepository compteRepository;

    @Autowired
    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    public BigDecimal soldeTotalCompteCourantByMandat(String mandat) {
        LOGGER.info("Calcul du solde des comptes courant pour le mandat {}", mandat);
        return compteRepository.findCompteCourantByLogin(mandat).stream()
                .map(Compte::getSolde)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal soldeTotalCompteEpargneByMandat(String mandat) {
        LOGGER.info("Calcul du solde des comptes épargne pour le mandat {}", mandat);
        return compteRepository.findCompteEpargneByLogin(mandat).stream()
                .map(Compte::getSolde)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
