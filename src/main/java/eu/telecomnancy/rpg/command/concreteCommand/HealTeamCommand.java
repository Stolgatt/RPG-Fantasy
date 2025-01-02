package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class HealTeamCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters params) {
        String team = params.getString("team");
        int healAmount = 0;
        try {
            healAmount = Integer.parseInt(params.getString("healAmount"));
        }
        catch (NumberFormatException e) {
            System.err.println("\nThe 'HealTeam' command did not receive the correct parameters. Please provide the name of the team involved " +
                    "and the heal amount in integer format. Error message: " + e.getMessage());
            return;
        }

        history = "Heal of a team : " + team + ". Heal Amount : " + healAmount;
        gameFacade.healTeam(team, healAmount);
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
