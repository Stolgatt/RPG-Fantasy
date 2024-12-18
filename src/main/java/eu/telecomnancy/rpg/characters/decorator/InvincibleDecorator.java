package eu.telecomnancy.rpg.characters.decorator;

import eu.telecomnancy.rpg.characters.factory.CharacterInterface;
import eu.telecomnancy.rpg.characters.factory.GameCharacter;

/**
 * Decorator that makes a character invincible.
 * An invincible character takes no damage regardless of the attack.
 */
public class InvincibleDecorator extends CharacterDecorator {

    /**
     * Constructor for the InvincibleDecorator.
     * @param character the character to decorate as invincible.
     */
    public InvincibleDecorator(CharacterInterface character) {
        super(character);
    }

    /**
     * Calculates the damage taken by the character.
     * An invincible character always takes zero damage.
     * @param baseDamage the original incoming damage.
     * @return always 0.
     */
    public double calculateTakeDamage(double baseDamage) {
        return 0;
    }
}
