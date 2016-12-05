package com.github.jntakpe.reactiveapp.repository;

import com.github.jntakpe.reactiveapp.domain.Compte;
import com.github.jntakpe.reactiveapp.domain.CompteCourant;
import com.github.jntakpe.reactiveapp.domain.CompteEpargne;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * Repository permettant d'accéder aux informations des {@link Compte}
 *
 * @author jntakpe
 */
@Repository
public class CompteRepository {

    @Value("${mock-server.url}")
    private String mockServerUrl;

    public Set<CompteCourant> findCompteCourantByLogin(String login) {
        return new RestTemplate().exchange(mockServerUrl + "/comptecourant/" + login, HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<CompteCourant>>() {
                })
                .getBody();
    }

    public Set<CompteEpargne> findCompteEpargneByLogin(String login) {
        return new RestTemplate().exchange(mockServerUrl + "/compteepargne/" + login, HttpMethod.GET, null,
                new ParameterizedTypeReference<Set<CompteEpargne>>() {
                })
                .getBody();
    }
}
