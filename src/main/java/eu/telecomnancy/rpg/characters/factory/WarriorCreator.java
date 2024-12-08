package eu.telecomnancy.rpg.characters.factory;

/**
 * Concrete creator class for creating Warrior characters.
 * Implements the `CharacterCreator` interface as part of the
 * Factory Method pattern. This class encapsulates the logic for
 * instantiating `Warrior` objects, separating the creation logic
 * from the client code.
 */
public class WarriorCreator implements CharacterCreator {

    /**
     * Creates a new Warrior character with the specified name.
     *
     * @param name the name of the Warrior to create.
     * @return a new instance of a Warrior.
     */
    @Override
    public GameCharacter createCharacter(String name) {
        return new Warrior(name);
    }
}
