package eu.telecomnancy.rpg.characters.team;

import eu.telecomnancy.rpg.characters.GameCharacter;

public class TeamBuilder {
    private Team team;

    public TeamBuilder(String name) {
        this.team = new Team(name);
    }

    public TeamBuilder addPlayer(GameCharacter character) {
        team.addPlayer(character);
        return this;
    }

    public Team build() {
        return team;
    }
}
