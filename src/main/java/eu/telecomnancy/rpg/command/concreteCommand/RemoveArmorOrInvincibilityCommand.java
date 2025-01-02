package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class RemoveArmorOrInvincibilityCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");

        history = "Remove an armor or invincibility of " + character + " from " + team;
        gameFacade.removeDecorator(team, character);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        System.out.println("\nAll armor removed are lost. Removing an armor cannot be undone.");
    }

    @Override
    public String toString(){
        return history;
    }
}
