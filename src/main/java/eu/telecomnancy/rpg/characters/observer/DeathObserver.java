package eu.telecomnancy.rpg.characters.observer;

import eu.telecomnancy.rpg.characters.factory.GameCharacter;

/**
 * Observer that monitors a character's health and handles death logic.
 */
public class DeathObserver implements Observer {
    private final GameCharacter character;
    Boolean dead = false;

    /**
     * Constructor to associate the observer with a specific character.
     * @param character the character being observed
     */
    public DeathObserver(GameCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {
        double health = character.getHealth();
        if (health <= 0 && !dead) {
            dead = true;
            character.setHealth(0); // Ensure health does not drop below 0
            System.out.println("\nA character is dead: " + character.toString());
        }
    }

    @Override
    public String toString() {
        if (dead) {
            return "\u001B[33m[DEAD] \u001B[0m";
        }
        else {
            return "";
        }
    }
}
