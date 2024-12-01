package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.Healer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HealerTest {

    @Test
    public void testWarriorClone() {
        Healer original = new Healer("Conan");
        original.setWisdom(100);

        Healer clone = original.clone();

        assertNotSame(original, clone);

        assertEquals(original.getName(), clone.getName());
        assertEquals(original.getWisdom(), clone.getWisdom());
        assertEquals(original.getHealth(), clone.getHealth());

        clone.setWisdom(50);
        assertNotEquals(original.getWisdom(), clone.getWisdom());
    }
}
