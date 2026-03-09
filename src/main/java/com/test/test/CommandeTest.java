package com.test.test;

import com.test.entities.Commande;
import com.test.entities.LigneCommandeProduit;
import com.test.entities.Produit;
import com.test.services.CommandeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandeTest {

    private static final CommandeService cs = new CommandeService();

    public static void run() {
//         testCreate();
//         testUpdate();
//         testFindAll();
//         testDelete();
        testAfficherProduitsCommande();
    }

    public static void testCreate() {
        System.out.println("\n=== TEST CREATE COMMANDE ===");

        Produit p1 = new Produit();
        p1.setId(1);


        LigneCommandeProduit l1 = new LigneCommandeProduit();
        l1.setProduit(p1);
        l1.setQuantite(2);

        List<LigneCommandeProduit> lignes = new ArrayList<>();
        lignes.add(l1);


        Commande commande = new Commande();
        commande.setDate(new Date());
        commande.setLigneCommandeProduits(lignes);

        boolean created = cs.create(commande);

        System.out.println("Create status : " + created);
        System.out.println("Commande id : " + commande.getId());
    }

    public static void testUpdate() {
        System.out.println("\n=== TEST UPDATE COMMANDE ===");

        Commande commande = cs.findById(3);

        if (commande == null) {
            System.out.println("Commande introuvable.");
            return;
        }

        Produit p1 = new Produit();
        p1.setId(1);

        LigneCommandeProduit l1 = new LigneCommandeProduit();
        l1.setProduit(p1);
        l1.setQuantite(10);

        List<LigneCommandeProduit> lignes = new ArrayList<>();
        lignes.add(l1);

        commande.setDate(new Date());
        commande.setLigneCommandeProduits(lignes);

        boolean updated = cs.update(commande);

        System.out.println("Update status : " + updated);
    }

    public static void testFindAll() {
        System.out.println("\n=== TEST FIND ALL COMMANDES ===");

        List<Commande> commandes = cs.findAll();

        if (commandes != null && !commandes.isEmpty()) {
            for (Commande c : commandes) {
                System.out.println(c);
            }
        } else {
            System.out.println("Aucune commande trouvée.");
        }
    }

    public static void testDelete() {
        System.out.println("\n=== TEST DELETE COMMANDE ===");

        Commande commande = cs.findById(3);

        if (commande != null) {
            boolean deleted = cs.delete(commande);
            System.out.println("Delete status : " + deleted);
        } else {
            System.out.println("Commande introuvable.");
        }
    }

    public static void testAfficherProduitsCommande() {

        System.out.println("\n=== TEST PRODUITS D'UNE COMMANDE ===");

        cs.afficherProduitsCommande(6);
    }
}