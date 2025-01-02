package eu.telecomnancy.rpg.characters.factory;

import eu.telecomnancy.rpg.characters.observer.DeathObserver;
import eu.telecomnancy.rpg.characters.observer.LevelUpObserver;
import eu.telecomnancy.rpg.characters.strategy.NeutralStrategy;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

import java.util.Random;


public class Wizard extends GameCharacter {
    private int intelligence;

    //Contructor
    /**
     * Creates a new Wizard character with a specified name.
     * The intelligence is calculated based on the character's level
     * plus a random factor, and the health is set to an initial value.
     * The default combat strategy is set to Neutral.
     *
     * @param name the name of the Wizard.
     */
    public Wizard(String name) {
        super(name);
        intelligence = getLevel() * 10+new Random().nextInt(10);
        this.setHealth(150);
        this.setCombatStrategy(new NeutralStrategy());
    }

    //Pattern Prototype
    /**
     * Private constructor used to create a deep copy of a Wizard.
     * Copies the intelligence, health, and level from the source Wizard.
     * The clone starts with a Neutral combat strategy by default.
     *
     * @param source the Wizard to copy.
     */
    private Wizard(Wizard source) {
        super(source.getName());
        this.intelligence = source.getIntelligence();
        this.setHealth(source.getHealth());
        this.setLevel(source.getLevel());
        this.setCombatStrategy(new NeutralStrategy()); //Le clone commencera avec une stratégie Neutre initialement
        this.clearObservers();
        this.attach(new LevelUpObserver(this));
        this.attach(new DeathObserver(this));
    }

    /**
     * Creates a deep copy of this Wizard.
     * Uses the private copy constructor to duplicate all fields,
     * ensuring a new instance is created with the same attributes.
     *
     * @return a new Wizard instance that is a copy of this one.
     */
    @Override
    public Wizard clone() {
        return new Wizard(this);
    }

    //Pattern Visitors
    /**
     * Accepts a Visitor to perform an operation specific to Wizards.
     * This enables dynamic behavior to be applied without modifying the class.
     *
     * @param visitor the Visitor implementing specific logic for Wizards.
     */
    @Override
    public void accept(Visitor visitor){
        visitor.visitWizard(this);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public String toString() {
        return observersToString() + "\u001B[32mWizard\u001B[0m " + this.getName() + " (Level " + this.getLevel() + ") with " + this.getHealth() + " HP and " + this.getExperiencePoints() + " XP " +
                "| \u001B[4mAttributes\u001B[0m: " + intelligence + " intelligence | \u001B[4mStrategy\u001B[0m: " + this.getCombatStrategy().getName() +
                " | \u001B[4mBelongings\u001B[0m:";
    }
}
