package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.Healer;
import eu.telecomnancy.rpg.characters.Warrior;
import eu.telecomnancy.rpg.characters.Wizard;

public class DamageVisitor implements Visitor {
    private double damage;

    public void visitWarrior(Warrior warrior){
        double realDamage = warrior.calculateTakeDamage(damage);
        warrior.setHealth(warrior.getHealth() - realDamage);
    }
    public void visitWizard(Wizard wizard){
        double realDamage = wizard.calculateTakeDamage(damage);
        wizard.setHealth(wizard.getHealth() - realDamage);
    }
    public void visitHealer(Healer healer){
        double realDamage = healer.calculateTakeDamage(damage);
        healer.setHealth(healer.getHealth() - realDamage);
    }

    public double getDamage() {
        return damage;
    }
    public void setDamage(double damage) {
        this.damage = damage;
    }
}
