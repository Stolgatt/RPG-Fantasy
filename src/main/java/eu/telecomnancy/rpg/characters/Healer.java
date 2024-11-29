package eu.telecomnancy.rpg.characters;

import java.util.Random;

public class Healer extends GameCharacter {
    private int wisdom;

    public Healer(String name) {
        super(name);
        wisdom = getLevel() * 10+new Random().nextInt(10);
        this.setHealth(100);
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }


}
