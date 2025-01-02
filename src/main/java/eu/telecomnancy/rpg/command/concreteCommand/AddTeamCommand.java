package eu.telecomnancy.rpg.command.concreteCommand;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.command.Command;
import eu.telecomnancy.rpg.command.CommandParameters;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class AddTeamCommand implements Command {
    String history = null;
    List<String> basicTeam = new ArrayList<>();

    public AddTeamCommand() {
        basicTeam.add("Balanced");
        basicTeam.add("Warrior");
        basicTeam.add("Wizard");
    }

    @Override
    public void execute(GameFacade gameFacade, CommandParameters parameters) {
        String team = parameters.getString("team");
        String type = parameters.getString("type");

        // Basic team
        if (basicTeam.contains(type)) {
            history = "Add team : " + team + " of type " + type;
            gameFacade.addTeam(team, type, null);
        }
        // Custom team
        else {
            history = "Add team : " + team + " of type custom.";
            GameConfiguration gameConfiguration = GameConfiguration.getGameConfiguration();
            int maxSize = gameConfiguration.getMaxSizeTeam();

            List<Pair<String, String>> customCharacters = new ArrayList<>();

            for (int i = 1; i <= maxSize; i++) {
                String charName = parameters.getString("name" + i);
                String charType = parameters.getString("type" + i);

                if (charName != null && !charName.isEmpty() && charType != null && !charType.isEmpty()) {
                    customCharacters.add(new Pair<>(charName, charType));
                }
            }
            gameFacade.addTeam(team, "custom", customCharacters);
        }
    }


    @Override
    public void undo(GameFacade gameFacade, CommandParameters parameters) {
        System.out.println("Creation of a team cannot be undone, please remove it manually");
    }

    public String toString() {
        return history;
    }
}
