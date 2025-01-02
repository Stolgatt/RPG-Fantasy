package eu.telecomnancy.rpg.characters.observer;

/**
 * Defines the Observer interface for objects that need to be notified of changes.
 */
public interface Observer {
    /**
     * Method triggered when the observable state changes.
     */
    void update();

    String toString();
}
