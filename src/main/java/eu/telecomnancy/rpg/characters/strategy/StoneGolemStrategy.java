package eu.telecomnancy.rpg.characters.strategy;

public class StoneGolemStrategy implements CombatStrategy {
    public double calculateDamageDealt(double baseDamage){
        return baseDamage*0.4;
    }
    public double calculateDamageReceived(double baseDamage){
        return  baseDamage*0.4;
    }


}
