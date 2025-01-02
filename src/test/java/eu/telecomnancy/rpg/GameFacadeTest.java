package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.App.GameFacade;
import eu.telecomnancy.rpg.characters.decorator.ArmoredDecorator;
import eu.telecomnancy.rpg.characters.decorator.CharacterDecorator;
import eu.telecomnancy.rpg.characters.decorator.InvincibleDecorator;
import eu.telecomnancy.rpg.characters.factory.*;
import eu.telecomnancy.rpg.characters.team.Team;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class GameFacadeTest {
    static GameFacade facade;

    @BeforeAll
    static void setUp() {
        // Simulate user entries : difficulty = 2 et max team size = 5
        String simulatedInput = "2\n5\n";                           // Simulated Input because Intellij Idea Terminal is in read-only
        Scanner testScanner = new Scanner(simulatedInput);          // If used from an external terminal, comment this line and use the following one
        //Scanner testScanner = null;

        facade = new GameFacade(testScanner);
    }

    @Test
    void testGameConfiguration() {
        GameConfiguration config = GameConfiguration.getGameConfiguration();
        assertEquals(2, config.getDifficulty()); // Vérifie la difficulté configurée
        assertEquals(5, config.getMaxSizeTeam()); // Vérifie la taille max de l'équipe
    }

    @Test
    void testAddTeam() {
        facade.addTeam("TestTeam", "Balanced", null);

        assertTrue(facade.getTeams().containsKey("TestTeam"));
    }

    @Test
    void testRemoveTeam() {
        facade.addTeam("TestTeam", "Balanced", null);

        facade.removeTeam("TestTeam");

        assertFalse(facade.getTeams().containsKey("TestTeam"));
    }

    @Test
    void testAttack() {
        facade.addTeam("Team1", "Balanced", null);
        facade.addTeam("Team2", "Balanced", null);

        facade.attack("Team1", "Strong Warrior", "Team2", "Smart Wizard", 20);

        Team team2 = facade.getTeams().get("Team2");
        assertTrue(team2.getPlayer("Smart Wizard").getHealth() < 150);
    }

    @Test
    void testHealTeam() {
        facade.addTeam("TestTeam", "Balanced", null);

        Team team = facade.getTeams().get("TestTeam");
        CharacterInterface wa = team.getPlayer("Strong Warrior");
        CharacterInterface healer = team.getPlayer("Kind Healer");
        CharacterInterface wizard = team.getPlayer("Smart Wizard");
        double waHealth = ((Warrior) wa).getHealth();
        double heHealth = ((Healer) healer).getHealth();
        double wiHealth = ((Wizard) wizard).getHealth();

        int heal = 10;
        facade.healTeam("TestTeam", heal);
        wa = team.getPlayer("Strong Warrior");
        healer = team.getPlayer("Kind Healer");
        wizard = team.getPlayer("Smart Wizard");
        double newWaHealth = ((Warrior) wa).getHealth();
        double newHeHealth = ((Healer) healer).getHealth();
        double newWiHealth = ((Wizard) wizard).getHealth();
        assertEquals(newWaHealth, waHealth + heal);
        assertEquals(newHeHealth, heHealth + heal);
        assertEquals(newWiHealth, wiHealth + heal);
    }

    @Test
    void testAddArmor() {
        facade.addTeam("TestTeam", "Balanced", null);

        facade.addArmor("TestTeam", "Strong Warrior", 0.5);

        Team team = facade.getTeams().get("TestTeam");
        assertInstanceOf(ArmoredDecorator.class, team.getPlayer("Strong Warrior"));
    }

    @Test
    void testMakeInvincible() {
        facade.addTeam("TestTeam", "Balanced", null);

        facade.makeInvincible("TestTeam", "Strong Warrior");

        Team team = facade.getTeams().get("TestTeam");
        assertInstanceOf(InvincibleDecorator.class, team.getPlayer("Strong Warrior"));
    }

    @Test
    void testRemoveDecorator() {
        facade.addTeam("TestTeam", "Balanced", null);

        facade.addArmor("TestTeam", "Strong Warrior", 0.5);
        facade.removeDecorator("TestTeam", "Strong Warrior");

        Team team = facade.getTeams().get("TestTeam");
        assertFalse(team.getPlayer("String Warrior") instanceof CharacterDecorator);
    }

    @Test
    void testBuffTeam() {
        facade.addTeam("TestTeam", "Balanced", null);

        Team team = facade.getTeams().get("TestTeam");
        CharacterInterface wa = team.getPlayer("Strong Warrior");
        CharacterInterface healer = team.getPlayer("Kind Healer");
        CharacterInterface wizard = team.getPlayer("Smart Wizard");
        int strength = ((Warrior) wa).getStrength();
        int wisdom = ((Healer) healer).getWisdom();
        int intelligence = ((Wizard) wizard).getIntelligence();

        int buff = 5;
        facade.buffTeam("TestTeam", buff);
        wa = team.getPlayer("Strong Warrior");
        healer = team.getPlayer("Kind Healer");
        wizard = team.getPlayer("Smart Wizard");
        int newStrength = ((Warrior) wa).getStrength();
        int newWisdom = ((Healer) healer).getWisdom();
        int newIntelligence = ((Wizard) wizard).getIntelligence();
        assertEquals(newStrength, strength + buff);
        assertEquals(newWisdom, wisdom + buff);
        assertEquals(newIntelligence, intelligence + buff);
    }

    @Test
    void testMemoryCleanup() {
        facade.addTeam("TestTeam", "Balanced", null);

        facade.removeTeam("TestTeam");

        // Forcer le GC et attendre
        System.gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {}

        assertNull(facade.getTeams().get("TestTeam"));
    }

}
