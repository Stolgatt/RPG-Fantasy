package eu.telecomnancy.rpg.characters;

import eu.telecomnancy.rpg.characters.visitors.Visitor;

public interface Visitable {

    public void accept(Visitor visitor);
}
