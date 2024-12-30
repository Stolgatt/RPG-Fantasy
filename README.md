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

1. **Singleton** :
    - **Rôle** : Garantir une instance unique d'une classe regroupant des paramètres globaux du jeu et accessibles de partout
    - **Problème résolu** : on cherche à avoir une classe qui regroupe des paramètres utiles à de nombreux endroits de la conception et qui soit unique, pour éviter des doublons de configuration qui affecterait la cohérence du jeu.
    - **Implémentation** : voir [GameConfiguration.java](src/main/java/eu/telecomnancy/rpg/GameConfiguration.java). Une seule classe avec un attribut static qui stock sa propore instance unique et un constructeur public et static qui soit renvoie cette instance si elle est initialisée, soit la créer. Le constructeur sans paramètre est marqué privé.

2. **Factory Method** :
    - **Rôle** : Centraliser la création des objets du jeu, comme les personnages, ennemis ou encore les 
    - **Problème résolu** : Éviter de dupliquer le code de création des objets complexes ou légèrement différents et permet de substituer un objet par un autre s'ils peuvent subir les mêmes effets et transformations. Le système ne connait alors que le type GameCharacter et effectue le même travail sur chaque personnage, quelque soit sont type réel.
    - **Implémentation** : Voir dossier [Factory](src/main/java/eu/telecomnancy/rpg/characters/factory). Une interface CharacterCreator permet d'implémenter un créateur spécifique par personnage ajouté au jeu (actuellement seuls Warrior, Wizard et Healer sont présents, chacun avec leur factory)

3. **Prototypes** :
    - **Rôle** : Permet de rendre chaque élément qui implémente l'interface prototype clonable. C'est alors le cass pour le type Team et GameCharacter qui sont des objets clonables. On peut alors cloner des objets existants en copiant leurs paramètre.
    - **Problème résolu** : On peut vouloir chercher à copier un objet complexe, créer un nouvel objet qui possède les mêmes attributs mais bien souvent ces attributs sont privés et ainsi difficile d'accès. On peut alors créer des objets clonables via ce pattern et toutes les classes d'objets clonables doivent implémenter leur version de la méthode clone() qui permet de renvoyer un objet du même type que l'objet cloné et avec les mêmes attributs.
    - **Implémentation** : voir interface [Prototype.java](src/main/java/eu/telecomnancy/rpg/characters/factory) et dossier [Team](src/main/java/eu/telecomnancy/rpg/characters/factory). On a cette interface que toutes classes d'objets clonable doivent implémenter et qui oblige la création d'une méthode clone().

4. **Builder** :
    - **Rôle** : construire des objets d'un même type mais avec de légère variations sont multiplier les classes et le code.
    - **Problème résolu** : On cherche à pouvoir créer des objets similaires mais pas exactement les mêmes. Plutôt que de créer une classe par version de l'objets, quitte à écrire de nombreuses classes pour des variations légère dans la configuration de l'objet, on va plutôt mettre en place une classe Builder qui va se charger d'initialiser l'objet à créer et va ensuite proposer un certain nombre de méthodes qui permettront de customiser l'objet comme on le souhaite avant de la récupérer.
    - **Implémentation** : à nouveau le dossier [Team](src/main/java/eu/telecomnancy/rpg/characters/factory) qui contient les fichiers permettant la création d'équipe par ajout successif de personnages et même un TeamDirector qui permet de créer des team par défaut, composés de différents personnages. Il suffit alors de créer ces teams au démarrage de l'application et de les stocker dans un TeamRegistry afin de les cloner ensuite via le pattern Prototype.

5. **Visiteur** :
    - **Rôle** :
    - **Problème résolu** :
    - **Implémentation** :

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
