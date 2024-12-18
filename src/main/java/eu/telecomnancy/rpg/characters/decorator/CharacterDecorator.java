package eu.telecomnancy.rpg.characters.decorator;

import eu.telecomnancy.rpg.characters.factory.CharacterInterface;
import eu.telecomnancy.rpg.characters.factory.GameCharacter;

/**
 * Abstract decorator class for enhancing a character's functionality.
 * It wraps a concrete GameCharacter object and delegates its basic actions.
 */
public abstract class CharacterDecorator implements CharacterInterface {
    protected CharacterInterface decoratedCharacter; // Reference to the decorated character

    /**
     * Constructor for the decorator.
     * @param character the character to be decorated.
     */
    public CharacterDecorator(CharacterInterface character) {
        this.decoratedCharacter = character;
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
}
