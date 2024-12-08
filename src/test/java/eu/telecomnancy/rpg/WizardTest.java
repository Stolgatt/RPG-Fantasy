package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.factory.Wizard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WizardTest {

    @Test
    public void testWarriorClone() {
        Wizard original = new Wizard("Conan");
        original.setIntelligence(100);

        Wizard clone = original.clone();

        assertNotSame(original, clone);

        assertEquals(original.getName(), clone.getName());
        assertEquals(original.getIntelligence(), clone.getIntelligence());
        assertEquals(original.getHealth(), clone.getHealth());

        clone.setIntelligence(50);
        assertNotEquals(original.getIntelligence(), clone.getIntelligence());
    }
}
