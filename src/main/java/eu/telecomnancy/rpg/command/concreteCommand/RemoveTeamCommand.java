package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class RemoveTeamCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");

        history = "Remove team : " + team;
        gameFacade.removeTeam(team);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        System.out.println("Any removed team is gone forever.");
    }

    @Override
    public String toString() {
        return history;
    }
}
