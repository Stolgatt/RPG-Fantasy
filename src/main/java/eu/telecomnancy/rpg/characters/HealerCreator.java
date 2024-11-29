package eu.telecomnancy.rpg.characters;

public class HealerCreator implements CharacterCreator {

    @Override
    public GameCharacter createCharacter(String name) {
        return new Healer(name);
    }
}
