package eu.telecomnancy.rpg.command;

import eu.telecomnancy.rpg.App.GameFacade;

public class UndoneCommand implements Command {
    Command undoneCommand;

    public UndoneCommand(Command undoneCommand) {
        this.undoneCommand = undoneCommand;
    }

    @Override
    public void execute(GameFacade gameFacade, CommandParameters commandParameters) {
        System.out.println("This is an UndoneCommand linked to " + undoneCommand.toString());
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters commandParameters) {
        System.out.println("This is an UndoneCommand linked to " + undoneCommand.toString());
    }

    @Override
    public String toString() {
        return "Undone Command linked to " + '"' + undoneCommand.toString() + '"';
    }
}
