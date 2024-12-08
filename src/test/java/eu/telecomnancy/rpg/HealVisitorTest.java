package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.*;
import eu.telecomnancy.rpg.characters.visitors.HealVisitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HealVisitorTest {

    @Test
    void testHealWarrior() {
        Warrior warrior = new Warrior("Strong Warrior");
        warrior.setHealth(50);
        double initialHealth = warrior.getHealth();

        HealVisitor healVisitor = new HealVisitor();
        healVisitor.setHeal(20);
        warrior.accept(healVisitor);

        assertEquals(initialHealth + 20, warrior.getHealth());
    }

    @Test
    void testHealWizard() {
        Wizard wizard = new Wizard("Wise Wizard");
        wizard.setHealth(40);
        double initialHealth = wizard.getHealth();

        HealVisitor healVisitor = new HealVisitor();
        healVisitor.setHeal(10);
        wizard.accept(healVisitor);

        assertEquals(initialHealth + 10, wizard.getHealth());
    }

    @Test
    void testHealHealer() {
        Healer healer = new Healer("Kind Healer");
        healer.setHealth(30);
        double initialHealth = healer.getHealth();

        HealVisitor healVisitor = new HealVisitor();
        healVisitor.setHeal(25);
        healer.accept(healVisitor);

        assertEquals(initialHealth + 25, healer.getHealth());
    }
}

