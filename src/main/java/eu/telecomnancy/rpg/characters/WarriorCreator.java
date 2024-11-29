package eu.telecomnancy.rpg.characters;

public class WarriorCreator implements CharacterCreator {

    @Override
    public GameCharacter createCharacter(String name) {
        return new Warrior(name);
    }
}
