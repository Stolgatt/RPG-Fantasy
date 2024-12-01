package eu.telecomnancy.rpg.characters;

import java.util.HashMap;
import java.util.Map;

public class TeamRegistry {
    private Map<String, Team> prototypes = new HashMap<>();

    public void registerPrototype(String key, Team prototype) {
        prototypes.put(key, prototype);
    }

    public Team createClone(String key) {
        Team prototype = prototypes.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("Team not found for key: " + key);
        }
        return prototype.clone();
    }
}
