package eu.telecomnancy.rpg.characters.team;

import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;
import eu.telecomnancy.rpg.characters.observer.DeathObserver;
import eu.telecomnancy.rpg.characters.observer.LevelUpObserver;
import eu.telecomnancy.rpg.characters.factory.GameCharacter;

/**
 * A director class to create pre-configured teams using the TeamBuilder.
 * Implements the Director role in the Builder pattern.
 */
public class TeamDirector {

    /**
     * Adds necessary observers (DeathObserver and LevelUpObserver) to a given character.
     *
     * @param character The GameCharacter to which observers are added.
     */
    private void addObservers(GameCharacter character) {
        character.attach(new DeathObserver(character));      // Surveille la mort
        character.attach(new LevelUpObserver(character));    // Surveille les montées de niveau
    }

    /**
     * Constructs a balanced team consisting of a Warrior, Wizard, and Healer.
     * Each character is equipped with the necessary observers.
     *
     * @return a pre-configured balanced Team object.
     */
    public Team constructBalancedTeam() {
        TeamBuilder builder = new TeamBuilder("Balanced Team");

        // Créer les personnages et ajouter les observateurs
        Warrior warrior = new Warrior("Strong Warrior");
        addObservers(warrior);

        Wizard wizard = new Wizard("Smart Wizard");
        addObservers(wizard);

        Healer healer = new Healer("Kind Healer");
        addObservers(healer);

        // Ajouter les personnages à l'équipe
        return builder.addPlayer(warrior)
                .addPlayer(wizard)
                .addPlayer(healer)
                .build();
    }

    /**
     * Constructs a team composed entirely of Warriors.
     * Each Warrior is equipped with the necessary observers.
     *
     * @return a pre-configured combat Team object.
     */
    public Team constructCombatTeam() {
        TeamBuilder builder = new TeamBuilder("Combat Team");

        for (int i = 1; i <= 3; i++) {
            Warrior warrior = new Warrior("Fighter " + i);
            addObservers(warrior);
            builder.addPlayer(warrior);
        }

        return builder.build();
    }

    /**
     * Constructs a team composed entirely of Wizards.
     * Each Wizard is equipped with the necessary observers.
     *
     * @return a pre-configured wizard Team object.
     */
    public Team constructWizardTeam() {
        TeamBuilder builder = new TeamBuilder("Magic Team");

        for (int i = 1; i <= 3; i++) {
            Wizard wizard = new Wizard("Wizard " + i);
            addObservers(wizard);
            builder.addPlayer(wizard);
        }

        return builder.build();
    }
}
