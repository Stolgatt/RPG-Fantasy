package eu.telecomnancy.rpg.characters;

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
