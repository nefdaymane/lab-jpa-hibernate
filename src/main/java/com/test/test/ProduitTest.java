package com.test.test;

import com.test.entities.Categorie;
import com.test.entities.Produit;
import com.test.services.ProduitService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProduitTest {

    private static final ProduitService ps = new ProduitService();

    public static void run() {
//         testCreate();
        // testFindByReference();
        // testFindByCategorie();
        // testUpdate();
        // testFindAll();
        // testDelete();
//         testFindProduitsCommandesBetweenDates();
        testPrixSuperieur();
    }

    public static void testCreate() {
        System.out.println("\n=== TEST CREATE PRODUIT ===");

        Categorie categorie = new Categorie();
        categorie.setId(1);

        Produit produit = new Produit("REF001", 1500.0f, categorie);

        boolean created = ps.create(produit);
        System.out.println("Create status : " + created);

        Produit found = ps.findByReference("REF001");
        System.out.println("Produit trouvé : " + found);
    }

    public static void testFindByReference() {
        System.out.println("\n=== TEST FIND BY REFERENCE ===");

        Produit produit = ps.findByReference("REF001");

        if (produit != null) {
            System.out.println("Produit trouvé : " + produit);
        } else {
            System.out.println("Aucun produit trouvé.");
        }
    }

    public static void testFindByCategorie() {
        System.out.println("\n=== TEST FIND BY CATEGORIE ===");

        List<Produit> produits = ps.findByCategorie(1);

        if (produits != null && !produits.isEmpty()) {
            for (Produit p : produits) {
                System.out.println(p);
            }
        } else {
            System.out.println("Aucun produit trouvé pour cette catégorie.");
        }
    }

    public static void testUpdate() {
        System.out.println("\n=== TEST UPDATE PRODUIT ===");

        Produit produit = ps.findByReference("REF001");
        System.out.println("Avant update : " + produit);

        if (produit != null) {
            produit.setReference("REF001_UPDATED");
            produit.setPrix(2000.0f);

            boolean updated = ps.update(produit);
            System.out.println("Update status : " + updated);

            Produit updatedProduit = ps.findByReference("REF001_UPDATED");
            System.out.println("Après update : " + updatedProduit);
        } else {
            System.out.println("Produit introuvable.");
        }
    }

    public static void testFindAll() {
        System.out.println("\n=== TEST FIND ALL PRODUITS ===");

        List<Produit> produits = ps.findAll();

        if (produits != null && !produits.isEmpty()) {
            for (Produit p : produits) {
                System.out.println(p);
            }
        } else {
            System.out.println("Aucun produit trouvé.");
        }
    }

    public static void testDelete() {
        System.out.println("\n=== TEST DELETE PRODUIT ===");

        Produit produit = ps.findByReference("REF001_UPDATED");

        if (produit != null) {
            boolean deleted = ps.delete(produit);
            System.out.println("Delete status : " + deleted);
        } else {
            System.out.println("Produit introuvable pour suppression.");
        }
    }

    public static void testFindProduitsCommandesBetweenDates() {
        System.out.println("\n=== TEST FIND PRODUITS COMMANDES BETWEEN DATES ===");

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date d1 = sdf.parse("2024-01-01");
            Date d2 = sdf.parse("2026-12-31");

            System.out.println("Date début : " + d1);
            System.out.println("Date fin   : " + d2);

            List<Produit> produits = ps.findProduitsCommandesBetweenDates(d1, d2);

            if (produits == null || produits.isEmpty()) {
                System.out.println("Aucun produit trouvé entre ces dates.");
                return;
            }

            System.out.println("Produits trouvés :");

            for (Produit p : produits) {
                System.out.println(p);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void testPrixSuperieur() {

        System.out.println("\n=== TEST PRODUITS PRIX > 100 ===");

        List<Produit> produits = ps.findPrixSuperieur(100);

        for (Produit p : produits) {
            System.out.println(p);
        }
    }
}