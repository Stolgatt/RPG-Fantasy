package eu.telecomnancy.rpg.characters;

public class WizardCreator implements CharacterCreator {

    @Override
    public GameCharacter createCharacter(String name) {
        return new Wizard(name);
    }
}
