package eu.telecomnancy.rpg.command;

public class CommandEntry {
    private final Command command;
    private final CommandParameters parameters;

    public CommandEntry(Command command, CommandParameters parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public Command getCommand() {
        return command;
    }

    public CommandParameters getParameters() {
        return parameters;
    }
}
