# Guide de Connexion à la Base de Données

Ce document explique comment le projet se connecte à la base de données MySQL en utilisant Hibernate.

## 1. Configuration Hibernate (`hibernate.cfg.xml`)

La configuration se trouve dans `src/main/resources/hibernate.cfg.xml`. Voici les paramètres clés :

- **Dialecte** : `org.hibernate.dialect.MySQL8Dialect` (pour MySQL 8+)
- **Driver JDBC** : `com.mysql.cj.jdbc.Driver`
- **URL** : `jdbc:mysql://localhost:3306/lab1` (Base de données `lab1` sur le port par défaut 3306)
- **Identifiants** : `root` (sans mot de passe spécifié dans ce fichier)

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/lab1</property>
<property name="hibernate.connection.username">root</property>
```

## 2. Utilitaire Hibernate (`HibernateUtil.java`)

Le projet utilise une classe utilitaire `HibernateUtil` pour gérer la `SessionFactory`. Elle est responsable de l'ouverture et de la fermeture des sessions SQL.

## 3. Auto-Génération du Schéma

La propriété `hibernate.hbm2ddl.auto` est définie sur `update`. Cela signifie que Hibernate va :
- Créer les tables si elles n'existent pas.
- Mettre à jour la structure des tables si elle change.

## 4. Étapes pour démarrer
1. S'assurer qu'un serveur MySQL est lancé.
2. Créer une base de données nommée `lab1`.
3. Vérifier les identifiants dans `hibernate.cfg.xml`.
4. Lancer l'application via `Main.java`.
