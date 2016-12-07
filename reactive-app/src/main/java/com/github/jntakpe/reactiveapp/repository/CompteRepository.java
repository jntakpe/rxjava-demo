package com.github.jntakpe.reactiveapp.repository;

import com.github.jntakpe.reactiveapp.domain.Compte;
import com.github.jntakpe.reactiveapp.domain.CompteCourant;
import com.github.jntakpe.reactiveapp.domain.CompteEpargne;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rx.Observable;

import java.util.Set;

/**
 * Repository permettant d'accéder aux informations des {@link Compte}
 *
 * @author jntakpe
 */
@FeignClient(name = "compte-repository", url = "${mock-server.url}")
public interface CompteRepository {

    @RequestMapping(value = "/comptecourant/{login}", method = RequestMethod.GET)
    Observable<Set<CompteCourant>> findCompteCourantByLogin(@PathVariable("login") String login);

    @RequestMapping(value = "/compteepargne/{login}", method = RequestMethod.GET)
    Observable<Set<CompteEpargne>> findCompteEpargneByLogin(@PathVariable("login") String login);
}
