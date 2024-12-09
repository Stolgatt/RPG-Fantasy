package eu.telecomnancy.rpg.characters.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the Observable interface for objects that can be observed.
 */
public interface Observable {
    /**
     * Attach an observer to this observable object.
     * @param observer the observer to attach
     */
    void attach(Observer observer);

    /**
     * Detach an observer from this observable object.
     * @param observer the observer to detach
     */
    void detach(Observer observer);

    /**
     * Notify all attached observers of a change.
     */
    void notifyObservers();
}
