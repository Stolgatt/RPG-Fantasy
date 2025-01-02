package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class PrintTeamCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");

        history = "Print a team : " + team;
        gameFacade.printTeam(team);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        System.out.println("\nCannot undo a print.");
    }

    @Override
    public String toString(){
        return history;
    }
}
