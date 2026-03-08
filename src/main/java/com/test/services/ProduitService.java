package com.test.services;

import com.test.entities.Categorie;
import com.test.entities.Produit;
import com.test.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class ProduitService extends AbstractFacade<Produit, Integer> {

    private final CategorieService cs = new CategorieService();

    public ProduitService() {
        super(Produit.class);
    }

    @Override
    public boolean create(Produit entity) {

        Produit existingProduit = findByReference(entity.getReference());

        if (existingProduit != null) {
            return false;
        }

        if (entity.getCategorie() == null) {
            return false;
        }

        Categorie categorie = cs.findById(entity.getCategorie().getId());

        if (categorie == null) {
            return false;
        }

        return super.create(entity);
    }

    public List<Produit> findByCategorie(int categorieId) {
        Session s = null;
        List<Produit> list = null;

        try {
            s = HibernateUtil.getSessionFactory().openSession();

            list = s.getNamedQuery("Produit.findByCategory")
                    .setParameter("id", categorieId)
                    .list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (s != null) s.close();
        }

        return list;
    }

    public Produit findByReference(String reference) {
        Session s = null;
        Produit p = null;

        try {
            s = HibernateUtil.getSessionFactory().openSession();
            p = (Produit) s.getNamedQuery("Produit.findByReference")
                    .setParameter("reference", reference)
                    .uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }

        return p;
    }

    public List<Produit> findProduitsCommandesBetweenDates(Date d1, Date d2) {
        Session s = null;
        List<Produit> list = null;

        try {
            s = HibernateUtil.getSessionFactory().openSession();

            list = s.getNamedQuery("Produit.findBetweenDates")
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (s != null) s.close();
        }

        return list;
    }
}