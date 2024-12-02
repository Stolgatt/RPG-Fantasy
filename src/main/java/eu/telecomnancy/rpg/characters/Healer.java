package eu.telecomnancy.rpg.characters;

import eu.telecomnancy.rpg.characters.visitors.Visitor;

import java.util.Random;

public class Healer extends GameCharacter {
    private int wisdom;

    public Healer(String name) {
        super(name);
        wisdom = getLevel() * 10+new Random().nextInt(10);
        this.setHealth(100);
    }

    private Healer(Healer source) {
        super(source.getName());
        this.wisdom = source.wisdom;
        this.setHealth(source.getHealth());
        this.setLevel(source.getLevel());
    }

    @Override
    public Healer clone() {
        return new Healer(this);
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitHealer(this);
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }


}
