package com.github.jntakpe.reactiveapp.repository;

import com.github.jntakpe.reactiveapp.domain.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Repository permettant d'accéder aux informations des {@link Client}
 *
 * @author jntakpe
 */
@Repository
public class ClientRepository {

    @Value("${mock-server.url}")
    private String mockServerUrl;

    public Client findByLogin(String login) {
        return new RestTemplate().getForObject(mockServerUrl + "/clients/" + login, Client.class);
    }

}
