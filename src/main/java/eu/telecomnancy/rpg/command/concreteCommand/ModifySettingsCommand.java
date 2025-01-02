package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class ModifySettingsCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
        int difficulty = 0;
        int maxSizeTeam = 0;
        try {
            difficulty = Integer.parseInt(parameters.getString("difficulty"));
            maxSizeTeam = Integer.parseInt(parameters.getString("maxSizeTeam"));
        }
        catch (NumberFormatException e) {
            System.err.println("\nThe 'ModifySettings' command did not receive the correct parameters. Please provide the difficulty level (integer format) and " +
                    "the desired team size (integer format). Error message: " + e.getMessage());
            return;
        }

        gameConfiguration.setDifficulty(difficulty);
        gameConfiguration.setMaxSizeTeam(maxSizeTeam);
        history = "Settings Modified : difficulty set to " + difficulty + " and maxSizeTeam set to " + maxSizeTeam;
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {

    }

    @Override
    public String toString() {
        return history;
    }
}
