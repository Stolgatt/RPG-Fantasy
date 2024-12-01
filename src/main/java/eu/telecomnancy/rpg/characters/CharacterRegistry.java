package eu.telecomnancy.rpg.characters;

import java.util.HashMap;
import java.util.Map;

public class CharacterRegistry {
    private Map<String, GameCharacter> prototypes = new HashMap<>();

    public void registerPrototype(String key, GameCharacter prototype) {
        prototypes.put(key, prototype);
    }

    public GameCharacter createClone(String key) {
        GameCharacter prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Prototype not found for key: " + key);
        }
        return prototype.clone();
    }
}
