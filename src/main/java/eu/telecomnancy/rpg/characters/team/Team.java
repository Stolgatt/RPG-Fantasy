package eu.telecomnancy.rpg.characters.team;

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
    private Collection<GameCharacter> players;


    //Constructeur & Clone
    /**
     * Constructs a new team with a given name.
     *
     * @param name the name of the team.
     */
    public Team(String name) {
        this.name = name;
        players=new ArrayList<GameCharacter>();
    }

    /**
     * Private copy constructor used for cloning a team.
     * Creates deep copies of all players.
     *
     * @param source the original team to clone.
     */
    private Team(Team source) {
        this.name = source.name;
        this.players = new ArrayList<>();
        for (GameCharacter player : source.players) {
            this.players.add(player.clone()); // Deep copy for each character
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
    
    public Collection<GameCharacter> getPlayers() {
        return players;
    }

    public void addPlayer(GameCharacter player) {
        players.add(player);
    }

    public void removePlayer(GameCharacter player) {
        players.remove(player);
    }

    public void removePlayer(String name) {
        for (GameCharacter player : players) {
            if (player.getName().equals(name)) {
                players.remove(player);
                return;
            }
        }
    }

    public GameCharacter getPlayer(String name) {
        for (GameCharacter player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    public boolean containsPlayer(String name) {
        for (GameCharacter player : players) {
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
