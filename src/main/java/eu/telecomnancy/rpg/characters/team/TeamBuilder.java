package eu.telecomnancy.rpg.characters.team;

import eu.telecomnancy.rpg.characters.factory.GameCharacter;

/**
 * A builder class for creating and customizing teams.
 * Allows step-by-step addition of players before finalizing the team.
 * Implements the Builder pattern.
 */
public class TeamBuilder {
    private Team team;

    /**
     * Initializes a new team builder with a team name.
     *
     * @param name the name of the team to be built.
     */
    public TeamBuilder(String name) {
        this.team = new Team(name);
    }

    /**
     * Adds a player to the team being built.
     *
     * @param character the GameCharacter to add.
     * @return the current TeamBuilder instance (for method chaining).
     */
    public TeamBuilder addPlayer(GameCharacter character) {
        team.addPlayer(character);
        return this;
    }

    /**
     * Finalizes the team construction and returns the built team.
     *
     * @return the constructed Team object.
     */
    public Team build() {
        return team;
    }
}
