package eu.telecomnancy.rpg.characters.factory;

/**
 * Concrete creator class for creating Healer characters.
 * Implements the `CharacterCreator` interface as part of the
 * Factory Method pattern. This class encapsulates the logic for
 * instantiating `Healer` objects, separating the creation logic
 * from the client code.
 */
public class HealerCreator implements CharacterCreator {

     /**
     * Creates a new Healer character with the specified name.
     *
     * @param name the name of the Healer to create.
     * @return a new instance of a Healer.
     */
     @Override
    public GameCharacter createCharacter(String name) {
        return new Healer(name);
    }
}
