# Clés Composées en JPA

Une clé composée est une clé primaire constituée de plusieurs colonnes. C'est souvent le cas dans les tables de jointure.

## 1. La classe `CommandeProduitPk`

Dans ce projet, `CommandeProduitPk` est la classe qui représente la clé primaire pour `LigneCommandeProduit`.

- Elle implémente `Serializable` (Obligatoire en JPA).
- Elle doit surcharger `equals()` et `hashCode()` pour comparer les clés primaires de manière fiable.
- Elle contient les colonnes `commande` et `produit`.

```java
@Embeddable
public class CommandeProduitPk implements Serializable {
    @Column(name = "commande")
    private int commandeId;
    
    @Column(name = "produit")
    private int produitId;
    ...
}
```

## 2. Utilisation dans `LigneCommandeProduit`

On utilise `@EmbeddedId` pour intégrer cette clé composée dans l'entité.

```java
@Entity
public class LigneCommandeProduit {
    @EmbeddedId
    private CommandeProduitPk commandeProduitPk;
    
    @ManyToOne
    @JoinColumn(name = "produit", insertable = false, updatable = false)
    private Produit produit;
    ...
}
```

**Note importante** : Les champs `produit` et `commande` dans `LigneCommandeProduit` sont en `insertable = false, updatable = false` car c'est la clé `@EmbeddedId` qui gère déjà ces colonnes en base de données. On ne veut pas que JPA essaie de les insérer deux fois.

## 3. Pourquoi utiliser des clés composées ?
- Pour modéliser des relations N-N avec des données supplémentaires (ex: quantité).
- Pour garantir l'unicité de la relation (on ne peut pas avoir deux fois le même produit dans la même commande).
- Pour respecter les schémas SQL relationnels classiques.
