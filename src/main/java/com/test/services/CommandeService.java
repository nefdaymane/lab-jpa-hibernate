package com.test.services;

import com.test.entities.Commande;
import com.test.entities.CommandeProduitPk;
import com.test.entities.LigneCommandeProduit;
import com.test.entities.Produit;

import java.util.List;

public class CommandeService extends AbstractFacade<Commande, Integer> {

    private final LigneCommandeService lcs = new LigneCommandeService();

    public CommandeService() {
        super(Commande.class);
    }

    @Override
    public boolean create(Commande entity) {

        boolean created = super.create(entity);

        if (!created) {
            return false;
        }

        if (entity.getLigneCommandeProduits() != null) {
            for (LigneCommandeProduit ligne : entity.getLigneCommandeProduits()) {
                ligne.setCommande(entity);

                if (ligne.getProduit() != null) {
                    ligne.setCommandeProduitPk(
                            new CommandeProduitPk(entity.getId(), ligne.getProduit().getId())
                    );
                }

                if (!lcs.create(ligne)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean update(Commande entity) {

        Commande existingCommande = findById(entity.getId());

        if (existingCommande == null) {
            return false;
        }

        if (existingCommande.getLigneCommandeProduits() != null) {
            for (LigneCommandeProduit ligne : existingCommande.getLigneCommandeProduits()) {
                if (!lcs.delete(ligne)) {
                    return false;
                }
            }
        }

        boolean updated = super.update(entity);

        if (!updated) {
            return false;
        }

        if (entity.getLigneCommandeProduits() != null) {
            for (LigneCommandeProduit ligne : entity.getLigneCommandeProduits()) {
                ligne.setCommande(entity);

                if (ligne.getProduit() != null) {
                    ligne.setCommandeProduitPk(
                            new CommandeProduitPk(entity.getId(), ligne.getProduit().getId())
                    );
                }

                if (!lcs.create(ligne)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public boolean delete(Commande entity) {

        Commande existingCommande = findById(entity.getId());

        if (existingCommande == null) {
            return false;
        }

        if (existingCommande.getLigneCommandeProduits() != null) {
            for (LigneCommandeProduit ligne : existingCommande.getLigneCommandeProduits()) {
                if (!lcs.delete(ligne)) {
                    return false;
                }
            }
        }

        Commande commandeToDelete = new Commande();
        commandeToDelete.setId(existingCommande.getId());

        return super.delete(commandeToDelete);
    }

    public void afficherProduitsCommande(int commandeId) {

        Commande commande = findById(commandeId);

        if (commande == null) {
            System.out.println("Commande introuvable");
            return;
        }

        List<LigneCommandeProduit> lignes = lcs.findByCommande(commandeId);

        System.out.println("Commande : " + commande.getId() +
                "    Date : " + commande.getDate());

        System.out.println("Liste des produits :");
        System.out.println("Reference   Prix   Quantite");

        for (LigneCommandeProduit l : lignes) {

            Produit p = l.getProduit();

            System.out.println(
                    p.getReference() + "      " +
                            p.getPrix() + " DH      " +
                            l.getQuantite()
            );
        }
    }

}