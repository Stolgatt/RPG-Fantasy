package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterRegistryTest {

    private CharacterRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new CharacterRegistry();
        registry.registerPrototype("wizard", new Wizard("Test Wizard"));
        registry.registerPrototype("warrior", new Warrior("Test Warrior"));
        registry.registerPrototype("healer", new Healer("Test Healer"));
    }

    @Test
    void testRegisterPrototypeAndRetrieve() {
        GameCharacter wizard = registry.createClone("wizard");
        assertNotNull(wizard);
        assertEquals("Test Wizard", wizard.getName());

        GameCharacter warrior = registry.createClone("warrior");
        assertNotNull(warrior);
        assertEquals("Test Warrior", warrior.getName());
    }

    @Test
    void testCreateCloneReturnsNewInstance() {
        GameCharacter wizard1 = registry.createClone("wizard");
        GameCharacter wizard2 = registry.createClone("wizard");

        assertNotSame(wizard1, wizard2);
        assertEquals(wizard1.getName(), wizard2.getName());
    }

    @Test
    void testCreateCloneIndependence() {
        GameCharacter originalWizard = registry.createClone("wizard");
        GameCharacter clonedWizard = registry.createClone("wizard");

        // Change the name of the clone
        clonedWizard.setName("Modified Wizard");

        // Verify the original is not affected
        assertEquals("Test Wizard", originalWizard.getName());
        assertEquals("Modified Wizard", clonedWizard.getName());
    }

    @Test
    void testPrototypeNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registry.createClone("nonexistent");
        });

        assertEquals("Character not found for key: nonexistent", exception.getMessage());
    }

    @Test
    void testRegistryWithMultipleCharacters() {
        GameCharacter wizard = registry.createClone("wizard");
        GameCharacter warrior = registry.createClone("warrior");
        GameCharacter healer = registry.createClone("healer");

        assertEquals("Test Wizard", wizard.getName());
        assertEquals("Test Warrior", warrior.getName());
        assertEquals("Test Healer", healer.getName());
    }
}
