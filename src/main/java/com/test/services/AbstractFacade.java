package com.test.services;

import com.test.dao.IDao;
import com.test.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractFacade<T,ID extends Serializable> implements IDao<T,ID> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(T entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return true;
        }catch (HibernateException e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(T entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        }catch (HibernateException e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public boolean delete(T entity) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            return true;
        }catch (HibernateException e){
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public T findById(ID id) {
        Session session = null;
//        Transaction transaction = null;
        T obj = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
            obj = session.get(entityClass, id);
//            transaction.commit();
        }catch (HibernateException e){
//            if(transaction!=null){
//                transaction.rollback();
//            }
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public List<T> findAll() {
        Session session = null;
        List<T> obj = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            obj = session.createQuery("FROM "+entityClass.getSimpleName()).list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if (session != null) {
                session.close();
            }
        }
        return obj;
    }
}
