package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;

/**
 * The BuffVisitor applies a "buff" (stat boost) to characters of type Warrior, Wizard, and Healer.
 * This visitor modifies certain attributes of a character by increasing or decreasing their value.
 */
public class BuffVisitor implements Visitor{
    private int buff;

    /**
     * Applies a buff to the Warrior's strength.
     *
     * @param warrior The Warrior to buff.
     */
    public void visitWarrior(Warrior warrior){
        warrior.setStrength(warrior.getStrength() + buff);
    }

    /**
     * Applies a buff to the Wizard's intelligence.
     *
     * @param wizard The Wizard to buff.
     */
    public void visitWizard(Wizard wizard){
        wizard.setIntelligence(wizard.getIntelligence() + buff);
    }

    /**
     * Applies a buff to the Healer's wisdom.
     *
     * @param healer The Healer to buff.
     */
    public void visitHealer(Healer healer){
        healer.setWisdom(healer.getWisdom() + buff);
    }

    /**
     * Applies a buff to the ArmoredDecorator's factor.
     *
     * @param armor The ArmoredDecorator to buff.
     */
    public void visitArmor(ArmoredDecorator armor){
        armor.setArmorFactor(armor.getArmorFactor() + buff);
    }


    // Getters and Setters for the buff value
    public int getBuff() {
        return buff;
    }
    public void setBuff(int buff) {
        this.buff = buff;
    }
}
