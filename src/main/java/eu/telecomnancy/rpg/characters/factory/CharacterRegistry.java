package eu.telecomnancy.rpg.characters.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry for managing and cloning character prototypes.
 * Implements the Prototype pattern by storing and reusing preconfigured instances of GameCharacter.
 * This class is useful for creating new characters with pre-defined attributes.
 */
public class CharacterRegistry {
    private Map<String, GameCharacter> prototypes = new HashMap<>();

    /**
     * Registers a prototype in the registry.
     *
     * @param key a unique identifier for the prototype.
     * @param prototype the GameCharacter instance to be stored.
     */
    public void registerPrototype(String key, GameCharacter prototype) {
        prototypes.put(key, prototype);
    }

    /**
     * Creates a clone of a registered prototype.
     *
     * @param key the unique identifier of the prototype to clone.
     * @return a new GameCharacter instance that is a copy of the prototype.
     * @throws IllegalArgumentException if no prototype is found for the given key.
     */
    public GameCharacter createClone(String key) {
        GameCharacter prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Character not found for key: " + key);
        }
        return prototype.clone();
    }
}
