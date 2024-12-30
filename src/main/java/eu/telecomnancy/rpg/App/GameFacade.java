package eu.telecomnancy.rpg.App;

import eu.telecomnancy.rpg.characters.Visitable;
import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.decorator.CharacterDecorator;
import eu.telecomnancy.rpg.characters.decorator.InvincibleDecorator;
import eu.telecomnancy.rpg.characters.factory.*;
import eu.telecomnancy.rpg.characters.team.Team;
import eu.telecomnancy.rpg.characters.team.TeamBuilder;
import eu.telecomnancy.rpg.characters.team.TeamDirector;
import eu.telecomnancy.rpg.characters.team.TeamRegistry;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.characters.visitors.BuffVisitor;
import eu.telecomnancy.rpg.characters.visitors.DamageVisitor;
import eu.telecomnancy.rpg.characters.visitors.HealVisitor;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameFacade {
    private final Map<String, Team> teams = new HashMap<>();
    private final CharacterRegistry characterBank;
    private final TeamRegistry teamBank;
    private final Scanner scanner;

    /**
     * Constructs the game facade, initializing character and team registries,
     * and configuring the game settings based on user input.
     */
    public GameFacade(Scanner scanner) {
        // Initialize registries
        characterBank = new CharacterRegistry();
        teamBank = new TeamRegistry();
        if (scanner != null) {
            this.scanner = scanner;
        }
        else {
            this.scanner = new Scanner(System.in);
            System.out.println("System.in a été utilisé");
        }
        //this.scanner = scanner != null ? scanner : new Scanner(System.in);

        // Register character prototypes
        characterBank.registerPrototype("Warrior", new Warrior("Warrior"));
        characterBank.registerPrototype("Healer", new Healer("Healer"));
        characterBank.registerPrototype("Wizard", new Wizard("Wizard"));

        // Register team prototypes
        TeamDirector teamDirector = new TeamDirector();
        teamBank.registerPrototype("Balanced", teamDirector.constructBalancedTeam());
        teamBank.registerPrototype("Combat", teamDirector.constructCombatTeam());
        teamBank.registerPrototype("Magic", teamDirector.constructWizardTeam());

        // Request game configuration settings from the user
        configureGameSettings();
    }

    /**
     * Configures game settings by asking the user to define difficulty and maximum team size.
     */
    private void configureGameSettings() {
        GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
        //Scanner scanner = new Scanner(System.in);

        // Retrieve difficulty range from configuration
        int minDifficulty = gameConfiguration.getMinDifficulty();
        int maxDifficulty = gameConfiguration.getMaxDifficulty();

        // Request difficulty
        System.out.println("Enter game difficulty (" + minDifficulty + " - " + maxDifficulty + "): ");
        int difficulty;

        // Validate user input for difficulty
        while (true) {
            try {
                difficulty = Integer.parseInt(scanner.nextLine().trim());
                if (difficulty >= minDifficulty && difficulty <= maxDifficulty) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a number between " +
                            minDifficulty + " and " + maxDifficulty + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number between " +
                        minDifficulty + " and " + maxDifficulty + ":");
            }
        }

        // Request maximum team size
        int maxTeamSizeAllowed = gameConfiguration.getMaxSizeTeamAllowed();
        System.out.println("Enter the maximum team size (integer between 1 and " + maxTeamSizeAllowed + "): ");
        int maxSize;

        // Validate user input for team size
        while (true) {
            try {
                maxSize = Integer.parseInt(scanner.nextLine().trim());
                if (maxSize > 0 && maxSize <= maxTeamSizeAllowed) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter a positive integer inferior to " + maxTeamSizeAllowed + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }

        // Apply settings to game configuration
        gameConfiguration.setDifficulty(difficulty);
        gameConfiguration.setMaxSizeTeam(maxSize);

        // Display configuration summary
        System.out.println("Game configured successfully!");
        System.out.println("Difficulty: " + gameConfiguration.getDifficulty());
        System.out.println("Max Team Size: " + gameConfiguration.getMaxSizeTeam());
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
    public void addTeam(String name, String type) {
        switch (type) {
            case "Warrior":
                teams.put(name, teamBank.createClone("Combat"));
                break;
            case "Wizard":
                teams.put(name, teamBank.createClone("Magic"));
                break;
            case "Balanced":
                teams.put(name, teamBank.createClone("Balanced"));
                break;
            default:
                teams.put(name, createTeam(name));
        }
    }

    /**
     * Creates a custom team based on user input for each character's name and type.
     *
     * @param name The name of the team.
     * @return The created Team object.
     */
    public Team createTeam(String name) {
        //Scanner scanner = new Scanner(System.in); // Scanner for user input
        GameConfiguration config = GameConfiguration.getGameConfiguration();
        int maxSize = config.getMaxSizeTeam(); // Maximum allowed team size

        // Initialize a TeamBuilder to construct the team
        TeamBuilder teamBuilder = new TeamBuilder(name);

        // Loop to add players based on user input
        for (int i = 0; i < maxSize; i++) {
            System.out.println("Enter name for player " + (i + 1) + ": ");
            String playerName = scanner.nextLine().trim();

            System.out.println("Enter type for player " + (i + 1) + " (W for Wizard, Wa for Warrior, H for Healer): ");
            String playerType = scanner.nextLine().trim();

            // Create the character based on the type provided
            GameCharacter character = switch (playerType.toUpperCase()) {
                case "W" -> characterBank.createClone("Wizard");
                case "WA" -> characterBank.createClone("Warrior");
                case "H" -> characterBank.createClone("Healer");
                default -> {
                    System.out.println("Invalid type. Defaulting to Warrior.");
                    yield new Warrior(playerName);
                }
            };

            teamBuilder.addPlayer(character);
        }
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
            // Cleans every Character in the Team
            for (CharacterInterface character : team.getPlayers()) {
                GameCharacter inner = unwrapCharacter(character);
                inner.detachAllObservers();                         // Detach all Observers from a GameCharacter
            }

            // Deletes the Team from the list
            teams.remove(name);

            System.out.println("Team " + name + " has been removed.");
        } else {
            System.out.println("Team " + name + " does not exist.");
        }
    }



    /**
     * Handles a 1v1 attack between characters in two teams.
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
                DamageVisitor damageDealer = new DamageVisitor();
                damageDealer.setDamage(actualTakeDamage);
                ((Visitable) defender).accept(damageDealer);
                System.out.println("Character " + character1 + " attacked " + character2 + " dealing " + actualTakeDamage + " damage.");
            }
            else {
                System.out.println("Character " + character1 + " or Character " + character2 + " does not exist.");
            }
        }
        else {
            System.out.println("Team " + team1 + " or Team " + team2 + " does not exist.");
        }
    }

    /**
     * Heals all members of a specified team.
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
            System.out.println("Team " + teamName + " does not exist.");
        }
    }

    /**
     * Buffs all members of a specified team by a given factor.
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
            System.out.println("Team " + teamName + " does not exist.");
        }
    }

    /**
     * Adds armor to a character by wrapping it with an ArmoredDecorator.
     */
    public void addArmor(String teamName, String characterName, double armorFactor) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                CharacterInterface armoredCharacter = new ArmoredDecorator(character, armorFactor);
                team.changePlayer(character, armoredCharacter);
            }
            else {
                System.out.println("Character " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("Team " + teamName + " does not exist.");
        }
    }

    /**
     * Makes a character invincible by wrapping it with an InvincibleDecorator.
     */
    public void makeInvincible(String teamName, String characterName) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                CharacterInterface invincibleCharacter = new InvincibleDecorator(character);
                team.changePlayer(character, invincibleCharacter);
            }
            else {
                System.out.println("Character " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("Team " + teamName + " does not exist.");
        }
    }

    /**
     * Removes the last decorator from a character if it has one.
     */
    public void removeDecorator(String teamName, String characterName) {
        Team team = teams.get(teamName);
        if (team != null){
            CharacterInterface character = team.getPlayer(characterName);
            if (character != null){
                if (character instanceof CharacterDecorator){
                    team.changePlayer(character, ((CharacterDecorator) character).getInnerCharacter());
                }
                else {
                    System.out.println("Character " + characterName + " has neither Armor nor is Invincible.");
                }
            }
            else {
                System.out.println("Character " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("Team " + teamName + " does not exist.");
        }
    }

    /**
     * Removes all decorators from a character, restoring it to its original state.
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
                System.out.println("Character " + characterName + " does not exist.");
            }
        }
        else {
            System.out.println("Team " + teamName + " does not exist.");
        }
    }

    // Getters

    public Map<String, Team> getTeams() {
        return teams;
    }
}
