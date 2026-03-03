package com.test.services;

import com.test.entities.Produit;
import com.test.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;

public class ProduitService extends AbstractFacade<Produit,Integer> {
    public ProduitService(){
        super(Produit.class);
    }

    @Override
    public boolean create(Produit entity) {

        entity = findByReference(entity.getReference());

        if(entity!=null){
            return false;
        }

        return super.create(entity);
    }

    public List<Produit> findByCategorie(Long categorieId){
        Session s = null;
        List<Produit> list = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            //list = s.createQuery("select p from Produit p where p.categorie.id = :id",Produit.class).setParameter("id", categorieId).getResultList();
            list = s.getNamedQuery("findByCategory").setParameter("categorie",categorieId).list();

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if (s!=null) s.close();
        }

        return list;
    }

    public Produit findByReference(String reference){
        Session s = null;
        Produit p = null;

        try {
            s = HibernateUtil.getSessionFactory().openSession();
            p = (Produit) s.getNamedQuery("Produit.findByReference").setParameter("reference",reference).uniqueResult();
        } catch (HibernateException e){
            e.printStackTrace();
        }finally {
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

//            list = s.createQuery(
//                            "select distinct p " +
//                                    "from Produit p " +
//                                    "join p.ligneCommandeProduits l " +
//                                    "join l.commande c " +
//                                    "where c.date between :d1 and :d2",
//                            Produit.class
//                    )
//                    .setParameter("d1", d1)
//                    .setParameter("d2", d2)
//                    .getResultList();

            list = s.getNamedQuery("findBetweenDates")
                    .setParameter("d1",d1)
                    .setParameter("d2",d2)
                    .list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (s != null) s.close();
        }

        return list;
    }
}
