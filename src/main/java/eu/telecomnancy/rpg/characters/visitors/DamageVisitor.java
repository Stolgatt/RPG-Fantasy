package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.Visitable;
import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.factory.*;

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
        warrior.setHealth(warrior.getHealth() - damage);
    }

    /**
     * Applies damage to the Wizard.
     *
     * @param wizard The Wizard to deal damage to.
     */
    public void visitWizard(Wizard wizard){
        wizard.setHealth(wizard.getHealth() - damage);
    }

    /**
     * Applies damage to the Healer.
     *
     * @param healer The Healer to deal damage to.
     */
    public void visitHealer(Healer healer){
        healer.setHealth(healer.getHealth() - damage);
    }

    /**
     * Applies damage to the ArmoredDecorator.
     *
     * @param armor The ArmoredDecorator to damage.
     */
    public void visitArmor(ArmoredDecorator armor){
        if (armor.getArmorFactor() == 0.0){         // The armor is broken, doing everything like it doesn't exist
            CharacterInterface inner = armor.getInnerCharacter();
            DamageVisitor visitor = new DamageVisitor();
            visitor.setDamage(damage);
            ((Visitable) inner).accept(visitor);
        }
        else{
            armor.setArmorFactor(armor.getArmorFactor() - damage);
        }
    }

    // Getters and Setters for the damage value
    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
}

