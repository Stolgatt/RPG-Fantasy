package eu.telecomnancy.rpg.characters.factory;

import eu.telecomnancy.rpg.characters.visitors.Visitor;

/**
 * Interface representing the basic contract for all character types,
 * including both concrete characters and decorators.
 */
public interface CharacterInterface {
    // Getter & Setter for basic attributes

    /**
     * Gets the character's name.
     * @return the character's name.
     */
    String getName();

    /**
     * Sets the character's name.
     * @param name the new name of the character.
     */
    void setName(String name);

    /**
     * Gets the character's current health.
     * @return the character's health.
     */
    double getHealth();

    /**
     * Updates the character's health.
     * @param health the new health value.
     */
    void setHealth(double health);

    /**
     * Gets the character's experience points.
     * @return the experience points of the character.
     */
    double getExperiencePoints();

    /**
     * Updates the character's experience points.
     * @param experiencePoints the new experience points value.
     */
    void setExperiencePoints(double experiencePoints);

    /**
     * Gets the character's level.
     * @return the character's level.
     */
    int getLevel();

    /**
     * Sets the character's level.
     * @param level the new level of the character.
     */
    void setLevel(int level);

    public double calculateTakeDamage(double baseDamage);

    public double calculateDealDamage(double baseDamage);

}
