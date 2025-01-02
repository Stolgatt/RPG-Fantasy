package eu.telecomnancy.rpg.command;

import eu.telecomnancy.rpg.App.GameFacade;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GameInvoker {
    private final GameFacade gameFacade;
    private final Stack<CommandEntry> commandHistory = new Stack<>();
    private final Stack<Command> actionHistory = new Stack<>();
    private final Queue<CommandEntry> awaitingCommands = new LinkedList<>();


    public GameInvoker(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
    }


    public void executeCommand(Command command, CommandParameters commandParameters) {
        command.execute(gameFacade, commandParameters);
        CommandEntry commandEntry = new CommandEntry(command, commandParameters);
        commandHistory.push(commandEntry);
        actionHistory.push(command);
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            CommandEntry lastCommandEntry = commandHistory.pop();
            Command lastCommand = lastCommandEntry.getCommand();
            CommandParameters lastCommandParameters = lastCommandEntry.getParameters();
            lastCommand.undo(gameFacade, lastCommandParameters);

            UndoneCommand undoneCommand = new UndoneCommand(lastCommand);
            actionHistory.push(undoneCommand);
        }
    }

    public void addCommandToSequence(Command command, CommandParameters commandParameters) {
        CommandEntry commandEntry = new CommandEntry(command, commandParameters);
        awaitingCommands.add(commandEntry);
    }


    public void executeCommandSequence() {
        while (!awaitingCommands.isEmpty()) {
            CommandEntry current = awaitingCommands.poll();
            executeCommand(current.getCommand(), current.getParameters());
        }
    }

    public void printActionHistory() {
        System.out.println("\n\nHistory of actions performed. From the last to the first :\n");
        while (!actionHistory.isEmpty()) {
            Command command = actionHistory.pop();
            System.out.println(command.toString());
        }
    }
}
