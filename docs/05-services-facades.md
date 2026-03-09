# Architecture des Services et Façades

Ce projet utilise le design pattern Façade pour isoler l'accès aux données (DAO) de la logique de l'application.

## 1. La classe `AbstractFacade<T>`

`AbstractFacade` est la base générique de tous les services du projet. Elle définit les méthodes CRUD standards :
- `void create(T entity)` : Ajoute une nouvelle entité.
- `void edit(T entity)` : Met à jour une entité existante.
- `void remove(T entity)` : Supprime une entité.
- `T find(Object id)` : Recherche une entité par son ID.
- `List<T> findAll()` : Récupère toutes les entités de la table.
- `int count()` : Retourne le nombre total d'entités.

```java
public abstract class AbstractFacade<T> {
    private Class<T> entityClass;
    ...
    protected abstract EntityManager getEntityManager();
    ...
}
```

## 2. Les Services Spécialisés

Chaque entité a son propre service qui hérite de `AbstractFacade`. Par exemple, `ProduitService` :
- Hérite des méthodes CRUD génériques de `AbstractFacade<Produit>`.
- Peut implémenter des méthodes spécifiques comme la recherche de produits par prix ou par stock.

## 3. Gestion des transactions
Les services ouvrent une transaction (`em.getTransaction().begin()`) avant d'effectuer des modifications en base de données et la ferment (`em.getTransaction().commit()`) à la fin.

## 4. Pourquoi utiliser des services ?
- Pour centraliser la logique de gestion des entités.
- Pour assurer la cohérence des données via les transactions.
- Pour rendre le code plus modulaire et facile à tester.
- Pour éviter de répéter le même code SQL/JPQL partout dans l'application.
