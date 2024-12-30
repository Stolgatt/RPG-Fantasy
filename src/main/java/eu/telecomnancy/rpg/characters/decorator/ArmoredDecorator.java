package eu.telecomnancy.rpg.characters.decorator;

import eu.telecomnancy.rpg.characters.Visitable;
import eu.telecomnancy.rpg.characters.factory.CharacterInterface;
import eu.telecomnancy.rpg.characters.factory.GameCharacter;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

/**
 * Decorator that enhances a character with armor.
 * The armor reduces incoming damage by a factor (armorFactor).
 */
public class ArmoredDecorator extends CharacterDecorator implements Visitable {
    private double armorFactor; // Factor to reduce incoming damage

    /**
     * Constructor for the ArmoredDecorator.
     * @param character the character to decorate with armor.
     * @param armorFactor the reduction factor applied to incoming damage (e.g., 0.2 reduces damage by 20%).
     */
    public ArmoredDecorator(CharacterInterface character, double armorFactor) {
        super(character);
        this.armorFactor = armorFactor;
    }

    /**
     * Sets the armor factor. If the factor is negative, the armor is considered broken.
     * @param factor the new armor factor.
     */
    public void setArmorFactor(double factor) {
        if (factor < 0){
            this.armorFactor = 0;
            System.out.println("The character " + decoratedCharacter.toString() + " has broken his armor");
        } else {
            this.armorFactor = factor;
        }
    }

    /**
     * Gets the current armor factor.
     * @return the armor factor.
     */
    public double getArmorFactor() {
        return this.armorFactor;
    }

    /**
     * Calculates the damage taken by the character after applying the armor's reduction factor.
     * @param baseDamage the original incoming damage.
     * @return the reduced damage after applying armor.
     */
    public double calculateTakeDamage(double baseDamage) {
        return Math.round(decoratedCharacter.calculateTakeDamage(baseDamage) * (1 - armorFactor));
    }

    /**
     * Calculates the damage dealt by the character in the armor.
     * @param baseDamage the original incoming damage
     * @return the actual damage amount.
     */
    public double calculateDealDamage(double baseDamage) {
        return decoratedCharacter.calculateDealDamage(baseDamage);
    }

    /**
     * Accepts a visitor for applying operations on this character.
     * @param visitor the visitor to apply.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitArmor(this);
    }
}
