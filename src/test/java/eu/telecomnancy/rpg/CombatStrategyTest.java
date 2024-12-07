package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.Healer;
import eu.telecomnancy.rpg.characters.Warrior;
import eu.telecomnancy.rpg.characters.Wizard;
import eu.telecomnancy.rpg.characters.strategy.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CombatStrategyTest {

    @Test
    void testStrategiesOnWizard() {
        Wizard wizard = new Wizard("Gandalf");

        // Base damage for tests
        double baseDamageDealt = 100.0;
        double baseDamageReceived = 50.0;

        // Test Aggressive Strategy
        wizard.setCombatStrategy(new AggressiveStrategy());
        assertEquals(130.0, wizard.calculateDealDamage(baseDamageDealt));
        assertEquals(65.0, wizard.calculateTakeDamage(baseDamageReceived));

        // Test Neutral Strategy
        wizard.setCombatStrategy(new NeutralStrategy());
        assertEquals(100.0, wizard.calculateDealDamage(baseDamageDealt));
        assertEquals(50.0, wizard.calculateTakeDamage(baseDamageReceived));

        // Test Defensive Strategy
        wizard.setCombatStrategy(new DefensiveStrategy());
        assertEquals(80.0, wizard.calculateDealDamage(baseDamageDealt));
        assertEquals(40.0, wizard.calculateTakeDamage(baseDamageReceived));

        // Test Berserker Strategy
        wizard.setCombatStrategy(new BerserkerStrategy());
        assertEquals(150.0, wizard.calculateDealDamage(baseDamageDealt));
        assertEquals(75.0, wizard.calculateTakeDamage(baseDamageReceived));

        // Test Stone Golem Strategy
        wizard.setCombatStrategy(new StoneGolemStrategy());
        assertEquals(40.0, wizard.calculateDealDamage(baseDamageDealt));
        assertEquals(20.0, wizard.calculateTakeDamage(baseDamageReceived));
    }

    @Test
    void testStrategiesOnWarrior() {
        Warrior warrior = new Warrior("Aragorn");

        // Base damage for tests
        double baseDamageDealt = 120.0;
        double baseDamageReceived = 60.0;

        // Test Aggressive Strategy
        warrior.setCombatStrategy(new AggressiveStrategy());
        assertEquals(156.0, warrior.calculateDealDamage(baseDamageDealt));
        assertEquals(78.0, warrior.calculateTakeDamage(baseDamageReceived));

        // Test Neutral Strategy
        warrior.setCombatStrategy(new NeutralStrategy());
        assertEquals(120.0, warrior.calculateDealDamage(baseDamageDealt));
        assertEquals(60.0, warrior.calculateTakeDamage(baseDamageReceived));

        // Test Defensive Strategy
        warrior.setCombatStrategy(new DefensiveStrategy());
        assertEquals(96.0, warrior.calculateDealDamage(baseDamageDealt));
        assertEquals(48.0, warrior.calculateTakeDamage(baseDamageReceived));

        // Test Berserker Strategy
        warrior.setCombatStrategy(new BerserkerStrategy());
        assertEquals(180.0, warrior.calculateDealDamage(baseDamageDealt));
        assertEquals(90.0, warrior.calculateTakeDamage(baseDamageReceived));

        // Test StoneGolem Strategy
        warrior.setCombatStrategy(new StoneGolemStrategy());
        assertEquals(48.0, warrior.calculateDealDamage(baseDamageDealt));
        assertEquals(24.0, warrior.calculateTakeDamage(baseDamageReceived));
    }

    @Test
    void testStrategiesOnHealer() {
        Healer healer = new Healer("Elrond");

        // Base damage for tests
        double baseDamageDealt = 80.0;
        double baseDamageReceived = 40.0;

        // Test Aggressive Strategy
        healer.setCombatStrategy(new AggressiveStrategy());
        assertEquals(104.0, healer.calculateDealDamage(baseDamageDealt));
        assertEquals(52.0, healer.calculateTakeDamage(baseDamageReceived));

        // Test Neutral Strategy
        healer.setCombatStrategy(new NeutralStrategy());
        assertEquals(80.0, healer.calculateDealDamage(baseDamageDealt));
        assertEquals(40.0, healer.calculateTakeDamage(baseDamageReceived));

        // Test Defensive Strategy
        healer.setCombatStrategy(new DefensiveStrategy());
        assertEquals(64.0, healer.calculateDealDamage(baseDamageDealt));
        assertEquals(32.0, healer.calculateTakeDamage(baseDamageReceived));

        // Test Berserker Strategy
        healer.setCombatStrategy(new BerserkerStrategy());
        assertEquals(120.0, healer.calculateDealDamage(baseDamageDealt));
        assertEquals(60.0, healer.calculateTakeDamage(baseDamageReceived));

        // Test StoneGolem Strategy
        healer.setCombatStrategy(new StoneGolemStrategy());
        assertEquals(32.0, healer.calculateDealDamage(baseDamageDealt));
        assertEquals(16.0, healer.calculateTakeDamage(baseDamageReceived));
    }
}
