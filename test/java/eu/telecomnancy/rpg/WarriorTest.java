package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.factory.Warrior;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WarriorTest {

    @Test
    public void testWarriorClone() {
        Warrior original = new Warrior("Conan");
        original.setStrength(100);

        Warrior clone = original.clone();

        assertNotSame(original, clone);

        assertEquals(original.getName(), clone.getName());
        assertEquals(original.getStrength(), clone.getStrength());
        assertEquals(original.getHealth(), clone.getHealth());

        clone.setStrength(50);
        assertNotEquals(original.getStrength(), clone.getStrength());
    }
}
