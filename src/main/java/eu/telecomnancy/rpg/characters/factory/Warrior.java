package eu.telecomnancy.rpg.characters.factory;

import eu.telecomnancy.rpg.characters.observer.DeathObserver;
import eu.telecomnancy.rpg.characters.observer.LevelUpObserver;
import eu.telecomnancy.rpg.characters.strategy.NeutralStrategy;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

import java.util.Random;

public class Warrior extends GameCharacter {
    private int strength;

    //Contructor
    /**
     * Creates a new Warrior character with a specified name.
     * The strength is calculated based on the character's level
     * plus a random factor, and the health is set to an initial value.
     * The default combat strategy is set to Neutral.
     *
     * @param name the name of the Warrior.
     */
    public Warrior(String name) {
        super(name);
        strength = getLevel() * 10+new Random().nextInt(10);
        this.setHealth(200);
        this.setCombatStrategy(new NeutralStrategy());
    }


    //Pattern Prototype
    /**
     * Private constructor used to create a deep copy of a Warrior.
     * Copies the strength, health, and level from the source Warrior.
     * The clone starts with a Neutral combat strategy by default.
     *
     * @param source the Warrior to copy.
     */
    private Warrior(Warrior source){
        super(source.getName());
        this.strength = source.getStrength();
        this.setHealth(source.getHealth());
        this.setLevel(source.getLevel());
        this.setCombatStrategy(new NeutralStrategy()); //Le clone commencera avec une stratégie Neutre initialement
        this.attach(new LevelUpObserver(this));
        this.attach(new DeathObserver(this));
    }

    /**
     * Creates a deep copy of this Warrior.
     * Uses the private copy constructor to duplicate all fields,
     * ensuring a new instance is created with the same attributes.
     *
     * @return a new Warrior instance that is a copy of this one.
     */
    @Override
    public Warrior clone(){
        return new Warrior(this);
    }

    //Pattern Visitors
    /**
     * Accepts a Visitor to perform an operation specific to Warriors.
     * This enables dynamic behavior to be applied without modifying the class.
     *
     * @param visitor the Visitor implementing specific logic for Warriors.
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visitWarrior(this);
    }


    public int getStrength() {
        return strength;
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    
}
