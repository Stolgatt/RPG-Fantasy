package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class ChangeCharacterNameCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");
        String newName = parameters.getString("newName");

        history = "The character '" + character + "' from team '" + team + "' changed their name to '" + newName + "'.";
        gameFacade.renameCharacter(team, character, newName);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        // do nothing
    }

    @Override
    public String toString() {
        return history;
    }
}
