package com.test.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CommandeProduitPk implements Serializable {

    private int commande;
    private int produit;

    public CommandeProduitPk() {}

    public CommandeProduitPk(int commande, int produit) {
        this.commande = commande;
        this.produit = produit;
    }

    public int getCommandeId() {
        return commande;
    }

    public void setCommandeId(int commandeId) {
        this.commande = commandeId;
    }

    public int getProduitId() {
        return produit;
    }

    public void setProduitId(int produitId) {
        this.produit = produitId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommandeProduitPk that = (CommandeProduitPk) o;
        return commande == that.commande && produit == that.produit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commande, produit);
    }
}
