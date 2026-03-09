package com.test.services;

import com.test.entities.CommandeProduitPk;
import com.test.entities.LigneCommandeProduit;
import com.test.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

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

    public List<LigneCommandeProduit> findByCommande(int commandeId) {

        Session s = null;
        List<LigneCommandeProduit> list = null;

        try {

            s = HibernateUtil.getSessionFactory().openSession();

            list = s.getNamedQuery("LigneCommandeProduit.findByCommande")
                    .setParameter("id", commandeId)
                    .list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (s != null) s.close();
        }

        return list;
    }
}
