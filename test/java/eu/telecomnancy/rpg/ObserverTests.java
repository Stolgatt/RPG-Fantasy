package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.factory.GameCharacter;
import eu.telecomnancy.rpg.characters.factory.Warrior; // Exemple de classe concrète de GameCharacter
import eu.telecomnancy.rpg.characters.observer.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverTests {
    private GameCharacter character;

    @BeforeEach
    void setUp() {
        // Initialize a concrete GameCharacter (e.g., Warrior)
        character = new Warrior("Test Warrior");
    }

    @Test
    void testObserverAttachment() {
        // Verify that observers are attached during initialization
        assertEquals(2, character.getObservers().size(), "Two observers should be attached by default.");
    }

    @Test
    void testLevelUpObserver() {
        // Add experience points to trigger a level-up
        character.setExperiencePoints(120); // Enough XP for level 1
        assertEquals(2, character.getLevel(), "Character should level up to level 2.");
        assertTrue(character.getExperiencePoints() < 100, "Remaining XP should carry over.");
    }

    @Test
    void testDeathObserver() {
        // Reduce health to 0 to trigger death
        character.setHealth(-10); // Negative health to ensure it is set to 0
        assertEquals(0, character.getHealth(), "Health should be set to 0 upon death.");
    }

    @Test
    void testDetachObserver() {
        List<Observer> observers = new ArrayList<>(character.getObservers()); // Clone the list to avoid modification issues
        for (Observer o : observers) {
            character.detach(o);
        }
        assertEquals(0, character.getObservers().size(), "No observers should remain.");
        character.setExperiencePoints(120); // Simulate XP gain
        assertEquals(1, character.getLevel(), "Character should not level up as observers are detached.");
    }
}
