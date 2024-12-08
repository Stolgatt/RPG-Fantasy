package eu.telecomnancy.rpg.characters;

/**
 * Interface for implementing the Prototype pattern.
 * Provides a method for cloning an object, allowing the creation of new instances
 * by duplicating existing ones.
 */
public interface Prototype {

    /**
     * Creates and returns a copy of the current object.
     *
     * @return a new instance that is a clone of the current object.
     */
    public Prototype clone();
}
