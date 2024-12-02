package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.*;
import eu.telecomnancy.rpg.characters.team.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    @Test
    void testCreateTeam() {
        Team team = new Team("test");
        assertEquals(0, team.size());
    }

    @Test void AddCharacterToTeam() {
        Team team = new Team("test");
        Wizard character = new Wizard("test");
        team.addPlayer(character);
        assertEquals(1, team.size());
    }

    @Test void RemoveCharacterFromTeam() {
        Team team = new Team("test");
        Wizard character = new Wizard("test");
        team.addPlayer(character);
        assertEquals(1, team.size());
        team.removePlayer(character);
        assertEquals(0, team.size());
    }

    @Test void RemoveCharacterFromTeam2() {
        Team team = new Team("test");
        Wizard character = new Wizard("test");
        team.addPlayer(character);
        Wizard character2 = new Wizard("test2");
        team.addPlayer(character2);
        assertEquals(2, team.size());
        team.removePlayer(character);
        assertEquals(1, team.size());
        team.removePlayer("test2");
        assertEquals(0, team.size());
    }

    @Test
    public void testTeamCloneCreatesNewInstance() {
        Team original = new Team("Team Alpha");
        original.addPlayer(new Wizard("Wizard"));

        Team clone = original.clone();

        assertNotSame(original, clone);

        assertEquals(original.getName(), clone.getName());
        assertEquals(original.getPlayers().size(), clone.getPlayers().size());

        assertTrue(original.getPlayers().containsAll(clone.getPlayers()));
        assertTrue(clone.getPlayers().containsAll(original.getPlayers()));
    }

    @Test
    public void testCloneIndependence() {
        Team original = new Team("Team Alpha");
        original.addPlayer(new Warrior("Hero"));

        Team clone = original.clone();
        clone.setName("Team Beta");
        clone.getPlayer("Hero").setName("Villain");

        assertEquals("Team Alpha", original.getName());
        assertNotNull(original.getPlayer("Hero"));
        assertEquals("Hero", original.getPlayer("Hero").getName());
    }

    @Test
    public void testCloneDeepCopy() {
        Team original = new Team("Team Alpha");
        original.addPlayer(new Healer("Hero"));

        Team clone = original.clone();

        clone.addPlayer(new Warrior("Sidekick"));

        assertEquals(1, original.getPlayers().size());
        assertEquals(2, clone.getPlayers().size());
    }

    @Test
    public void testClonePlayerReferences() {
        Team original = new Team("Original Team");
        Healer healer = new Healer("Healer");
        original.addPlayer(healer);

        Team clone = original.clone();

        GameCharacter originalPlayer = original.getPlayer("Healer");
        GameCharacter clonedPlayer = clone.getPlayer("Healer");

        // Vérifie que ce sont des objets différents
        assertNotSame(originalPlayer, clonedPlayer);

        // Vérifie que leurs attributs sont identiques
        assertEquals(originalPlayer.getName(), clonedPlayer.getName());
        assertEquals(originalPlayer.getHealth(), clonedPlayer.getHealth());
    }

    @Test
    public void testClonePlayerListIndependence() {
        Team original = new Team("Original Team");
        original.addPlayer(new Wizard("Mage"));

        Team clone = original.clone();

        clone.addPlayer(new Warrior("Warrior"));

        // L'équipe originale ne doit pas être affectée
        assertEquals(1, original.getPlayers().size());
        assertEquals(2, clone.getPlayers().size());
    }





}