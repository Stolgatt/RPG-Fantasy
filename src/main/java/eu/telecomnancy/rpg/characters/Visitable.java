package eu.telecomnancy.rpg.characters;

import eu.telecomnancy.rpg.characters.visitors.Visitor;


/**
 * Interface for objects that can be visited by a Visitor.
 * This interface is a core part of the Visitor pattern, enabling the
 * separation of algorithms from the objects on which they operate.
 * Classes implementing `Visitable` allow external operations to be performed
 * on their instances without changing their structure or behavior.
 */
public interface Visitable {

    /**
     * Accepts a visitor and allows it to perform operations on the current instance.
     *
     * @param visitor the Visitor object that will perform operations on this instance.
     */
    public void accept(Visitor visitor);
}
