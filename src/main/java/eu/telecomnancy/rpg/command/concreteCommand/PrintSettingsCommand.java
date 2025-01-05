package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class PrintSettingsCommand implements Command {

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
        System.out.println(gameConfiguration.toString());
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        // Do nothing
    }

    @Override
    public String toString() {
        return "Settings printed.";
    }
}
