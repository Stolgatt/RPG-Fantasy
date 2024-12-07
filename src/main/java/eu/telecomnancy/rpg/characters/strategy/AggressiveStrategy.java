package eu.telecomnancy.rpg.characters.strategy;

public class AggressiveStrategy implements CombatStrategy {

    public double calculateDamageDealt(double baseDamage){
        return 1.3 * baseDamage;
    }
    public double calculateDamageReceived(double baseDamage){
        return  1.3 * baseDamage;
    }

}
