package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class BuffTeamCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters params) {
        String team = params.getString("team");
        int buffAmount = 0;
        try {
            buffAmount = Integer.parseInt(params.getString("buffAmount"));
        }
        catch (NumberFormatException e) {
            System.err.println("\nThe 'BuffTeam' command did not receive the correct parameters. Please provide the name of the team involved " +
                    "and the buff amount in integer format. Error message: " + e.getMessage());
            return;
        }

        history = "Buff of a team : " + team + ". Buff Amount : " + buffAmount;
        gameFacade.buffTeam(team, buffAmount);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters params) {
        params.replace("buffAmount", "-"+params.getString("buffAmount"));
        execute(gameFacade, params);
    }

    @Override
    public String toString(){
        return history;
    }
}
