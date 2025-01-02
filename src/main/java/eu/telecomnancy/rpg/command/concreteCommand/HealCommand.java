package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class HealCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters params) {
        String team = params.getString("team");
        String character = params.getString("character");
        int healAmount = 0;
        try {
            healAmount = Integer.parseInt(params.getString("healAmount"));
        }
        catch (NumberFormatException e) {
            System.err.println("\nThe 'Heal' command did not receive the correct parameters. Please provide the name of the team, the name of the character involved, " +
                    "and the heal amount in integer format. Error message: " + e.getMessage());
            return;
        }

        history = "Heal of a character : " + character + " from team " + team + ". Heal Amount : " + healAmount;
        gameFacade.healCharacter(team, character, healAmount);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters params) {
        System.out.println("\nHealing cannot be undone.");
    }

    @Override
    public String toString(){
        return history;
    }
}
