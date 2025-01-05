package eu.telecomnancy.rpg.App;

import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.command.*;
import eu.telecomnancy.rpg.command.concreteCommand.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameClient {
    private static ArrayList<String> commandHistory = new ArrayList<>();
    private static int historyIndex = -1;

    public static void main(String[] args) {
        GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
        GameFacade gameFacade = new GameFacade();
        GameInvoker invoker = new GameInvoker(gameFacade);
        CommandParameters cp = new CommandParameters();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter commands (type 'exit' to quit, 'help' to show the manual):");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            String[] tokens;
            String command = input.split(" ")[0].toUpperCase();

            switch (command) {
                case "EXIT":
                    // Exit the game
                    System.out.println("Exiting the game.");
                    return;
                case "HELP":
                    // Print help
                    invoker.executeCommand(new PrintHelpCommand(), null);
                    break;
                case "COMMANDSUMMARY":
                    // Print Command Summary
                    invoker.executeCommand(new PrintCommandSummaryCommand(), null);
                    break;
                case "PREV":
                    // Handle previous command navigation
                    handlePrevCommand();
                    break;
                case "CLEAR":
                    // Clear the console output
                    clearConsole();
                    break;
                case "UNDO":
                    // Undo the previous command
                    invoker.undoLastCommand();
                    break;
                // Game Commands
                case "CREATETEAM":
                    // Handle the createTeam command
                    handleCreateTeam(input, invoker, cp);
                    break;
                case "DELETETEAM":
                    // Deletes the required team
                    handleDeleteTeam(input, invoker, cp);
                    break;
                case "PRINTTEAM":
                    // Prints the required team
                    handlePrintTeam(input, invoker, cp);
                    break;
                case "CHANGESETTINGS":
                    // Modify the settings
                    handleChangeSettings(input, invoker, cp);
                    break;
                case "SHOWSETTINGS":
                    // Show current settings
                    handleShowSettings(input, invoker, cp);
                    break;
                case "CHANGETEAMNAME":
                    // Change the name of a team
                    handleChangeTeamName(input, invoker, cp);
                    break;
                case "CHANGECHARACTERNAME":
                    // Change the name of a character
                    handleChangeCharacterName(input, invoker, cp);
                    break;
                case "BUFFTEAM":
                    // Buff all characters in a team
                    handleBuffTeam(input, invoker, cp);
                    break;
                case "BUFFCHARACTER":
                    // Buff a character in a team
                    handleBuffCharacter(input, invoker, cp);
                    break;
                case "EQUIPARMOR":
                    // Equip a character with an armor
                    handleEquipArmor(input, invoker, cp);
                    break;
                case "REMOVEARMOR":
                    // Unequipped armor from a character
                    handleRemoveArmor(input, invoker, cp);
                    break;
                case "MAKEINVINCIBLE":
                    // make a character invincible
                    handleMakeInvincible(input, invoker, cp);
                    break;
                case "REMOVEINVINCIBILITY":
                    // remove invincibility from a character
                    handleRemoveInvincibility(input, invoker, cp);
                    break;
                case "CHANGESTRATEGY":
                    // change the combat strategy of a character
                    handleChangeStrategy(input, invoker, cp);
                    break;
                case "ATTACK":
                    // Organise an attack
                    handleAttack(input, invoker, cp);
                    break;
                default:
                    System.out.println("Unknown command. Please try again.");
                    break;
            }

            // Save the current command to history
            addToHistory(input);
        }
    }

    // Handle previous command navigation (prev)
    private static void handlePrevCommand() {
        if (historyIndex > 0) {
            historyIndex--;
            String input = commandHistory.get(historyIndex);
            System.out.println(input);
        } else {
            System.out.println("No previous commands.");
        }
    }

    // Add the input command to history
    public static void addToHistory(String input) {
        if (!input.trim().isEmpty()) {
            commandHistory.add(input);
            historyIndex = commandHistory.size();
        }
    }

    // Clear console output
    private static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.println("Enter commands (type 'exit' to quit, 'help' to show the manual):");
    }

    public static ArrayList<String> parseArguments(String input) {
        ArrayList<String> args = new ArrayList<>();

        Matcher matcher = Pattern.compile("\"([^\"]+)\"|(\\S+)").matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Capture un argument entre guillemets
                args.add(matcher.group(1));
            } else {
                // Capture un mot individuel
                args.add(matcher.group(2));
            }
        }
        return args;
    }

    // Handle the "createTeam" command
    private static void handleCreateTeam(String input, GameInvoker invoker, CommandParameters cp) {
        ArrayList<String> tokens = parseArguments(input);

        // Validation de la commande
        if ((tokens.size() - 3) % 2 != 0) {
            System.out.println("Invalid createTeam command format. Example: createTeam \"Team Name\" Char1 Type1 Char2 Type2");
            return;
        }

        cp.clean();
        // Parse team name
        String teamName = tokens.get(1);
        cp.set("team", teamName);

        // Parse team type
        String teamType = tokens.get(2);
        cp.set("type", teamType);

        // Parse characters and their types
        for (int i = 3; i < tokens.size(); i += 2) {
            String charName = tokens.get(i);
            String charType = tokens.get(i + 1);
            cp.set("name" + ((i - 2) / 2 + 1), charName);
            cp.set("type" + ((i - 2) / 2 + 1), charType);
        }

        // Execute AddTeamCommand
        AddTeamCommand addTeamCommand = new AddTeamCommand();
        invoker.executeCommand(addTeamCommand, cp);
    }

    private static void handleDeleteTeam(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 2) {
            System.out.println("Invalid deleteTeam command format. Example: deleteTeam \"Team Name\"");
            return;
        }

        cp.clean();
        // Parse team name
        String teamName = tokens.get(1);
        cp.set("team", teamName);

        // Execute RemoveTeamCommand
        RemoveTeamCommand removeTeamCommand = new RemoveTeamCommand();
        invoker.executeCommand(removeTeamCommand, cp);
    }


    private static void handlePrintTeam(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 2) {
            System.out.println("Invalid printTeam command format. Example: printTeam \"Team Name\"");
            return;
        }

        cp.clean();
        // Parse team name
        String teamName = tokens.get(1);
        cp.set("team", teamName);

        // Execute PrintTeamCommand
        PrintTeamCommand printTeamCommand = new PrintTeamCommand();
        invoker.executeCommand(printTeamCommand, cp);
        System.out.flush();
    }

    private static void handleShowSettings(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.isEmpty()) {
            System.out.println("Invalid showSettings command format. Example: showSettings");
            return;
        }

        cp.clean();
        PrintSettingsCommand printSettingsCommand = new PrintSettingsCommand();
        invoker.executeCommand(printSettingsCommand, cp);
    }

    private static void handleChangeSettings(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 3) {
            System.out.println("Invalid changeSettings command format. Example: changeSettings <difficultyLevel> <sizeOfATeam>");
            return;
        }

        cp.clean();
        // parse difficulty and size of a team
        String difficultyLevel = tokens.get(1);
        String sizeOfATeam = tokens.get(2);
        cp.set("difficulty", difficultyLevel);
        cp.set("maxSizeTeam", sizeOfATeam);

        ModifySettingsCommand modifySettingsCommand = new ModifySettingsCommand();
        invoker.executeCommand(modifySettingsCommand, cp);
    }

    private static void handleChangeTeamName(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 3) {
            System.out.println("Invalid changeTeamName command format. Example: changeTeamName \"<teamName>\" \"<newTeamName>\"");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("newName", tokens.get(2));
        ChangeTeamNameCommand changeTeamNameCommand = new ChangeTeamNameCommand();
        invoker.executeCommand(changeTeamNameCommand, cp);
    }

    private static void handleChangeCharacterName(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 4) {
            System.out.println("Invalid changeCharacterName command format. Example: changeCharacterName \"<teamName>\" \"<character_name>\" \"<newCharacterName>\"");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        cp.set("newName", tokens.get(3));
        ChangeCharacterNameCommand changeCharacterNameCommand = new ChangeCharacterNameCommand();
        invoker.executeCommand(changeCharacterNameCommand, cp);
    }

    private static void handleBuffTeam(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 3) {
            System.out.println("Invalid buffTeam command format. Example: buffTeam \"<team_name>\" <buff_amount>");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("buffAmount", tokens.get(2));
        BuffTeamCommand buffTeamCommand = new BuffTeamCommand();
        invoker.executeCommand(buffTeamCommand, cp);
    }

    private static void handleBuffCharacter(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 4) {
            System.out.println("Invalid buffCharacter command format. Example: buffCharacter \"<team_name>\" \"<character_name>\" <buff_amount>");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        cp.set("buffAmount", tokens.get(3));
        BuffCommand buffCommand = new BuffCommand();
        invoker.executeCommand(buffCommand, cp);
    }

    private static void handleHealTeam(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 3) {
            System.out.println("Invalid healTeam command format. Example: healTeam \"<team_name>\" <heal_amount>");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("healAmount", tokens.get(2));
        HealTeamCommand healTeamCommand = new HealTeamCommand();
        invoker.executeCommand(healTeamCommand, cp);
    }

    private static void handleHealCharacter(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 4) {
            System.out.println("Invalid healCharacter command format. Example: healCharacter \"<team_name>\" \"<character_name>\" <heal_amount>");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        cp.set("healAmount", tokens.get(3));
        HealCommand healCommand = new HealCommand();
        invoker.executeCommand(healCommand, cp);
    }

    private static void handleEquipArmor(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 4) {
            System.out.println("Invalid equipArmor command format. Example: equipArmor \"<team_name>\" \"<character_name>\" <armor_Amount>");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        cp.set("armorAmount", tokens.get(3));
        AddArmorCommand addArmorCommand = new AddArmorCommand();
        invoker.executeCommand(addArmorCommand, cp);
    }

    private static void handleRemoveArmor(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 3) {
            System.out.println("Invalid removeArmor command format. Example: removeArmor \"<team_name>\" \"<character_name>\"");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        RemoveArmorOrInvincibilityCommand removeArmorOrInvincibilityCommand = new RemoveArmorOrInvincibilityCommand();
        invoker.executeCommand(removeArmorOrInvincibilityCommand, cp);
    }

    private static void handleMakeInvincible(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 3) {
            System.out.println("Invalid makeInvincible command format. Example: makeInvincible \"<team_name>\" \"<character_name>\"");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        AddInvincibilityCommand addInvincibilityCommand = new AddInvincibilityCommand();
        invoker.executeCommand(addInvincibilityCommand, cp);
    }

    private static void handleRemoveInvincibility(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 3) {
            System.out.println("Invalid removeInvincibility command format. Example: removeInvincibility \"<team_name>\" \"<character_name>\"");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        RemoveArmorOrInvincibilityCommand removeArmorOrInvincibilityCommand = new RemoveArmorOrInvincibilityCommand();
        invoker.executeCommand(removeArmorOrInvincibilityCommand, cp);
    }

    private static void handleChangeStrategy(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 4) {
            System.out.println("Invalid changeStrategy command format. Example: changeStrategy \"<team_name>\" \"<character_name>\" \"<strategy_name>\"");
            return;
        }

        cp.clean();
        cp.set("team", tokens.get(1));
        cp.set("character", tokens.get(2));
        cp.set("strategy", tokens.get(3));
        ChangeStrategyCommand changeStrategyCommand = new ChangeStrategyCommand();
        invoker.executeCommand(changeStrategyCommand, cp);
    }

    private static void handleAttack(String input, GameInvoker invoker, CommandParameters cp){
        ArrayList<String> tokens = parseArguments(input);

        if (tokens.size() < 6) {
            System.out.println("Invalid attack command format. Example: attack \"<attacker_team_name>\" \"<attacker_name>\" \"<defender_team_name>\" \"defender_name\" <damage_amount>");
            return;
        }

        cp.clean();
        cp.set("team1", tokens.get(1));
        cp.set("character1", tokens.get(2));
        cp.set("team2", tokens.get(3));
        cp.set("character2", tokens.get(4));
        cp.set("damageAmount", tokens.get(5));
        AttackCommand attackCommand = new AttackCommand();
        invoker.executeCommand(attackCommand, cp);
    }
}
