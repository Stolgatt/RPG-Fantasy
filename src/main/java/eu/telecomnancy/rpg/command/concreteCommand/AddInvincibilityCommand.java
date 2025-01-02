package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class AddInvincibilityCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");

        history = "Add Invincibility to " + character + " from team " + team;
        gameFacade.makeInvincible(team, character);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");

        gameFacade.removeDecorator(team, character);
    }

    @Override
    public String toString(){
        return history;
    }
}
