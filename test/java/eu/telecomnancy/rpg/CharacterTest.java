package eu.telecomnancy.rpg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;
import org.junit.jupiter.api.Test;

public class CharacterTest {
    @Test
    void testCreateCharacterWarrior() {
        Warrior warrior = new Warrior("test");
        assertEquals(200, warrior.getHealth());
    }

    @Test
    void testCreateCharacterWizard() {
        Wizard wizard = new Wizard("test");
        assertEquals(150, wizard.getHealth());
    }

    @Test
    void testCreateCharacterHealer() {
        Healer healer = new Healer("test");
        assertEquals(100, healer.getHealth());
    }
    
}
