package eu.telecomnancy.rpg.characters.decorator;

import eu.telecomnancy.rpg.characters.Visitable;
import eu.telecomnancy.rpg.characters.factory.CharacterInterface;
import eu.telecomnancy.rpg.characters.factory.GameCharacter;
import eu.telecomnancy.rpg.characters.strategy.CombatStrategy;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

/**
 * Abstract decorator class for enhancing a character's functionality.
 * It wraps a concrete GameCharacter object and delegates its basic actions.
 */
public abstract class CharacterDecorator implements CharacterInterface, Visitable {
    protected CharacterInterface decoratedCharacter; // Reference to the decorated character

    /**
     * Constructor for the decorator.
     * @param character the character to be decorated.
     */
    public CharacterDecorator(CharacterInterface character) {
        this.decoratedCharacter = character;
    }

    //Pattern Visitors
    /**
     * Accepts a Visitor to perform an operation on this GameCharacter.
     * This allows dynamic behavior to be added to the class without modifying it.
     *
     * @param visitor the Visitor implementing specific logic for this character type.
     */
    public void accept(Visitor visitor){
        if (decoratedCharacter instanceof CharacterDecorator){
            ((CharacterDecorator) decoratedCharacter).accept(visitor);
        }
        else if (decoratedCharacter instanceof GameCharacter){
            ((GameCharacter) decoratedCharacter).accept(visitor);
        }
    }


    // Delegate methods for basic attributes
    @Override
    public String getName(){
        return decoratedCharacter.getName();
    }

    @Override
    public void setName(String name){
        decoratedCharacter.setName(name);
    }

    @Override
    public double getHealth(){
        return decoratedCharacter.getHealth();
    }

    @Override
    public void setHealth(double health){
        decoratedCharacter.setHealth(health);
    }

    @Override
    public double getExperiencePoints(){
        return decoratedCharacter.getExperiencePoints();
    }

    @Override
    public void setExperiencePoints(double experiencePoints){
        decoratedCharacter.setExperiencePoints(experiencePoints);
    }

    @Override
    public int getLevel(){
        return decoratedCharacter.getLevel();
    }

    @Override
    public void setLevel(int level){
        decoratedCharacter.setLevel(level);
    }

    public CharacterInterface getInnerCharacter(){
        return decoratedCharacter;
    }

    public void setCombatStrategy(CombatStrategy strategy){
        decoratedCharacter.setCombatStrategy(strategy);
    }

    public CombatStrategy getCombatStrategy(){
        return decoratedCharacter.getCombatStrategy();
    }

    abstract public String toString();

}
