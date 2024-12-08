package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;
import eu.telecomnancy.rpg.characters.visitors.DamageVisitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DamageVisitorTest {

    @Test
    void testDamageWarrior() {
        Warrior warrior = new Warrior("Strong Warrior");
        double initialHealth = warrior.getHealth();

        DamageVisitor damageVisitor = new DamageVisitor();
        damageVisitor.setDamage(20);
        warrior.accept(damageVisitor);

        assertEquals(initialHealth - 20, warrior.getHealth());
    }

    @Test
    void testDamageWizard() {
        Wizard wizard = new Wizard("Wise Wizard");
        double initialHealth = wizard.getHealth();

        DamageVisitor damageVisitor = new DamageVisitor();
        damageVisitor.setDamage(15);
        wizard.accept(damageVisitor);

        assertEquals(initialHealth - 15, wizard.getHealth());
    }

    @Test
    void testDamageHealer() {
        Healer healer = new Healer("Kind Healer");
        double initialHealth = healer.getHealth();

        DamageVisitor damageVisitor = new DamageVisitor();
        damageVisitor.setDamage(10);
        healer.accept(damageVisitor);

        assertEquals(initialHealth - 10, healer.getHealth());
    }
}
