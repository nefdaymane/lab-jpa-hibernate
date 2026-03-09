# Bonnes Pratiques avec JPA et Hibernate

Ce projet suit plusieurs bonnes pratiques de développement pour assurer la qualité et la maintenabilité du code.

## 1. Modularité
L'architecture sépare les entités, les accès aux données (Services/Façades) et la logique de test. Chaque classe a une responsabilité unique.

## 2. Abstraction via `AbstractFacade<T>`
L'utilisation de la généricité avec `AbstractFacade<T>` évite la duplication de code pour les opérations CRUD de base.

## 3. Gestion des sessions Hibernate
`HibernateUtil` assure que la `SessionFactory` est créée une seule fois (Singleton) et permet de la fermer proprement.

## 4. Clés Composées propres
L'utilisation de `@Embeddable` et `@EmbeddedId` est la manière propre de JPA pour gérer les tables de jointure avec des données métier.

## 5. Utilisation des `NamedQuery`
Définir les requêtes sur l'entité elle-même (`@NamedQuery`) permet de centraliser le SQL/JPQL et de le réutiliser dans les services.

## 6. Transactions robustes
Toujours utiliser un bloc `try-catch` pour les transactions afin de faire un `rollback()` en cas d'erreur fatale.

## 7. Relations bien définies
Spécifier clairement les types de relations (`ManyToOne`, `OneToMany`) permet à JPA de générer correctement les clés étrangères en SQL.

## 8. Naming Conventions
- Classes au singulier : `Produit`, `Commande`, `Categorie`.
- Tables au pluriel (dans `@Table`) : `produits`, `commandes`, `categories`.
- Nommer les classes de clés primaires avec le suffixe `Pk`.
- Suffixer les classes de service avec `Service`.
- Suffixer les classes de test avec `Test`.
