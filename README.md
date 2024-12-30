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
    - **Rôle** : Implémenter des algorithmes qui s'appliqueront sur différents objets, et permettre à différents types d'objets de recevoir des modifications par la même classe, avec potentiellement un algorithme différents selon le type.
    - **Problème résolu** : On chercher à effectuer différentes modifications ou appliquer différents effets à des objets de type variés. Plutôt que de dupliquer le code de nombreuses fois et surcharger les classes d'objets de codes liés à ces modifications, on va plutôt chercher à écrire des classes de Visiteurs qui vont contenir le code nécéssaire et s'imposer aux objets qui nécéssitent des modifications. Ceux-ci, via une méthode accept vont recevoir le Visiteur et s'appliquer eux-même le code nécéssaire et qui correspond à leur type. La classe Visiteur est donc là pour apporter dynamiquement le code nécéssaire à un objet, sans surcharger statiquement le code de l'objet.
    - **Implémentation** : voir dossier [visitors](src/main/java/eu/telecomnancy/rpg/characters/visitors). On a une interface permettant d'unifier le code des objets visitable et également une interface pour unifier le code des objets visités. Tous les visiteurs doivent implémenter une version de l'algorithme qu'ils représentent destinée à chaque objet visitable.

6. **Strategy** :
    - **Rôle** : Fournir dynamiquement à des objets des variations dans leurs algorithmes.
    - **Problème résolu** : Plutôt que de surcharger le code d'un objet en lui ajoutant par exemple une variable stockant son "état" et des conditions dans l'algorithme permettant d'appliquer telle ou telle version de l'algorithme selon l'"état" actuel de l'objet, on va plutot demander à l'objet de stocker une instance d'une stratégie. Dans le code de cet objet, la méthode qui applique l'algorithme en question va alors demander à l'instance de la stratégie d'appliquer sa version de l'algorithme. Les stratégies étant intervertibles, on peut dynamiquement indiquer à l'objet d'appliquer une version ou l'autre de l'algorithme.
    - **Implémentation** : voir dossier [strategy](src/main/java/eu/telecomnancy/rpg/characters/strategy). Il y a une interface générale de stratégie et chaque stratégie concrête va implémenter sa version de chacun des algorithmes qui doivent être adaptés dynamiquement. 

7. **Observer** :
    - **Rôle** : Observer des changements dans les attributs de l'objet qui leur est confié et y réagir si nécéssaire
    - **Problème résolu** : On cherche à automatiser la mort ou la montée en niveau d'un personnage. Encore une fois dans une optique de séparer les responsabilités et éviter de surcharger le code, on va créer un Observer pour chacune de ces deux fonctions et tout personnage créé se verra attribuer les deux observer nécéssaires. Lors de modifications de certains de ses attributs comme ici la vie et la quantité de point d'expérience, les observer vont êêtre réveillés et vont éventuellement réagir (vie nulle ou négative ou points d'expérience dépassant un seuil).
    - **Implémentation** : voir dossier [observer](src/main/java/eu/telecomnancy/rpg/characters/observer). Il y a deux interfaces : Observer pour chaque observer et Observable pour que les objets observables implémentent les méthodes nécéssaires à l'ajout d'un observer, à sa suppression et à la notification des observer liés à l'objet.

8. **Decorators** : 
    - **Rôle** : ajouter et supprimer dynamiquement des améliorations (modifications) durables sur un objet.
    - **Problème résolu** : On veut pouvoir modifier le comportement d'un objet de manière dynamique en lui ajoutant un effet, une amélioration. En l'occurence ici on veut pouvoir ajouter une ou plusieurs armures à un personnage qui va alors recevoir moins de dégats voir même le rendre invincible. On va donc créer des classes qui d'un côté stockeront une instance de l'objet équipé d'une armure ou invincible (un personnage éventuellement déjà entouré d'armure) et d'un autre côté pourront apporter un pré-traitement ou un post-traitement aux méthodes contenues dans la classe de l'objet, par exemple diminuer les dégats reçus avant qu'ils ne soient comptabilisés par le personnage directement. 
    - **Implémentation** : voir dossier [decorator](src/main/java/eu/telecomnancy/rpg/characters/decorator). On une classe abstraite CharacterDecorator qui va pouvoir stocker et ensuite se substituer dans le reste de l'application à un GameCharacter via l'interface CharacterInterface. Alors les méthodes calculateDealDamage et CalculateTakeDamage implémentées dans la classse GameCharacter vont être réécrite en ajoutant un pré-traitement au paramètre baseDamage avant d'appeler la même méthode dans l'instance de CharacterInterface équipée de ce décorateur et récursivement diminuer la quantité de dégat reçus avant d'être finalement comptabilisé par l'instance de GameCharacter à l'intérieur de tous les décorateurs. 

8. **Command** : 
    - **Rôle**
    - **Problème résolu** :
    - **Implémentation** :

8. **Invoker** : 
    - **Rôle**
    - **Problème résolu** :
    - **Implémentation** :

8. **Client** : 
    - **Rôle**
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
