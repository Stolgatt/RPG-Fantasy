package eu.telecomnancy.rpg.characters.strategy;

import eu.telecomnancy.rpg.GameConfiguration;

public interface CombatStrategy {

    /**
     * Calculate the damage dealt by the character based on the base damage and the strategy.
     * @param baseDamage the base damage to be modified
     * @return the modified damage
     */
    double calculateDamageDealt(double baseDamage);

    /**
     * Calculate the damage received by the character based on the base damage and the strategy.
     * @param baseDamage the base damage to be modified
     * @return the modified damage
     */
    double calculateDamageReceived(double baseDamage);

    String getName();
}
