# Projet Java RPG - MOCI : Design Patterns

## Table des matières
1. [Introduction](#introduction)
2. [Objectifs pédagogiques](#objectifs-pédagogiques)
3. [Description du projet](#description-du-projet)
4. [Architecture et Design Patterns](#architecture-et-design-patterns)
   - [Patterns utilisés](#patterns-utilisés)
   - [Diagrammes UML](#diagrammes-uml)
5. [Code source](#code-source)
6. [Tests unitaires](#tests-unitaires)
   - [Organisation des tests](#organisation-des-tests)
   - [Structure des tests](#structure-des-tests)
7. [Compilation et Utilisation](#compilation-et-utilisation)


---

## Introduction
L'objectif de ce projet est de développer un prototype de jeu de rôle (RPG) dans le cadre du module de **Méthodes et Outils de Conception Informatique (MOCI)**. Le projet se concentre sur l’application et la mise en oeuvre de divers **design patterns**, tout en fournissant un code propre, maintenable et bien documenté.

---

## Objectifs pédagogiques
1. Appliquer des Design Patterns pour résoudre des problèmes de conception spécifiques.
2. Développer une architecture logicielle robuste et flexible.
3. Documenter la conception à l'aide de diagrammes UML clairs.
4. Rédiger des tests unitaires pour valider le bon fonctionnement des différents patterns.

---

## Description du projet
L’objectif est de concevoir un prototype de jeu RPG qui fourni les fonctionnalités suivantes :
- Créer et gérer des personnages et des équipes
- Faire interagir les personnages.
- implémenter différents éléments de gameplay de manière flexible

---

## Architecture et Design Patterns (A MODIFIER)

### Patterns utilisés
Cette section détaille chaque Design Pattern utilisé, leur rôle et comment ils ont résolu des problèmes de conception dans le jeu.

1. **Factory Method** :
    - **Rôle** : Centraliser la création des objets du jeu, comme les ennemis, PNJs, ou items.
    - **Problème résolu** : Éviter de dupliquer le code de création des objets complexes.
    - **Implémentation** : Voir [EnemyFactory.java](src/com/game/factory/EnemyFactory.java).

2. **Observer** :
    - **Rôle** : Gérer les notifications entre les objets du jeu (par exemple, mise à jour de l’interface utilisateur lorsqu’un ennemi est vaincu).
    - **Problème résolu** : Faciliter la communication entre objets sans dépendances directes.

3. **Singleton** :
    - **Rôle** : Garantir une instance unique pour les classes globales comme le gestionnaire de quêtes ou l’état du jeu.
    - **Problème résolu** : Éviter des conflits causés par plusieurs instances gérant le même état.

4. **Strategy** :
    - **Rôle** : Permettre différentes stratégies de combat (par exemple, attaque rapide, attaque puissante).
    - **Problème résolu** : Favoriser l’extensibilité en ajoutant facilement de nouvelles stratégies.

### Diagrammes UML (A MODIFIER)
Les diagrammes UML sont générés à l'aide de **PlantUML**. Ils décrivent :
- La structure des classes.
- Les interactions entre les objets et les patterns.

Vous trouverez les diagrammes [ici](uml/).

---

## Code source
Le code source complet est disponible dans le dossier [main](src/main). Chaque fichier est accompagné de commentaires détaillés pour faciliter la compréhension.

---

## Tests unitaires (A MODIFIER)

### Organisation des tests
Les tests unitaires sont organisés pour valider le comportement des différentes parties du jeu, notamment :
- La logique métier (ex : système de combat, gestion de l’inventaire).
- Les fonctionnalités associées aux Design Patterns (ex : création d’objets avec le Factory Method, notification avec le pattern Observer).

### Structure des tests
Les tests unitaires sont situés dans le répertoire [test](src/test/) et suivent une structure parallèle à celle des fichiers source.

---

## Compilation et Utilisation (A MODIFIER)
