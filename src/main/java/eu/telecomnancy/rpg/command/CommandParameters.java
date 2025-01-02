package eu.telecomnancy.rpg.command;

import eu.telecomnancy.rpg.characters.factory.CharacterInterface;
import eu.telecomnancy.rpg.characters.team.Team;

import java.util.HashMap;
import java.util.Map;

public class CommandParameters {
    private final Map<String, Object> parameters = new HashMap<>();

    public void set(String key, Object value) {
        parameters.put(key, value);
    }

    public void replace(String key, Object value) {
        if (parameters.containsKey(key)){
            parameters.remove(key);
            parameters.put(key, value);
        }
        else {
            parameters.put(key, value);
        }
    }

    public Object get(String key) {
        return parameters.get(key);
    }

    public String getString(String key) {
        return (String) parameters.get(key);
    }

    public CharacterInterface getCharacter(String key) {
        return (CharacterInterface) parameters.get(key);
    }

    public Team getTeam(String key){
        return (Team) parameters.get(key);
    }

    public void clean(){
        parameters.clear();
    }
}
