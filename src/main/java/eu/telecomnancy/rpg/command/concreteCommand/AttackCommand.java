package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class AttackCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String char1 = parameters.getString("character1");
        String char2 = parameters.getString("character2");
        String team1 = parameters.getString("team1");
        String team2 = parameters.getString("team2");
        double damageAmount = 0;
        try {
            damageAmount = Double.parseDouble(parameters.getString("damageAmount"));
        }
        catch (Exception e) {
            System.err.println("\nThe 'Attack' command did not receive the correct parameters. Please provide the names of the two teams and the names of the two characters involved " +
                    "and the damage amount in integer format. Error message: " + e.getMessage());
            return;
        }

        history = "Attack: " + char1 + " (" + team1 + ", attacker) vs " + char2 + " (" + team2 + ", defender), Damage = " + damageAmount;
        gameFacade.attack(team1, char1, team2, char2, damageAmount);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        System.out.println("\nAn attack cannot be undone.");
    }

    @Override
    public String toString(){
        return history;
    }
}
