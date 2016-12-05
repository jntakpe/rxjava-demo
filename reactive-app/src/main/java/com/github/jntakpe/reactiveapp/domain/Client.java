package com.github.jntakpe.reactiveapp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Bean représentant un client de la banque
 *
 * @author jntakpe
 */
public class Client {

    private String login;

    private String nom;

    private String prenom;

    private Set<String> mandats = new HashSet<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Set<String> getMandats() {
        return mandats;
    }

    public void setMandats(Set<String> mandats) {
        this.mandats = mandats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        Client client = (Client) o;
        return Objects.equals(login, client.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("login", login)
                .append("nom", nom)
                .append("prenom", prenom)
                .append("mandats", mandats)
                .toString();
    }
}
