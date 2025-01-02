package eu.telecomnancy.rpg.characters.factory;

import eu.telecomnancy.rpg.characters.observer.DeathObserver;
import eu.telecomnancy.rpg.characters.observer.LevelUpObserver;
import eu.telecomnancy.rpg.characters.strategy.NeutralStrategy;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

import java.util.Random;

public class Healer extends GameCharacter {
    private int wisdom;


    //Contructor
    /**
     * Creates a new Healer character with a specified name.
     * The wisdom is calculated based on the character's level
     * plus a random factor, and the health is set to an initial value.
     * The default combat strategy is set to Neutral.
     *
     * @param name the name of the Healer.
     */
    public Healer(String name) {
        super(name);
        wisdom = getLevel() * 10+new Random().nextInt(10);
        this.setHealth(100);
        this.setCombatStrategy(new NeutralStrategy());
    }

    //Pattern Prototype
    /**
     * Private constructor used to create a deep copy of a Healer.
     * Copies the wisdom, health, and level from the source Healer.
     * The clone starts with a Neutral combat strategy by default.
     *
     * @param source the Healer to copy.
     */
    private Healer(Healer source) {
        super(source.getName());
        this.wisdom = source.wisdom;
        this.setHealth(source.getHealth());
        this.setLevel(source.getLevel());
        this.setCombatStrategy(new NeutralStrategy()); //Le clone commencera avec une stratégie Neutre initialement
        this.clearObservers();
        this.attach(new DeathObserver(this));
        this.attach(new LevelUpObserver(this));
    }

    /**
     * Creates a deep copy of this Healer.
     * Uses the private copy constructor to duplicate all fields,
     * ensuring a new instance is created with the same attributes.
     *
     * @return a new Healer instance that is a copy of this one.
     */
    @Override
    public Healer clone() {
        return new Healer(this);
    }

    //Pattern Visitors
    /**
     * Accepts a Visitor to perform an operation specific to Healer.
     * This enables dynamic behavior to be applied without modifying the class.
     *
     * @param visitor the Visitor implementing specific logic for Healer.
     */
    @Override
    public void accept(Visitor visitor){
        visitor.visitHealer(this);
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public String toString() {
        return observersToString() + "\u001B[34mHealer\u001B[0m " + this.getName() + " (Level " + this.getLevel() + ") with " + this.getHealth() + " HP and " + this.getExperiencePoints() + " XP " +
                "| \u001B[4mAttributes\u001B[0m: " + wisdom + " wisdom | \u001B[4mStrategy\u001B[0m: " + this.getCombatStrategy().getName() +
                " | \u001B[4mBelongings\u001B[0m:";
    }


}
