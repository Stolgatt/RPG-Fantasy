package eu.telecomnancy.rpg.characters.strategy;


/**
 * An aggressive combat strategy where characters deal more damage but also take more damage.
 * This is suitable for players or characters who prioritize offense over defense.
 */
public class AggressiveStrategy implements CombatStrategy {

    /**
     * Calculates the damage dealt using the aggressive strategy.
     * Damage dealt is increased by 30%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the increased damage.
     */
    public double calculateDamageDealt(double baseDamage){
        return 1.3 * baseDamage;
    }

    /**
     * Calculates the damage received using the aggressive strategy.
     * Damage received is increased by 30%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the increased damage received.
     */
    public double calculateDamageReceived(double baseDamage){
        return  1.3 * baseDamage;
    }

    public String getName(){
        return "Aggressive";
    }
}
