package eu.telecomnancy.rpg.characters.team;

import eu.telecomnancy.rpg.characters.decorator.CharacterDecorator;
import eu.telecomnancy.rpg.characters.factory.CharacterInterface;
import eu.telecomnancy.rpg.characters.factory.GameCharacter;
import eu.telecomnancy.rpg.characters.Prototype;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Represents a team of GameCharacters with functionality for adding,
 * removing, and managing players.
 * Implements the Prototype pattern to allow cloning of teams, including deep copies of the characters.
 */
public class Team implements Prototype {

    private String name;
    private Collection<CharacterInterface> players;


    //Constructeur & Clone
    /**
     * Constructs a new team with a given name.
     *
     * @param name the name of the team.
     */
    public Team(String name) {
        this.name = name;
        players=new ArrayList<CharacterInterface>();
    }

    /**
     * Private copy constructor used for cloning a team.
     * Creates deep copies of all base players, without any decorator.
     *
     * @param source the original team to clone.
     */
    private Team(Team source) {
        this.name = source.name;
        this.players = new ArrayList<>();

        for (CharacterInterface player : source.players) {
            // Récupérer le personnage de base en déroulant les décorateurs si nécessaire
            GameCharacter baseCharacter = unwrapCharacter(player);

            // Cloner le personnage de base pour garantir une copie indépendante
            this.players.add(baseCharacter.clone());
        }
    }

    /**
     * Helper method to unwrap a CharacterInterface and retrieve the base GameCharacter.
     * If the character is decorated, recursively traverse the decorators to find the core GameCharacter.
     *
     * @param character the CharacterInterface to unwrap
     * @return the base GameCharacter
     * @throws IllegalArgumentException if the character cannot be unwrapped to a GameCharacter
     */
    private GameCharacter unwrapCharacter(CharacterInterface character) {
        // Traverse through decorators until the base GameCharacter is found
        while (character instanceof CharacterDecorator) {
            character = ((CharacterDecorator) character).getInnerCharacter();
        }

        // Ensure the final unwrapped character is a GameCharacter
        if (character instanceof GameCharacter) {
            return (GameCharacter) character;
        } else {
            throw new IllegalArgumentException("Invalid character type: Unable to unwrap to GameCharacter.");
        }
    }


    /**
     * Creates a deep clone of the team.
     *
     * @return a new Team object with cloned players.
     */
    public Team clone(){
        return new Team(this);
    }


    // Additional methods (getters, setters, and player management)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Collection<CharacterInterface> getPlayers() {
        return players;
    }

    public void addPlayer(GameCharacter player) {
        players.add(player);
    }

    public void removePlayer(GameCharacter player) {
        players.remove(player);
    }

    public void removePlayer(String name) {
        for (CharacterInterface player : players) {
            if (player.getName().equals(name)) {
                players.remove(player);
                return;
            }
        }
    }

    public CharacterInterface getPlayer(String name) {
        for (CharacterInterface player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public void changePlayer(CharacterInterface player, CharacterInterface newPlayer) {
        if (!this.players.contains(player)) {
            throw new IllegalArgumentException("The player is not in the team");
        }
        else if (this.players.contains(newPlayer)) {
            throw new IllegalArgumentException("The new player is already in the team");
        }
        this.players.remove(player);
        this.players.add(newPlayer);
    }

    public boolean containsPlayer(String name) {
        for (CharacterInterface player : players) {
            if (player.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsPlayer(GameCharacter player) {
        return players.contains(player);
    }

    public int size() {
        return players.size();
    }

}
