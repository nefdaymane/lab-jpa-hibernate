# Structure du Projet

Le projet est structuré selon les standards Maven pour une application Java.

## 1. Organisation des dossiers

- **`src/main/java/com/test`** : Dossier racine du projet.
  - **`entities`** : Classes Java avec des annotations JPA/Hibernate représentant les tables SQL.
  - **`dao`** : Classes de Data Access Object pour l'interaction avec la base de données.
  - **`services`** : Couche d'abstraction qui gère la logique métier en utilisant les entités et DAO.
  - **`test`** : Classes avec des méthodes `run()` pour tester les différents composants.
  - **`util`** : Classes utilitaires comme `HibernateUtil` pour gérer les sessions Hibernate.
  - **`Main.java`** : Le point d'entrée principal de l'application.

- **`src/main/resources`** : Contient les fichiers de configuration non-Java.
  - **`hibernate.cfg.xml`** : Configuration principale de Hibernate (DB, mapping entités).

- **`pom.xml`** : Gère les dépendances (JPA, Hibernate, MySQL Connector).

## 2. Flux de données typique

`Main.java` -> `Services` -> `AbstractFacade` -> `Database`

Le projet utilise un modèle de Façade Abstraite (`AbstractFacade`) qui définit les opérations courantes (CRUD) pour toutes les entités.
