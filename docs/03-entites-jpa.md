# Comprendre les Entités JPA

Les entités JPA sont des classes Java simples (POJO) qui mappent directement vers des tables de base de données.

## 1. Principales Entités du Projet

1. **`Categorie`** :
   - Contient `id` (Clé Primaire) et `libelle`.
   - Relation 1-N avec `Produit` (Une catégorie contient plusieurs produits).

2. **`Produit`** :
   - Contient `id`, `libelle`, `poids`, `prix`, `stock`.
   - Relation N-1 avec `Categorie`.

3. **`Commande`** :
   - Contient `id`, `date`.
   - Représente une commande client globale.

4. **`LigneCommandeProduit`** :
   - Entité de jonction entre `Commande` et `Produit`.
   - Contient une clé composée (`@EmbeddedId`).
   - Contient des informations métier supplémentaires comme la `quantite` commandée.

## 2. Annotations JPA importantes
- `@Entity` : Marque la classe comme une entité persistante.
- `@Table` : Définit le nom de la table SQL associée.
- `@Id` : Marque le champ comme clé primaire.
- `@GeneratedValue` : Permet l'auto-incrémentation du champ ID (via `GenerationType.IDENTITY`).
- `@ManyToOne` / `@OneToMany` : Définit les relations entre les entités.
- `@NamedQuery` : Permet de définir des requêtes JPQL réutilisables.

Exemple :
```java
@Entity
@Table(name = "produits")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    ...
}
```
