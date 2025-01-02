package eu.telecomnancy.rpg.App;

import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.command.*;
import eu.telecomnancy.rpg.command.concreteCommand.*;

public class GameClientScenario {
    public static void main(String[] args) {
        GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
        GameFacade gameFacade = new GameFacade();
        GameInvoker invoker = new GameInvoker(gameFacade);
        CommandParameters cp = new CommandParameters();

        cp.clean();
        cp.set("difficulty", "2");
        cp.set("maxSizeTeam", "5");
        Command changeSettings = new ModifySettingsCommand();
        invoker.executeCommand(changeSettings, cp);

        cp.clean();
        cp.set("team", "Rocket");
        cp.set("type", "Balanced");
        Command addTeam = new AddTeamCommand();
        invoker.executeCommand(addTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        Command printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        cp.set("character", "Smart Wizard");
        cp.set("armorAmount", "70");
        Command addArmor = new AddArmorCommand();
        invoker.executeCommand(addArmor, cp);

        cp.clean();
        cp.set("team", "Rocket");
        cp.set("character", "Kind Healer");
        cp.set("armorAmount", "30");
        addArmor = new AddArmorCommand();
        invoker.executeCommand(addArmor, cp);

        cp.clean();
        cp.set("team", "Rocket");
        cp.set("character", "Kind Healer");
        cp.set("armorAmount", "40");
        addArmor = new AddArmorCommand();
        invoker.executeCommand(addArmor, cp);

        cp.clean();
        cp.set("team", "Rocket");
        cp.set("character", "Strong Warrior");
        Command makeInvincible = new AddInvincibilityCommand();
        invoker.executeCommand(makeInvincible, cp);

        invoker.undoLastCommand();

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        cp.set("buffAmount", "46");
        Command buffTeamCommand = new BuffTeamCommand();
        invoker.executeCommand(buffTeamCommand, cp);

        cp.clean();
        cp.set("team", "RocketMan");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        cp.set("buffAmount", "35");
        cp.set("character", "Strong Warrior");
        Command buffCommand = new BuffCommand();
        invoker.executeCommand(buffCommand, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        invoker.undoLastCommand();

        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("type", "Custom");
        cp.set("name1", "Gandalf");
        cp.set("name2", "Gimli");
        cp.set("name3", "Aragorn");
        cp.set("name4", "Frodon");
        cp.set("name5", "Legolas");
        cp.set("type1", "Wizard");
        cp.set("type2", "Warrior");
        cp.set("type3", "Warrior");
        cp.set("type4", "Healer");
        cp.set("type5", "Warrior");
        addTeam = new AddTeamCommand();
        invoker.executeCommand(addTeam, cp);

        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

// Ajouter une armure à Gandalf
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Gandalf");
        cp.set("armorAmount", "50");
        addArmor = new AddArmorCommand();
        invoker.executeCommand(addArmor, cp);

// Ajouter une invincibilité à Aragorn
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Aragorn");
        makeInvincible = new AddInvincibilityCommand();
        invoker.executeCommand(makeInvincible, cp);

// Buff Gandalf
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Gandalf");
        cp.set("buffAmount", "20");
        buffCommand = new BuffCommand();
        invoker.executeCommand(buffCommand, cp);

// Buff toute l'équipe
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("buffAmount", "15");
        buffTeamCommand = new BuffTeamCommand();
        invoker.executeCommand(buffTeamCommand, cp);

// Soigner Aragorn
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Aragorn");
        cp.set("healAmount", "15");
        Command healCommand = new HealCommand();
        invoker.executeCommand(healCommand, cp);

// Soigner toute l'équipe
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("healAmount", "30");
        Command healTeamCommand = new HealTeamCommand();
        invoker.executeCommand(healTeamCommand, cp);

// Afficher l'équipe
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        /**
         * Tenter plusieurs attaques sur des personnages de ces deux équipes,
         * avec ou sans armure, avec ou sans invincibilité, ou avec ou sans buff
         */
        cp.clean();

// Première attaque : Rocket (sans armure) attaque Communautée (avec armure)
        cp.set("team1", "Rocket");
        cp.set("team2", "Communautée de l'anneau");
        cp.set("character1", "Strong Warrior");
        cp.set("character2", "Gandalf"); // Gandalf a une armure (50.0)
        cp.set("damageAmount", "40");
        AttackCommand attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

// Affichage des équipes après la première attaque
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

// Deuxième attaque : Communautée (avec invincibilité) attaque Rocket (sans armure)
        cp.clean();
        cp.set("team1", "Communautée de l'anneau");
        cp.set("team2", "Rocket");
        cp.set("character1", "Aragorn"); // Aragorn a l'invincibilité
        cp.set("character2", "Smart Wizard"); // Smart Wizard a une armure (70.0)
        cp.set("damageAmount", "50");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

// Affichage des équipes après la deuxième attaque
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

// Troisième attaque : Rocket (sans buff) attaque Communautée (sans armure)
        cp.clean();
        cp.set("team1", "Rocket");
        cp.set("team2", "Communautée de l'anneau");
        cp.set("character1", "Kind Healer");
        cp.set("character2", "Legolas"); // Legolas n'a pas d'armure
        cp.set("damageAmount", "30");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

// Affichage des équipes après la troisième attaque
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

// Quatrième attaque : Communautée (avec buff) attaque Rocket (sans armure)
        cp.clean();
        cp.set("team1", "Communautée de l'anneau");
        cp.set("team2", "Rocket");
        cp.set("character1", "Gimli"); // Gimli sans buff
        cp.set("character2", "Strong Warrior"); // Strong Warrior sans armure
        cp.set("damageAmount", "45");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

// Affichage des équipes après la quatrième attaque
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);


        System.out.println("\n\n************* Passage aux combats à mort avec stratégies *************\n");
        /** Combat avec stratégies */

        // 1. Change strategy to Aggressive and attack
        cp.clean();
        cp.set("team", "Rocket");
        cp.set("character", "Strong Warrior");
        cp.set("strategy", "Aggressive");
        ChangeStrategyCommand changeStrategyCommand = new ChangeStrategyCommand();
        invoker.executeCommand(changeStrategyCommand, cp);

        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Gandalf");
        cp.set("strategy", "Defensive");
        changeStrategyCommand = new ChangeStrategyCommand();
        invoker.executeCommand(changeStrategyCommand, cp);

        // Deuxième armure sur Gandalf
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Gandalf");
        cp.set("armorAmount", "60");
        addArmor = new AddArmorCommand();
        invoker.executeCommand(addArmor, cp);

        cp.clean();
        cp.set("team1", "Rocket");
        cp.set("team2", "Communautée de l'anneau");
        cp.set("character1", "Strong Warrior");
        cp.set("character2", "Gandalf"); // Gandalf a une armure
        cp.set("damageAmount", "30");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

        // Afficher les équipes
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        // 2. Cibler un personnage invincible
        cp.clean();
        cp.set("team2", "Communautée de l'anneau");
        cp.set("team1", "Rocket");
        cp.set("character2", "Aragorn"); // Aragorn est invincible
        cp.set("character1", "Smart Wizard");
        cp.set("damageAmount", "50");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

        // Afficher les équipes
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        invoker.executeCommand(printTeam, cp);

        // 3. Briser l'armure et attaquer jusqu'à la mort
        cp.clean();
        cp.set("team1", "Rocket");
        cp.set("team2", "Communautée de l'anneau");
        cp.set("character1", "Strong Warrior");
        cp.set("character2", "Gandalf");
        cp.set("damageAmount", "105");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        invoker.executeCommand(printTeam, cp);

        // Continuer jusqu'à la mort
        cp.clean();
        cp.set("team", "Rocket");
        cp.set("character", "Smart Wizard");
        cp.set("strategy", "Berserker");
        changeStrategyCommand = new ChangeStrategyCommand();
        invoker.executeCommand(changeStrategyCommand, cp);

        cp.clean();
        cp.set("team1", "Rocket");
        cp.set("team2", "Communautée de l'anneau");
        cp.set("character1", "Smart Wizard");
        cp.set("character2", "Gandalf");
        cp.set("damageAmount", "146");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team1", "Rocket");
        cp.set("team2", "Communautée de l'anneau");
        cp.set("character1", "Strong Warrior");
        cp.set("character2", "Gandalf");
        cp.set("damageAmount", "115");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team1", "Rocket");
        cp.set("team2", "Communautée de l'anneau");
        cp.set("character1", "Smart Wizard");
        cp.set("character2", "Gandalf");
        cp.set("damageAmount", "154");
        attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);

        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        cp.clean();
        cp.set("team", "Rocket");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

        /** Fin des attaques */


// Enlever l'armure ou l'invincibilité de Gandalf
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Gandalf");
        Command removeArmorOrInvincibility = new RemoveArmorOrInvincibilityCommand();
        invoker.executeCommand(removeArmorOrInvincibility, cp);

// Enlever l'armure ou l'invincibilité d'Aragorn
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("character", "Aragorn");
        removeArmorOrInvincibility = new RemoveArmorOrInvincibilityCommand();
        invoker.executeCommand(removeArmorOrInvincibility, cp);

// Changer le nom de l'équipe
        cp.clean();
        cp.set("team", "Communautée de l'anneau");
        cp.set("newName", "Heroes of Middle Earth");
        Command changeTeamName = new ChangeTeamNameCommand();
        invoker.executeCommand(changeTeamName, cp);

// Afficher la nouvelle équipe
        cp.clean();
        cp.set("team", "Heroes of Middle Earth");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

// Changer le nom de Gandalf en Saruman
        cp.clean();
        cp.set("team", "Heroes of Middle Earth");
        cp.set("character", "Gandalf");
        cp.set("newName", "Saruman");
        Command changeCharacterName = new ChangeCharacterNameCommand();
        invoker.executeCommand(changeCharacterName, cp);

// Afficher la modification
        cp.clean();
        cp.set("team", "Heroes of Middle Earth");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);

// Supprimer l'équipe
        cp.clean();
        cp.set("team", "Heroes of Middle Earth");
        Command removeTeam = new RemoveTeamCommand();
        invoker.executeCommand(removeTeam, cp);

        invoker.undoLastCommand();

// Essayer d'afficher une équipe supprimée
        cp.clean();
        cp.set("team", "Heroes of Middle Earth");
        printTeam = new PrintTeamCommand();
        invoker.executeCommand(printTeam, cp);


        invoker.printActionHistory();
    }
}