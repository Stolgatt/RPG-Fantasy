package eu.telecomnancy.rpg.characters.observer;

import eu.telecomnancy.rpg.characters.factory.GameCharacter;

/**
 * Observer that monitors a character's health and handles death logic.
 */
public class DeathObserver implements Observer {
    private final GameCharacter character;

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
        if (health < 0) {
            character.setHealth(0); // Ensure health does not drop below 0
        } else if (health == 0) {
            System.out.println("A character is dead: " + character.toString());
        }
    }
}
