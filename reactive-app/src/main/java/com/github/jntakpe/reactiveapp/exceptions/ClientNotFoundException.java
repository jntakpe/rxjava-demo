package com.github.jntakpe.reactiveapp.exceptions;

/**
 * Exception indiquant que le client demandé n'existe pas
 *
 * @author jntakpe
 */
public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(String message) {
        super(message);
    }

}
