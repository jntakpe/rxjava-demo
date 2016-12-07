package com.github.jntakpe.reactiveapp.repository;

import com.github.jntakpe.reactiveapp.domain.Client;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Repository permettant d'accéder aux informations des {@link Client}
 *
 * @author jntakpe
 */
@FeignClient(name = "client-repository", url = "${mock-server.url}")
public interface ClientRepository {

    @RequestMapping(value = "/clients/{login}", method = RequestMethod.GET)
    Client findByLogin(@PathVariable("login") String login);

}
