package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.Healer;
import eu.telecomnancy.rpg.characters.Warrior;
import eu.telecomnancy.rpg.characters.Wizard;

public interface Visitor {
    public void visitWarrior(Warrior warrior);
    public void visitWizard(Wizard wizard);
    public void visitHealer(Healer healer);


}
