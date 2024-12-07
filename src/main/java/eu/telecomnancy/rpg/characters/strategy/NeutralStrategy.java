package eu.telecomnancy.rpg.characters.strategy;

public class NeutralStrategy implements CombatStrategy {

    public double calculateDamageDealt(double baseDamage){
        return baseDamage;
    }
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage;
    }
}
