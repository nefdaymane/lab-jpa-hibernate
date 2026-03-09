package com.test.services;

import com.test.entities.Commande;
import com.test.entities.CommandeProduitPk;
import com.test.entities.LigneCommandeProduit;

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

        if (entity.getLigneCommandeProduits() != null) {
            for (LigneCommandeProduit ligne : entity.getLigneCommandeProduits()) {
                if (!lcs.delete(ligne)) {
                    return false;
                }
            }
        }

        return super.delete(entity);
    }
}