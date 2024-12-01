package eu.telecomnancy.rpg.App;

import eu.telecomnancy.rpg.characters.*;

import java.lang.System;

public class Main {

    public static void main (String[] args) {
        CharacterRegistry registry = new CharacterRegistry();

        Warrior warrior = new Warrior("Warrior Prototype");
        registry.registerPrototype("warrior", warrior);
        Healer healer = new Healer("Healer Prototype");
        registry.registerPrototype("healer", healer);
        Wizard wizard = new Wizard("Wizard Prototype");
        registry.registerPrototype("wizard", wizard);

        // Création d'une nouvelle instance en utilisant le prototype
        Warrior clonedWarrior = (Warrior) registry.createClone("warrior");
        clonedWarrior.setName("Cloned Warrior");
        clonedWarrior.setStrength(80);

        //Création d'une équipe avec le TeamBuilder
        System.out.println(clonedWarrior);

        Team team = new TeamBuilder("Dream Team")
                .addPlayer(new Warrior("Conan"))
                .addPlayer(new Wizard("Gandalf"))
                .addPlayer(new Healer("Florence"))
                .build();

        System.out.println("Team created: " + team.getName());
        System.out.println("Team size: " + team.size());
    }
}
