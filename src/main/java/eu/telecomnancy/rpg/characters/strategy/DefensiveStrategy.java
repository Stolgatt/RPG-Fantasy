package eu.telecomnancy.rpg.characters.strategy;

public class DefensiveStrategy implements CombatStrategy {
    public double calculateDamageDealt(double baseDamage){
        return baseDamage*0.8;
    }
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage*0.8;
    }
}
