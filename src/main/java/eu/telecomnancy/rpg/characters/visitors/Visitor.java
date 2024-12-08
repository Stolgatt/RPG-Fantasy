package eu.telecomnancy.rpg.characters.visitors;

import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;

/**
 * The Visitor interface defines methods for visiting each character type.
 * Concrete visitors (like BuffVisitor, DamageVisitor, HealVisitor) implement this interface to apply actions to the characters.
 */
public interface Visitor {
    /**
     * Visit a Warrior character.
     *
     * @param warrior The Warrior to visit.
     */
    public void visitWarrior(Warrior warrior);

    /**
     * Visit a Wizard character.
     *
     * @param wizard The Wizard to visit.
     */
    public void visitWizard(Wizard wizard);

    /**
     * Visit a Healer character.
     *
     * @param healer The Healer to visit.
     */
    public void visitHealer(Healer healer);
}
