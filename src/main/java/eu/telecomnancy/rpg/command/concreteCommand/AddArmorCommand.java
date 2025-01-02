package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;

public class AddArmorCommand implements Command {
    String history = null;

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");
        double armor = 0;
        try {
            armor = Double.parseDouble(parameters.getString("armorAmount"));
        }
        catch (Exception e) {
            System.err.println("\nThe 'AddArmor' command did not receive the correct parameters. Please provide the name of the team, the name of the character involved" +
                    "and the armor amount in double format. Error message: " + e.getMessage());
            return;
        }

        history = "Add armor : " + team + " " + character + " " + armor;
        gameFacade.addArmor(team, character, armor);
    }

    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String character = parameters.getString("character");

        gameFacade.removeDecorator(team, character);

    }

    @Override
    public String toString(){
        return history;
    }
}
