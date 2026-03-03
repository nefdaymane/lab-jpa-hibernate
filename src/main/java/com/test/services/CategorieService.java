package com.test.services;

import com.test.entities.Categorie;
import com.test.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class CategorieService extends AbstractFacade<Categorie,Integer>{

    public CategorieService() {
        super(Categorie.class);
    }

    @Override
    public boolean create(Categorie entity) {

        entity = findByCode(entity.getCode());

        if(entity != null){
            return false;
        }

        return super.create(entity);
    }

    public Categorie findByCode(String code){
        Session session = null;
        Categorie categorie = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            categorie = session.createQuery("from Categorie c where c.code = :code",Categorie.class)
                    .setParameter("code",code).uniqueResult();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }

        return categorie;
    }
}
