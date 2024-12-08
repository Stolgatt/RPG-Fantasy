package eu.telecomnancy.rpg.characters.strategy;

/**
 * A neutral combat strategy where damage dealt and received are unchanged.
 * This is the default behavior for characters who do not adopt any specific strategy.
 */
public class NeutralStrategy implements CombatStrategy {

    /**
     * Calculates the damage dealt using the neutral strategy.
     * The damage remains unchanged.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the unmodified base damage.
     */
    public double calculateDamageDealt(double baseDamage){
        return baseDamage;
    }

    /**
     * Calculates the damage received using the neutral strategy.
     * The damage remains unchanged.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the unmodified base damage.
     */
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage;
    }
}
