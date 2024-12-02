package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.*;
import eu.telecomnancy.rpg.characters.visitors.BuffVisitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuffVisitorTest {

    @Test
    void testBuffWarrior() {
        Warrior warrior = new Warrior("Strong Warrior");
        int initialStrength = warrior.getStrength();

        BuffVisitor buffVisitor = new BuffVisitor();
        buffVisitor.setBuff(10);
        warrior.accept(buffVisitor);

        assertEquals(initialStrength + 10, warrior.getStrength());
    }

    @Test
    void testBuffWizard() {
        Wizard wizard = new Wizard("Wise Wizard");
        int initialIntelligence = wizard.getIntelligence();

        BuffVisitor buffVisitor = new BuffVisitor();
        buffVisitor.setBuff(5);
        wizard.accept(buffVisitor);

        assertEquals(initialIntelligence + 5, wizard.getIntelligence());
    }

    @Test
    void testBuffHealer() {
        Healer healer = new Healer("Kind Healer");
        int initialWisdom = healer.getWisdom();

        BuffVisitor buffVisitor = new BuffVisitor();
        buffVisitor.setBuff(7);
        healer.accept(buffVisitor);

        assertEquals(initialWisdom + 7, healer.getWisdom());
    }
}
