package eu.telecomnancy.rpg.characters.team;

import eu.telecomnancy.rpg.characters.GameCharacter;
import eu.telecomnancy.rpg.characters.Prototype;

import java.util.ArrayList;
import java.util.Collection;

public class Team implements Prototype {

    private String name;
    private Collection<GameCharacter> players;


    //Constructeur & Clone
    public Team(String name) {
        this.name = name;
        players=new ArrayList<GameCharacter>();
    }

    private Team(Team source) {
        this.name = source.name;
        this.players = new ArrayList<>();
        for (GameCharacter player : source.players) {
            this.players.add(player.clone());
        }
    }

    public Team clone(){
        return new Team(this);
    }


    //Getter et setter
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
