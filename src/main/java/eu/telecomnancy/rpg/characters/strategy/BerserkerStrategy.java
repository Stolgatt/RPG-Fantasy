package eu.telecomnancy.rpg.characters.strategy;

public class BerserkerStrategy implements CombatStrategy {
    public double calculateDamageDealt(double baseDamage){
        return baseDamage*1.5;
    }
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage*1.5;
    }
}
