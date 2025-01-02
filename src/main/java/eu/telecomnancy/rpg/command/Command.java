package eu.telecomnancy.rpg.command;

import eu.telecomnancy.rpg.App.GameFacade;

public interface Command {

    void execute(GameFacade gameFacade, CommandParameters parameters);

    void undo(GameFacade gameFacade, CommandParameters parameters);

    String toString();
}
