package eu.telecomnancy.rpg.App;

import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.decorator.InvincibleDecorator;
import eu.telecomnancy.rpg.characters.factory.*;
import eu.telecomnancy.rpg.characters.team.Team;
import eu.telecomnancy.rpg.characters.team.TeamBuilder;
import eu.telecomnancy.rpg.characters.visitors.BuffVisitor;

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

        CharacterInterface characterTest = new Warrior("SuperMegaWarrior");
        BuffVisitor buff = new BuffVisitor();
        buff.setBuff(56);
        ((GameCharacter) characterTest).accept(buff);
        CharacterInterface characterTest2 = new ArmoredDecorator(characterTest, 30);
        CharacterInterface characterTest3 = new ArmoredDecorator(characterTest2, 40);
        CharacterInterface characterTest4 = new ArmoredDecorator(characterTest3, 50);
        CharacterInterface characterTest5 = new InvincibleDecorator(characterTest4);
        System.out.println(characterTest5.toString());

        GameFacade gameFacade = new GameFacade();
        gameFacade.addTeam("Rocket", "Balanced", null);
        //gameFacade.addTeam("Communauté de l'anneau", "custom");
        gameFacade.printTeam("Rocket");
        gameFacade.addArmor("Rocket", "Smart Wizard", 70);
        gameFacade.addArmor("Rocket", "Kind Healer", 30);
        gameFacade.addArmor("Rocket", "Kind Healer", 40);
        gameFacade.makeInvincible("Rocket", "Strong Warrior");
        gameFacade.printTeam("Rocket");
        gameFacade.removeDecorator("Rocket", "Smart Wizard");
        gameFacade.removeDecorator("Rocket", "Kind Healer");
        gameFacade.printTeam("Rocket");
        //gameFacade.printTeam("Communauté de l'anneau");

    }
}
