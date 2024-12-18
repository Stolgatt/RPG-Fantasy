package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;

/**
 * The HealVisitor applies healing to characters of type Warrior, Wizard, and Healer.
 * This visitor restores the health of characters by a specified amount.
 */
public class HealVisitor implements Visitor {
    private int heal;

    /**
     * Applies healing to the Warrior.
     *
     * @param warrior The Warrior to heal.
     */
    public void visitWarrior(Warrior warrior){
        warrior.setHealth(warrior.getHealth() + heal);
    }

    /**
     * Applies healing to the Wizard.
     *
     * @param wizard The Wizard to heal.
     */
    public void visitWizard(Wizard wizard){
        wizard.setHealth(wizard.getHealth() + heal);
    }

    /**
     * Applies healing to the Healer.
     *
     * @param healer The Healer to heal.
     */
    public void visitHealer(Healer healer){
        healer.setHealth(healer.getHealth() + heal);
    }

    /**
     * Applies healing to the ArmoredDecorator.
     *
     * @param armor The ArmoredDecorator to heal.
     */
    public void visitArmor(ArmoredDecorator armor){
        armor.setHealth(armor.getHealth() + heal);
    }

    // Getters and Setters for the healing amount
    public int getHeal() {
        return heal;
    }
    public void setHeal(int heal) {
        this.heal = heal;
    }

}
