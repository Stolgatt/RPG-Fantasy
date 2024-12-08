package eu.telecomnancy.rpg.characters.factory;


import eu.telecomnancy.rpg.characters.Prototype;
import eu.telecomnancy.rpg.characters.Visitable;
import eu.telecomnancy.rpg.characters.strategy.CombatStrategy;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

import java.util.Objects;

public abstract class GameCharacter implements Prototype, Visitable {

    private String name;
    private double health;
    private int experiencePoints;
    private int level;

    private CombatStrategy strategy;


    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
    }

    //Pattern Prototype
    /**
     * Creates a deep copy of the current GameCharacter.
     * Subclasses must implement this method to ensure that all fields,
     * including strategy and unique attributes, are copied properly.
     */
    public abstract GameCharacter clone();

    //Pattern Visitors
    /**
     * Accepts a Visitor to perform an operation on this GameCharacter.
     * This allows dynamic behavior to be added to the class without modifying it.
     *
     * @param visitor the Visitor implementing specific logic for this character type.
     */
    public abstract void accept(Visitor visitor);


    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String toString() {
        return name + " (Level " + level + ") with " + health + " HP and " + experiencePoints + " XP";
    }

    //Pattern Strategy
    /**
     * Sets the combat strategy for the character.
     * This allows the character's behavior during combat to be dynamically altered.
     *
     * @param strategy the CombatStrategy implementation to apply; must not be null.
     * @throws IllegalArgumentException if the strategy is null.
     */
    public void setCombatStrategy(CombatStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Combat strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public CombatStrategy getCombatStrategy() {
        return strategy;
    }

    /**
     * Calculates the damage dealt by this character based on its current combat strategy.
     *
     * @param baseDamage the base damage to modify based on the strategy.
     * @return the modified damage value after applying the combat strategy.
     */
    public double calculateDealDamage(double baseDamage) {
        return strategy.calculateDamageDealt(baseDamage);
    }

    /**
     * Calculates the damage taken by this character based on its current combat strategy.
     *
     * @param baseDamage the base damage to modify based on the strategy.
     * @return the modified damage value after applying the combat strategy.
     */
    public double calculateTakeDamage(double baseDamage) {
        return strategy.calculateDamageReceived(baseDamage);
    }


    //Other useful methods
    /**
     * Compares this GameCharacter to another object for equality.
     * Two GameCharacters are considered equal if they have the same name,
     * health, experience points, and level.
     *
     * @param o the object to compare to.
     * @return true if the two objects are equivalent, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCharacter that = (GameCharacter) o;
        return health == that.health &&
                experiencePoints == that.experiencePoints &&
                level == that.level &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, experiencePoints, level);
    }


}
