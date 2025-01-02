package eu.telecomnancy.rpg.App;

import eu.telecomnancy.rpg.characters.Visitable;
import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.decorator.CharacterDecorator;
import eu.telecomnancy.rpg.characters.decorator.InvincibleDecorator;
import eu.telecomnancy.rpg.characters.factory.*;
import eu.telecomnancy.rpg.characters.strategy.*;
import eu.telecomnancy.rpg.characters.team.Team;
import eu.telecomnancy.rpg.characters.team.TeamBuilder;
import eu.telecomnancy.rpg.characters.team.TeamDirector;
import eu.telecomnancy.rpg.characters.team.TeamRegistry;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.characters.visitors.BuffVisitor;
import eu.telecomnancy.rpg.characters.visitors.DamageVisitor;
import eu.telecomnancy.rpg.characters.visitors.HealVisitor;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameFacade {
    private final Map<String, Team> teams = new HashMap<>();
    private final CharacterRegistry characterBank;
    private final TeamRegistry teamBank;

    /**
     * Offers a constructor without parameters, giving a "null" scanner to the other constructor.
     */
    public GameFacade(){
        this(null);
    }

    /**
     * Constructs the game facade, initializing character and team registries,
     * and configuring the game settings based on user input.
     *
     * @param scanner A Scanner instance for reading user input.
     */
    public GameFacade(Scanner scanner) {
        characterBank = new CharacterRegistry();
        teamBank = new TeamRegistry();

        // Register character prototypes
        characterBank.registerPrototype("Warrior", new Warrior("Warrior"));
        characterBank.registerPrototype("Healer", new Healer("Healer"));
        characterBank.registerPrototype("Wizard", new Wizard("Wizard"));

        // Register team prototypes
        TeamDirector teamDirector = new TeamDirector();
        teamBank.registerPrototype("Balanced", teamDirector.constructBalancedTeam());
        teamBank.registerPrototype("Combat", teamDirector.constructCombatTeam());
        teamBank.registerPrototype("Magic", teamDirector.constructWizardTeam());
    }

    /**
     * Unwraps a decorated character to retrieve the base GameCharacter object.
     *
     * @param character The CharacterInterface to unwrap.
     * @return The unwrapped GameCharacter object.
     * @throws RuntimeException if the character cannot be unwrapped.
     */
    public GameCharacter unwrapCharacter(CharacterInterface character) {
        try {
            while (character instanceof CharacterDecorator) {
                character = ((CharacterDecorator) character).getInnerCharacter();
            }
            if (character instanceof GameCharacter) {
                return (GameCharacter) character;
            } else {
                throw new IllegalArgumentException("Invalid character type. Cannot unwrap.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error unwrapping character: " + e.getMessage(), e);
        }
    }

    /**
     * Adds a team with a predefined type or creates a custom team.
     *
     * @param name The name of the team.
     * @param type The type of the team (e.g., "Warrior", "Wizard", "Balanced").
     */
    public void addTeam(String name, String type, List<Pair<String, String>> customCharacters) {
        switch (type) {
            case "Warrior" -> teams.put(name, teamBank.createClone("Combat"));
            case "Wizard" -> teams.put(name, teamBank.createClone("Magic"));
            case "Balanced" -> teams.put(name, teamBank.createClone("Balanced"));
            default -> teams.put(name, createTeam(name, customCharacters));
        }
    }

    /**
     * Creates a custom team based on user input for each character's name and type.
     *
     * @param name The name of the team.
     * @return The created Team object.
     */
    public Team createTeam(String name, List<Pair<String, String>> customCharacters) {
        GameConfiguration config = GameConfiguration.getGameConfiguration();
        int maxSize = config.getMaxSizeTeam();

        TeamBuilder teamBuilder = new TeamBuilder(name);

        // Parcours des personnages fournis dans customCharacters
        for (int i = 0; i < customCharacters.size() && i < maxSize; i++) {
            Pair<String, String> characterInfo = customCharacters.get(i);
            String playerName = characterInfo.getKey();    // Character's name
            String playerType = characterInfo.getValue();  // Character's type

            // Crée le personnage en fonction du type
            GameCharacter character = switch (playerType.toUpperCase()) {
                case "WIZARD" -> {
                    GameCharacter wizard = characterBank.createClone("Wizard");
                    wizard.setName(playerName);
                    yield wizard;
                }
                case "WARRIOR" -> {
                    GameCharacter warrior = characterBank.createClone("Warrior");
                    warrior.setName(playerName);
                    yield warrior;
                }
                case "HEALER" -> {
                    GameCharacter healer = characterBank.createClone("Healer");
                    healer.setName(playerName);
                    yield healer;
                }
                default -> {
                    System.out.println("\nInvalid type for " + playerName + ". Defaulting to Warrior.");
                    GameCharacter defaultWarrior = characterBank.createClone("Warrior");
                    defaultWarrior.setName(playerName);
                    yield defaultWarrior;
                }
            };

            // Ajoute le personnage à l'équipe
            teamBuilder.addPlayer(character);
        }
        // Construit et retourne l'équipe
        return teamBuilder.build();
    }


    /**
     * Removes a team from the registry and cleans up all associated resources.
     *
     * @param name The name of the team to remove.
     */
    public void removeTeam(String name) {
        Team team = teams.get(name);
        if (team != null) {
            for (CharacterInterface character : team.getPlayers()) {
                GameCharacter inner = unwrapCharacter(character);
                inner.detachAllObservers();
            }
            teams.remove(name);
            System.out.println("\nTeam " + name + " has been removed.");
        } else {
            System.out.println("\nTeam " + name + " does not exist.");
        }
    }

    /**
     * Handles a 1v1 attack between characters in two teams.
     *
     * @param team1 the name of the attacker team
     * @param character1 the name of the attacker
     * @param team2 the name of the defender team
     * @param character2 the name of the defender
     * @param damage the amount of damage dealt
     */
    public void attack(String team1, String character1, String team2, String character2, double damage) {
        Team teamNo1 = teams.get(team1);
        Team teamNo2 = teams.get(team2);
        if (teamNo1 != null && teamNo2 != null) {
            CharacterInterface attacker = teamNo1.getPlayer(character1);
            CharacterInterface defender = teamNo2.getPlayer(character2);
            if (attacker != null && defender != null) {
                double actualDealtDamage = attacker.calculateDealDamage(damage);
                double actualTakeDamage = defender.calculateTakeDamage(actualDealtDamage);
                System.out.println("\n\u001B[1m\u001B[35mAn attack occured !!\u001B[0m");
                System.out.println("Character \u001B[1m" + character1 + "\u001B[0m attacked \u001B[1m" + character2 + "\u001B[0m dealing " + actualTakeDamage + " damage.");
                DamageVisitor damageDealer = new DamageVisitor();
                damageDealer.setDamage(actualTakeDamage);
                ((Visitable) defender).accept(damageDealer);
            }
            else {
                System.out.println("\nCharacter " + character1 + " or Character " + character2 + " does not exist.");
            }
        }
        else {
            System.out.println("\nTeam " + team1 + " or Team " + team2 + " does not exist.");
        }
    }


    /**
     * Heals a member of a specified team.
     * @param teamName the name of the team to heal
     * @param character the name of the character to heal
     * @param healAmount the amount of healthPoints to heal
     */
    public void healCharacter(String teamName, String character, int healAmount) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface player = team.getPlayer(character);
            if (player != null) {
                HealVisitor healer = new HealVisitor();
                healer.setHeal(healAmount);

                GameCharacter inner = unwrapCharacter(player);
                inner.accept(healer);
            }
            else {
                System.out.println("\nCharacter " + character + " does not exist.");
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }



    /**
     * Heals all members of a specified team.
     * @param teamName the name of the team to heal
     * @param healAmount the amount of healthPoints to heal
     */
    public void healTeam(String teamName, int healAmount) {
        Team team = teams.get(teamName);
        if (team != null){
            HealVisitor healer = new HealVisitor();
            healer.setHeal(healAmount);

            for (CharacterInterface character : team.getPlayers()) {
                GameCharacter inner = unwrapCharacter(character);
                inner.accept(healer);
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Buff a member of a specified team.
     * @param teamName the name of the team to buff
     * @param character the name of the character to buff
     * @param buffAmount the amount to buff
     */
    public void buffCharacter(String teamName, String character, int buffAmount) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface player = team.getPlayer(character);
            if (player != null) {
                BuffVisitor buffer = new BuffVisitor();
                buffer.setBuff(buffAmount);

                GameCharacter inner = unwrapCharacter(player);
                inner.accept(buffer);
            }
            else {
                System.out.println("\nCharacter " + character + " does not exist.");
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Buffs all members of a specified team by a given factor.
     *
     * @param teamName the name of the team to buff
     * @param buffAmount the amount of buff received by each character
     */
    public void buffTeam(String teamName, int buffAmount) {
        Team team = teams.get(teamName);
        if (team != null){
            BuffVisitor buffer = new BuffVisitor();
            buffer.setBuff(buffAmount);

            for (CharacterInterface character : team.getPlayers()) {
                GameCharacter inner = unwrapCharacter(character);
                inner.accept(buffer);
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Adds armor to a character by wrapping it with an ArmoredDecorator.
     *
     * @param teamName the name of the team considered
     * @param characterName the name of the receiver
     * @param armorFactor the "amount of armor" received
     */
    public void addArmor(String teamName, String characterName, double armorFactor) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                if (character instanceof InvincibleDecorator){
                    System.out.println("\nCharacter " + characterName + " is invincible. It's impossible to put an armor " +
                            "on an invincible character.");
                }
                else {
                    if (armorFactor >= 100){
                        System.out.println("The armor amount is too high, please do not give an armor with more than 80 of armor amount. Armor caped to 80");
                        armorFactor = 80;
                    }
                    CharacterInterface armoredCharacter = new ArmoredDecorator(character, armorFactor);
                    team.changePlayer(character, armoredCharacter);
                }
            }
            else {
                System.out.println("\nCharacter " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Makes a character invincible by wrapping it with an InvincibleDecorator.
     *
     * @param teamName the name of the team considered
     * @param characterName the name of the receiver
     */
    public void makeInvincible(String teamName, String characterName) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                if (character instanceof InvincibleDecorator){
                    System.out.println("\nCharacter " + characterName + " is already invincible. It's impossible to put invincibility " +
                            "on an already invincible character.");
                }
                else {
                    CharacterInterface invincibleCharacter = new InvincibleDecorator(character);
                    team.changePlayer(character, invincibleCharacter);
                }
            }
            else {
                System.out.println("\nCharacter " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Removes the last decorator from a character if it has one.
     *
     * @param teamName the name of the team considered
     * @param characterName the name of the character considered
     */
    public void removeDecorator(String teamName, String characterName) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                if (character instanceof CharacterDecorator){;
                    team.changePlayer(character, ((CharacterDecorator) character).getInnerCharacter());
                }
                else {
                    System.out.println("\nCharacter " + characterName + " has neither Armor nor is Invincible.");
                }
            }
            else {
                System.out.println("\nCharacter " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Removes all decorators from a character, restoring it to its original state.
     *
     * @param teamName the name of the team considered
     * @param characterName the name of the character considered
     */
    public void removeAllDecorators(String teamName, String characterName) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                CharacterInterface inner = unwrapCharacter(character);
                team.changePlayer(character, inner);
            }
            else {
                System.out.println("\nCharacter " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Retrieves all registered teams.
     *
     * @return A map of team names and their corresponding Team objects.
     */
    public Map<String, Team> getTeams() {
        return teams;
    }

    /**
     * Print the composition of a team.
     *
     * @param teamName the team to print.
     */
    public void printTeam(String teamName){
        Team team = teams.get(teamName);
        if (team != null){
            System.out.println("\n\u001B[1mTeam\u001B[0m : " + teamName);
            for (CharacterInterface player : team.getPlayers()) {
                System.out.println(player.toString());
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
        }
    }

    /**
     * Renames a team.
     *
     * @param nameTeam The current name of the team.
     * @param newName The new name for the team.
     */
    public void renameTeam(String nameTeam, String newName) {
        Team team = teams.get(nameTeam);
        if (team != null && !teams.containsKey(newName)){
            teams.remove(nameTeam);
            team.setName(newName);
            teams.put(newName, team);
        }
        else {
            System.out.println("\nThe team " + nameTeam + " does not exist or the team " + newName + " already exists.");
        }
    }

    /**
     * Renames a character within a specific team.
     *
     * @param nameTeam The name of the team containing the character.
     * @param nameCharacter The current name of the character.
     * @param newName The new name for the character.
     */
    public void renameCharacter(String nameTeam, String nameCharacter, String newName) {
        Team team = teams.get(nameTeam);
        if (team != null) {
            CharacterInterface character = team.getPlayer(nameCharacter);
            if (character != null && (team.getPlayer(newName) == null)) {
                character.setName(newName);
            } else {
                System.out.println("Character not found: " + nameCharacter + " or character " + newName + " already exists.");
            }
        } else {
            System.out.println("Team not found: " + nameTeam);
        }
    }

    /**
     * Change the strategy of a character.
     * @param teamName the name of the involved team
     * @param characterName the name of the involved character
     * @param strategy the new strategy to set
     *
     */
    public CombatStrategy changeStrategy(String teamName, String characterName, String strategy){
        CombatStrategy combatStrategy = null;
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                combatStrategy = character.getCombatStrategy();
                switch (strategy.toUpperCase()){
                    case "NEUTRAL" -> character.setCombatStrategy(new NeutralStrategy());
                    case "AGGRESSIVE" -> character.setCombatStrategy(new AggressiveStrategy());
                    case "DEFENSIVE" -> character.setCombatStrategy(new DefensiveStrategy());
                    case "STONE GOLEM" -> character.setCombatStrategy(new StoneGolemStrategy());
                    case "BERSERKER" -> character.setCombatStrategy(new BerserkerStrategy());
                    default -> {
                        System.out.println("No strategy " + strategy + " exists. Defaulting to Neutral.");
                        character.setCombatStrategy(new NeutralStrategy());
                    }
                }
                return combatStrategy;
            }
            else {
                System.out.println("\nCharacter " + characterName + " does not exist.");
                return null;
            }
        }
        else {
            System.out.println("\nTeam " + teamName + " does not exist.");
            return null;
        }
    }
}
