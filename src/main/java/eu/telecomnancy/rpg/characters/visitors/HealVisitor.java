package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.Healer;
import eu.telecomnancy.rpg.characters.Warrior;
import eu.telecomnancy.rpg.characters.Wizard;

public class HealVisitor implements Visitor {
    private int heal;

    public void visitWarrior(Warrior warrior){
        warrior.setHealth(warrior.getHealth() + heal);
    }
    public void visitWizard(Wizard wizard){
        wizard.setHealth(wizard.getHealth() + heal);
    }
    public void visitHealer(Healer healer){
        healer.setHealth(healer.getHealth() + heal);
    }

    public int getHeal() {
        return heal;
    }
    public void setHeal(int heal) {
        this.heal = heal;
    }

}
