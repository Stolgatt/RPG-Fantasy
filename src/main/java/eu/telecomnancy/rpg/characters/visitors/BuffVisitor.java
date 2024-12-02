package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.Healer;
import eu.telecomnancy.rpg.characters.Warrior;
import eu.telecomnancy.rpg.characters.Wizard;

public class BuffVisitor implements Visitor{
    private int buff;

    public void visitWarrior(Warrior warrior){
        warrior.setStrength(warrior.getStrength() + buff);
    }
    public void visitWizard(Wizard wizard){
        wizard.setIntelligence(wizard.getIntelligence() + buff);
    }
    public void visitHealer(Healer healer){
        healer.setWisdom(healer.getWisdom() + buff);
    }

    public int getBuff() {
        return buff;
    }
    public void setBuff(int buff) {
        this.buff = buff;
    }
}
