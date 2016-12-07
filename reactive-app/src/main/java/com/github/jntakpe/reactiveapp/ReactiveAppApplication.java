package com.github.jntakpe.reactiveapp;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Classe principale de l'application
 *
 * @author jntakpe
 */
@EnableFeignClients
@SpringBootApplication
public class ReactiveAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveAppApplication.class, args);
    }

    @Bean
    public Logger.Level logger() {
        return Logger.Level.FULL;
    }
}
