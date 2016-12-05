package com.github.jntakpe.reactiveapp.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Bean représentant un compte
 *
 * @author jntakpe
 */
public abstract class Compte {

    private String iban;

    private Devise devise;

    private BigDecimal solde;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Devise getDevise() {
        return devise;
    }

    public void setDevise(Devise devise) {
        this.devise = devise;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Compte)) {
            return false;
        }
        Compte compte = (Compte) o;
        return Objects.equals(iban, compte.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("iban", iban)
                .append("devise", devise)
                .append("solde", solde)
                .toString();
    }
}
