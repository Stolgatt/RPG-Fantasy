package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.decorator.InvincibleDecorator;
import eu.telecomnancy.rpg.characters.factory.GameCharacter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterDecoratorTest {

    // Mock class for GameCharacter to test decorators
    class MockGameCharacter extends GameCharacter {
        public MockGameCharacter(String name) {
            super(name);
        }

        @Override
        public GameCharacter clone() {
            return new MockGameCharacter(this.getName());
        }

        @Override
        public void accept(eu.telecomnancy.rpg.characters.visitors.Visitor visitor) {
            // Do nothing, used for testing purposes
        }

        @Override
        public double calculateTakeDamage(double baseDamage) {
            return baseDamage; // No modifications to damage
        }

        @Override
        public double calculateDealDamage(double baseDamage) {
            return baseDamage; // No modifications to damage
        }

        @Override
        public String toString(){
            return this.getName() + " in MockGameCharacterTest";
        }
    }

    @Test
    void testArmoredDecoratorDamageReduction() {
        GameCharacter character = new MockGameCharacter("Test Character");
        character.setHealth(100);

        // Apply armor with a 50% reduction
        ArmoredDecorator armoredCharacter = new ArmoredDecorator(character, 50);

        // Test health remains the same when armor is applied
        assertEquals(100, armoredCharacter.getHealth(), "Health should remain unchanged when decorating");

        // Test damage reduction
        double reducedDamage = armoredCharacter.calculateTakeDamage(50);
        assertEquals(25, reducedDamage, "Damage should be reduced by 50% (50 * (1 - 0.5))");

        // Test changing the armor factor
        armoredCharacter.setArmorFactor(80); // 80% reduction
        reducedDamage = armoredCharacter.calculateTakeDamage(50);
        assertEquals(10, reducedDamage, "Damage should be reduced by 80% (50 * (1 - 0.8))");

        // Test breaking armor
        armoredCharacter.setArmorFactor(-1); // Break armor
        reducedDamage = armoredCharacter.calculateTakeDamage(50);
        assertEquals(50, reducedDamage, "Damage should no longer be reduced as armor is broken");
    }

    @Test
    void testInvincibleDecorator() {
        GameCharacter character = new MockGameCharacter("Test Character");
        character.setHealth(100);

        // Apply invincibility
        InvincibleDecorator invincibleCharacter = new InvincibleDecorator(character);

        // Test health remains the same when invincibility is applied
        assertEquals(100, invincibleCharacter.getHealth(), "Health should remain unchanged when decorating");

        // Test damage taken is always 0
        double damageTaken = invincibleCharacter.calculateTakeDamage(50);
        assertEquals(0, damageTaken, "An invincible character should take 0 damage");

        // Test chaining multiple decorators
        ArmoredDecorator armoredInvincibleCharacter = new ArmoredDecorator(invincibleCharacter, 0.5);
        damageTaken = armoredInvincibleCharacter.calculateTakeDamage(50);
        assertEquals(0, damageTaken, "An invincible character should still take 0 damage even with armor");
    }

    @Test
    void testDecoratorIntegration() {
        GameCharacter character = new MockGameCharacter("Integrated Test Character");
        character.setHealth(200);
        character.setExperiencePoints(100);
        character.setLevel(5);

        // Apply both decorators
        ArmoredDecorator armoredCharacter = new ArmoredDecorator(character, 0.3);
        InvincibleDecorator fullyDecoratedCharacter = new InvincibleDecorator(armoredCharacter);

        // Test basic attributes delegation
        assertEquals("Integrated Test Character", fullyDecoratedCharacter.getName());
        assertEquals(200, fullyDecoratedCharacter.getHealth());
        assertEquals(100, fullyDecoratedCharacter.getExperiencePoints());
        assertEquals(5, fullyDecoratedCharacter.getLevel());

        // Test damage calculation with both decorators
        double damageTaken = fullyDecoratedCharacter.calculateTakeDamage(50);
        assertEquals(0, damageTaken, "An invincible character should take 0 damage regardless of armor");
    }
}
