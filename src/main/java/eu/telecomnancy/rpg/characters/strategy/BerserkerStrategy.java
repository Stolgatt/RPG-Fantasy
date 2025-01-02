package eu.telecomnancy.rpg.characters.strategy;

/**
 * A berserker combat strategy where characters deal significantly higher damage
 * but also take significantly more damage.
 * This strategy suits aggressive characters who rely on overwhelming force.
 */
public class BerserkerStrategy implements CombatStrategy {

    /**
     * Calculates the damage dealt using the berserker strategy.
     * Damage dealt is increased by 50%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the increased damage.
     */
    public double calculateDamageDealt(double baseDamage){
        return baseDamage*1.5;
    }

    /**
     * Calculates the damage received using the berserker strategy.
     * Damage received is increased by 50%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the increased damage received.
     */
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage*1.5;
    }

    public String getName(){
        return "Berserker";
    }
}
