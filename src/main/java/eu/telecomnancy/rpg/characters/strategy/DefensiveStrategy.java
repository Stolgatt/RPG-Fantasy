package eu.telecomnancy.rpg.characters.strategy;

/**
 * A defensive combat strategy where characters deal less damage
 * but also take less damage.
 * This strategy is ideal for characters prioritizing survival over offense.
 */
public class DefensiveStrategy implements CombatStrategy {

    /**
     * Calculates the damage dealt using the defensive strategy.
     * Damage dealt is reduced by 20%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the reduced damage dealt.
     */
    public double calculateDamageDealt(double baseDamage){
        return baseDamage*0.8;
    }

    /**
     * Calculates the damage received using the defensive strategy.
     * Damage received is reduced by 20%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the reduced damage received.
     */
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage*0.8;
    }

    public String getName(){
        return "Defensive";
    }
}
