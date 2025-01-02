package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.characters.strategy.CombatStrategy;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class ChangeStrategyCommand implements Command {
    String history = null;
    CombatStrategy previousStrategy = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");
        String strategy = parameters.getString("strategy");

        history = "The character " + character + " from team " + team + " has changed their strategy to " + strategy + ".";
        previousStrategy = gameFacade.changeStrategy(team, character, strategy);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");
        if (previousStrategy != null) {
            gameFacade.changeStrategy(team, character, previousStrategy.getName());
        }
    }

    @Override
    public String toString() {
        return history;
    }
}

