package eu.telecomnancy.rpg.characters.factory;

/**
 * Concrete creator class for creating Wizard characters.
 * Implements the `CharacterCreator` interface as part of the
 * Factory Method pattern. This class encapsulates the logic for
 * instantiating `Wizard` objects, separating the creation logic
 * from the client code.
 */
public class WizardCreator implements CharacterCreator {

    /**
     * Creates a new Wizard character with the specified name.
     *
     * @param name the name of the Wizard to create.
     * @return a new instance of a Wizard.
     */
    @Override
    public GameCharacter createCharacter(String name) {
        return new Wizard(name);
    }
}
