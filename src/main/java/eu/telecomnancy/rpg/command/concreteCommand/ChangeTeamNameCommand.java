package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class ChangeTeamNameCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String newName = parameters.getString("newName");

        history = "The team '" + team + "' changed its name to '" + newName + "'.";
        gameFacade.renameTeam(team, newName);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {

    }

    @Override
    public String toString() {
        return history;
    }
}
