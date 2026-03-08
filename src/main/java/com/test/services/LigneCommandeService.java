package com.test.services;

import com.test.entities.CommandeProduitPk;
import com.test.entities.LigneCommandeProduit;

public class LigneCommandeService extends AbstractFacade<LigneCommandeProduit, CommandeProduitPk> {

    public LigneCommandeService() {
        super(LigneCommandeProduit.class);
    }

    @Override
    public boolean create(LigneCommandeProduit entity) {

        if (entity.getProduit() == null || entity.getCommande() == null) {
            return false;
        }

        return super.create(entity);
    }
}
