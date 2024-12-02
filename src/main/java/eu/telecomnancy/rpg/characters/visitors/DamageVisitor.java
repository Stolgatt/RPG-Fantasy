package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.Healer;
import eu.telecomnancy.rpg.characters.Warrior;
import eu.telecomnancy.rpg.characters.Wizard;

public class DamageVisitor implements Visitor {
    private int damage;

    public void visitWarrior(Warrior warrior){
        warrior.setHealth(warrior.getHealth() - damage);
    }
    public void visitWizard(Wizard wizard){
        wizard.setHealth(wizard.getHealth() - damage);
    }
    public void visitHealer(Healer healer){
        healer.setHealth(healer.getHealth() - damage);
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
