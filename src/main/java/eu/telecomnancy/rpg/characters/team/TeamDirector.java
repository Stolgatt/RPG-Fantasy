package eu.telecomnancy.rpg.characters.team;

import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;

/**
 * A director class to create pre-configured teams using the TeamBuilder.
 * Implements the Director role in the Builder pattern.
 */
public class TeamDirector {

    /**
     * Constructs a balanced team consisting of a Warrior, Wizard, and Healer.
     *
     * @return a pre-configured balanced Team object.
     */
    public Team constructBalancedTeam() {
        return new TeamBuilder("Balanced Team")
                .addPlayer(new Warrior("Strong Warrior"))
                .addPlayer(new Wizard("Smart Wizard"))
                .addPlayer(new Healer("Kind Healer"))
                .build();
    }

    /**
     * Constructs a team composed entirely of Warriors.
     *
     * @return a pre-configured combat Team object.
     */
    public Team constructCombatTeam() {
        return new TeamBuilder("Combat Team")
                .addPlayer(new Warrior("Fighter 1"))
                .addPlayer(new Warrior("Fighter 2"))
                .addPlayer(new Warrior("Fighter 3"))
                .build();
    }

    /**
     * Constructs a team composed entirely of Wizards.
     *
     * @return a pre-configured wizard Team object.
     */
    public Team constructWizardTeam() {
        return new TeamBuilder("Magic Team")
                .addPlayer(new Wizard("Wizard 1"))
                .addPlayer(new Wizard("Wizard 2"))
                .addPlayer(new Wizard("Wizard 3"))
                .build();
    }
}
