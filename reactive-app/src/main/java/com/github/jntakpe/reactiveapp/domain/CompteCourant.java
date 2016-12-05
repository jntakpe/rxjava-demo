package com.github.jntakpe.reactiveapp.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Bean représentant un dépôt à vue (compte courant)
 *
 * @author jntakpe
 */
public class CompteCourant extends Compte {

    private BigDecimal encoursCB;

    private LocalDate dateEncours;

    public BigDecimal getEncoursCB() {
        return encoursCB;
    }

    public void setEncoursCB(BigDecimal encoursCB) {
        this.encoursCB = encoursCB;
    }

    public LocalDate getDateEncours() {
        return dateEncours;
    }

    public void setDateEncours(LocalDate dateEncours) {
        this.dateEncours = dateEncours;
    }

}
