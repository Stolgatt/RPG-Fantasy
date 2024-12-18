package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;

/**
 * The DamageVisitor applies damage to characters of type Warrior, Wizard, and Healer.
 * This visitor reduces a character's health based on the damage dealt, considering their combat strategy.
 */
public class DamageVisitor implements Visitor {
    private double damage;

    /**
     * Applies damage to the Warrior.
     *
     * @param warrior The Warrior to deal damage to.
     */
    public void visitWarrior(Warrior warrior){
        double realDamage = warrior.calculateTakeDamage(damage);
        warrior.setHealth(warrior.getHealth() - realDamage);
    }

    /**
     * Applies damage to the Wizard.
     *
     * @param wizard The Wizard to deal damage to.
     */
    public void visitWizard(Wizard wizard){
        double realDamage = wizard.calculateTakeDamage(damage);
        wizard.setHealth(wizard.getHealth() - realDamage);
    }

    /**
     * Applies damage to the Healer.
     *
     * @param healer The Healer to deal damage to.
     */
    public void visitHealer(Healer healer){
        double realDamage = healer.calculateTakeDamage(damage);
        healer.setHealth(healer.getHealth() - realDamage);
    }

    /**
     * Applies damage to the ArmoredDecorator.
     *
     * @param armor The ArmoredDecorator to damage.
     */
    public void visitArmor(ArmoredDecorator armor){
        armor.setArmorFactor(armor.getArmorFactor() - damage);
    }

    // Getters and Setters for the damage value
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
}
