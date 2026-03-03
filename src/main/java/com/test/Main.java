package com.test;

import com.test.util.HibernateUtil;
import org.hibernate.Session;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {

            Object r = session.createNativeQuery("SELECT 1").getSingleResult();
            System.out.println("Connexion OK! " +r);
        }catch (Exception e){
            System.out.println("Erreur de connexion "+e.getMessage());
            e.printStackTrace();
        }finally {
            HibernateUtil.shutdown();
        }
    }
}