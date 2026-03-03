package com.test.entities;

import javax.persistence.*;

@Entity
@Table(name = "lignecommandeproduits")
public class LigneCommandeProduit {

    @EmbeddedId
    private CommandeProduitPk commandeProduitPk;

    private int quantite;


    @ManyToOne
    @JoinColumn(name = "produit", insertable = false, updatable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "commande", insertable = false, updatable = false)
    private Commande commande;

    public LigneCommandeProduit() {}

    public LigneCommandeProduit(int quantite, Produit produit, Commande commande) {
        this.quantite = quantite;
        this.produit = produit;
        this.commande = commande;
        commandeProduitPk = new CommandeProduitPk(commande.getId(), produit.getId());
    }

    public CommandeProduitPk getCommandeProduitPk() {
        return commandeProduitPk;
    }

    public void setCommandeProduitPk(CommandeProduitPk commandeProduitPk) {
        this.commandeProduitPk = commandeProduitPk;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }


}
