package eu.telecomnancy.rpg.characters.strategy;

/**
 * A stone golem combat strategy where characters deal minimal damage
 * but also take minimal damage.
 * This strategy is ideal for tank-like characters that absorb damage.
 */
public class StoneGolemStrategy implements CombatStrategy {

    /**
     * Calculates the damage dealt using the stone golem strategy.
     * Damage dealt is reduced by 60%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the significantly reduced damage dealt.
     */
    public double calculateDamageDealt(double baseDamage){
        return baseDamage*0.4;
    }

    /**
     * Calculates the damage received using the stone golem strategy.
     * Damage received is reduced by 60%.
     *
     * @param baseDamage the base damage before applying any modifiers.
     * @return the significantly reduced damage received.
     */
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage*0.4;
    }


}
