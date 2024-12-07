package eu.telecomnancy.rpg.characters;


import eu.telecomnancy.rpg.characters.strategy.CombatStrategy;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

import java.util.Objects;

public abstract class GameCharacter implements Prototype, Visitable {

    private String name;
    private int health;
    private int experiencePoints;
    private int level;

    private CombatStrategy strategy;


    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
    }

    public abstract GameCharacter clone();

    public abstract void accept(Visitor visitor);

    //Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
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

    public void setCombatStrategy(CombatStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Combat strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public CombatStrategy getCombatStrategy() {
        return strategy;
    }

    public double calculateDealDamage(double baseDamage) {
        return strategy.calculateDamageDealt(baseDamage);
    }

    public double calculateTakeDamage(double baseDamage) {
        return strategy.calculateDamageReceived(baseDamage);
    }


    public String toString() {
        return name + " (Level " + level + ") with " + health + " HP and " + experiencePoints + " XP";
    }

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
