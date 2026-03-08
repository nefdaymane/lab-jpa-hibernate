package com.test.test;

import com.test.entities.Categorie;
import com.test.services.CategorieService;

import java.util.List;

public class CategorieTest {

    private static final CategorieService cs = new CategorieService();

    public static void run() {
        testCreate();
//        testFindByCode();
//        testUpdate();
//        testFindAll();
    }

    public static void testCreate() {
        System.out.println("\n=== TEST CREATE ===");

        Categorie categorie = new Categorie("EGJDKG2", "INFO");

        boolean created = cs.create(categorie);
        System.out.println("Create status : " + created);
    }

    public static void testFindByCode() {
        System.out.println("\n=== TEST FIND BY CODE ===");

        Categorie categorie = cs.findByCode("EGJDKG1");

        if (categorie != null) {
            System.out.println(categorie);
        } else {
            System.out.println("Categorie introuvable");
        }
    }

    public static void testUpdate() {
        System.out.println("\n=== TEST UPDATE ===");

        Categorie categorie = cs.findByCode("EGJDKG1");

        if (categorie != null) {
            categorie.setCode("CAT12");

            boolean updated = cs.update(categorie);
            System.out.println("Update status : " + updated);
        }
    }

    public static void testFindAll() {
        System.out.println("\n=== TEST FIND ALL ===");

        List<Categorie> list = cs.findAll();

        for (Categorie c : list) {
            System.out.println(c);
        }
    }
}