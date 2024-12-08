package eu.telecomnancy.rpg.characters.factory;

/**
 * Interface for creating game characters.
 * Defines a factory method `createCharacter` that must be implemented
 * by concrete creator classes.
 */
public interface CharacterCreator {

    /**
     * Creates a new GameCharacter with the specified name.
     *
     * @param name the name of the character to create.
     * @return a new instance of a GameCharacter.
     */
    public GameCharacter createCharacter(String name);
}
