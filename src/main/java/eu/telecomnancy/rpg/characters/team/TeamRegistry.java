package eu.telecomnancy.rpg.characters.team;

import java.util.HashMap;
import java.util.Map;

/**
 * A registry class for storing and cloning team prototypes.
 * Implements the Prototype Registry pattern, allowing efficient creation of new teams based on existing templates.
 */
public class TeamRegistry {
    private Map<String, Team> prototypes = new HashMap<>();

    /**
     * Registers a team prototype with a specified key.
     *
     * @param key the key to associate with the prototype.
     * @param prototype the team prototype to register.
     */
    public void registerPrototype(String key, Team prototype) {
        prototypes.put(key, prototype);
    }

    /**
     * Creates a deep clone of a registered team prototype.
     *
     * @param key the key associated with the prototype.
     * @return a new Team object cloned from the prototype.
     * @throws IllegalArgumentException if no prototype is found for the given key.
     */
    public Team createClone(String key) {
        Team prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Team not found for key: " + key);
        }
        return prototype.clone();
    }
}
