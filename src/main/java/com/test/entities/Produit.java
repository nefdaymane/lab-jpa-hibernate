    package com.test.entities;

    import javax.persistence.*;
    import java.util.List;

    @Entity
    @Table(name = "produits")
    @NamedQueries({
            @NamedQuery(
                    name = "Produit.findByReference",
                    query = "from Produit p where p.reference = :reference"
            ),
            @NamedQuery(
                    name = "findByCategory", query = "SELECT p from Produit p where p.categorie = :categorie"
            ),
            @NamedQuery(
                    name = "findBetweenDates",
                    query = "select distinct p " +
                            "from Produit p " +
                            "join p.ligneCommandeProduits l " +
                            "join l.commande c " +
                            "where c.date between :d1 and :d2"
            )
    })
    public class Produit {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;


        private String reference;
        private float prix;

        @ManyToOne
        private Categorie categorie;

        @OneToMany(mappedBy = "produit")
        private List<LigneCommandeProduit>  ligneCommandeProduits;

        public Produit() {}

        public Produit(String reference, float prix, Categorie categorie) {
            this.reference = reference;
            this.prix = prix;
            this.categorie = categorie;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public float getPrix() {
            return prix;
        }

        public void setPrix(float prix) {
            this.prix = prix;
        }

        public Categorie getCategorie() {
            return categorie;
        }

        public void setCategorie(Categorie categorie) {
            this.categorie = categorie;
        }

        @Override
        public String toString() {
            return "Produit{" +
                    "id=" + id +
                    ", reference='" + reference + '\'' +
                    ", prix=" + prix +
                    '}';
        }
    }
