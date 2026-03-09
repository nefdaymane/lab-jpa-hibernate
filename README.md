# Lab JPA & Hibernate - Gestion de Commandes

Ce projet est un laboratoire pratique sur l'utilisation de **JPA 2.2** et **Hibernate 5.6** dans une application Java Standard (JavaSE). Il simule un système de gestion de commandes avec des produits et des catégories.

## 🚀 Fonctionnalités du Projet

- **Persistance des données** : Utilisation de MySQL 8.
- **Modèle de données relationnel** : Tables liées par des clés étrangères.
- **Architecture Façade** : Utilisation de `AbstractFacade` pour le CRUD générique.
- **Clés Composées** : Gestion avancée via `@EmbeddedId` pour les lignes de commande.
- **Tests unitaires simulés** : Classes de test pour valider chaque service.

## 📂 Documentation du Projet

La documentation détaillée est organisée par thématiques dans le dossier `docs` :

1. [🔌 Connexion à la Base de Données](docs/01-connexion-bd.md) - Configuration Hibernate et MySQL.
2. [🏗️ Structure du Projet](docs/02-structure-projet.md) - Organisation des packages et Maven.
3. [📊 Les Entités JPA](docs/03-entites-jpa.md) - Mapping des classes vers les tables SQL.
4. [🔑 Clés Composées](docs/04-cles-composees.md) - Utilisation de `@Embeddable` et `@EmbeddedId`.
5. [🛠️ Services et Façades](docs/05-services-facades.md) - Logique métier et CRUD générique.
6. [✨ Bonnes Pratiques](docs/06-bonnes-pratiques.md) - Conseils de développement avec JPA/Hibernate.

## 🛠️ Pré-requis

- **Java JDK 21+**
- **Maven 3.x**
- **MySQL Server 8.x**
- **Un IDE** (IntelliJ IDEA conseillé)

## 🏁 Démarrage Rapide

1. Clonez ce dépôt sur votre machine locale.
2. Configurez votre base de données MySQL et créez une DB nommée `lab1`.
3. Vérifiez vos identifiants dans `src/main/resources/hibernate.cfg.xml`.
4. Compilez le projet :
   ```bash
   mvn clean install
   ```
5. Lancez l'application via la classe `com.test.Main`.

---
© 2026 - Lab JPA Hibernate Project
