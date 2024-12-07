package eu.telecomnancy.rpg.characters;

import eu.telecomnancy.rpg.characters.strategy.NeutralStrategy;
import eu.telecomnancy.rpg.characters.visitors.Visitor;

import java.util.Random;

public class Warrior extends GameCharacter {
    private int strength;

    public Warrior(String name) {
        super(name);
        strength = getLevel() * 10+new Random().nextInt(10);
        this.setHealth(200);
        this.setCombatStrategy(new NeutralStrategy());
    }

    private Warrior(Warrior source){
        super(source.getName());
        this.strength = source.getStrength();
        this.setHealth(source.getHealth());
        this.setLevel(source.getLevel());
        this.setCombatStrategy(new NeutralStrategy()); //Le clone commencera avec une stratégie Neutre initialement
    }

    @Override
    public Warrior clone(){
        return new Warrior(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitWarrior(this);
    }


    public int getStrength() {
        return strength;
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    
}
