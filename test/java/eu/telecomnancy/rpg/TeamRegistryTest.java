package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.factory.Healer;
import eu.telecomnancy.rpg.characters.factory.Warrior;
import eu.telecomnancy.rpg.characters.factory.Wizard;
import eu.telecomnancy.rpg.characters.team.Team;
import eu.telecomnancy.rpg.characters.team.TeamRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamRegistryTest {

    private TeamRegistry registry;

    @BeforeEach
    void setUp() {
        registry = new TeamRegistry();

        // Crée et enregistre des prototypes d'équipes
        Team alphaTeam = new Team("Alpha");
        alphaTeam.addPlayer(new Wizard("Wizard Alpha"));
        alphaTeam.addPlayer(new Warrior("Warrior Alpha"));

        Team betaTeam = new Team("Beta");
        betaTeam.addPlayer(new Healer("Healer Beta"));

        registry.registerPrototype("teamAlpha", alphaTeam);
        registry.registerPrototype("teamBeta", betaTeam);
    }

    @Test
    void testRegisterPrototypeAndRetrieve() {
        Team clonedAlpha = registry.createClone("teamAlpha");
        assertNotNull(clonedAlpha);
        assertEquals("Alpha", clonedAlpha.getName());
        assertEquals(2, clonedAlpha.getPlayers().size());
        assertTrue(clonedAlpha.containsPlayer("Wizard Alpha"));
        assertTrue(clonedAlpha.containsPlayer("Warrior Alpha"));

        Team clonedBeta = registry.createClone("teamBeta");
        assertNotNull(clonedBeta);
        assertEquals("Beta", clonedBeta.getName());
        assertEquals(1, clonedBeta.getPlayers().size());
        assertTrue(clonedBeta.containsPlayer("Healer Beta"));
    }

    @Test
    void testCreateCloneReturnsNewInstance() {
        Team clonedAlpha1 = registry.createClone("teamAlpha");
        Team clonedAlpha2 = registry.createClone("teamAlpha");

        assertNotSame(clonedAlpha1, clonedAlpha2);
        assertEquals(clonedAlpha1.getName(), clonedAlpha2.getName());
        assertEquals(clonedAlpha1.getPlayers().size(), clonedAlpha2.getPlayers().size());
    }

    @Test
    void testCloneIndependence() {
        Team original = registry.createClone("teamAlpha");
        Team clone = registry.createClone("teamAlpha");

        // Modifie le clone
        clone.setName("Modified Alpha");
        clone.getPlayer("Wizard Alpha").setName("Modified Wizard");

        // Vérifie que l'original reste inchangé
        assertEquals("Alpha", original.getName());
        assertNotNull(original.getPlayer("Wizard Alpha"));
        assertEquals("Wizard Alpha", original.getPlayer("Wizard Alpha").getName());
    }

    @Test
    void testPrototypeNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            registry.createClone("nonexistentTeam");
        });

        assertEquals("Team not found for key: nonexistentTeam", exception.getMessage());
    }

    @Test
    void testRegistryWithMultipleTeams() {
        Team clonedAlpha = registry.createClone("teamAlpha");
        Team clonedBeta = registry.createClone("teamBeta");

        assertEquals("Alpha", clonedAlpha.getName());
        assertEquals(2, clonedAlpha.getPlayers().size());
        assertEquals("Beta", clonedBeta.getName());
        assertEquals(1, clonedBeta.getPlayers().size());
    }

    @Test
    void testCloneDeepCopy() {
        Team original = registry.createClone("teamAlpha");
        Team clone = registry.createClone("teamAlpha");

        // Ajoute un nouveau joueur à l'équipe clonée
        clone.addPlayer(new Warrior("New Warrior"));

        // Vérifie que l'original reste inchangé
        assertEquals(2, original.getPlayers().size());
        assertEquals(3, clone.getPlayers().size());
    }
}
