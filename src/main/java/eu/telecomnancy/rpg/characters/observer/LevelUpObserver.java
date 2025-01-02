package eu.telecomnancy.rpg.characters.observer;

import eu.telecomnancy.rpg.characters.factory.GameCharacter;

/**
 * Observer that monitors a character's experience points and handles level-up logic.
 */
public class LevelUpObserver implements Observer {
    private final GameCharacter character;

    /**
     * Constructor to associate the observer with a specific character.
     * @param character the character being observed
     */
    public LevelUpObserver(GameCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {
        // Leveling up requires filling an XP bar: 100 + 5 * character's level.
        int level = character.getLevel();
        double xp = character.getExperiencePoints();
        int xpNeeded = 100 + 5 * level;

        if (xp >= xpNeeded) {
            character.setExperiencePoints(xp - xpNeeded); // Remaining XP carried over
            level++;
            character.setLevel(level); // Update the character's level
            System.out.println("\nLevel up: " + character.toString());
        }
    }

    @Override
    public String toString() {
        return "";
    }
}
