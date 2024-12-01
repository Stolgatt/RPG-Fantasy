package eu.telecomnancy.rpg.characters;

import java.util.Random;


public class Wizard extends GameCharacter {

    private int intelligence;

    public Wizard(String name) {
        super(name);
        intelligence = getLevel() * 10+new Random().nextInt(10);
        this.setHealth(150);
    }

    private Wizard(Wizard source) {
        super(source.getName());
        this.intelligence = source.getIntelligence();
        this.setHealth(source.getHealth());
        this.setLevel(source.getLevel());
    }

    @Override
    public Wizard clone() {
        return new Wizard(this);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    
    
}
