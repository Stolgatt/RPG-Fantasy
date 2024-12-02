package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.characters.team.Team;
import eu.telecomnancy.rpg.characters.team.TeamBuilder;
import eu.telecomnancy.rpg.characters.Warrior;
import eu.telecomnancy.rpg.characters.Wizard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TeamBuilderTest {

    @Test
    void testTeamBuilderCreatesTeam() {
        Team team = new TeamBuilder("Test Team")
                .addPlayer(new Warrior("Warrior"))
                .addPlayer(new Wizard("Wizard"))
                .build();

        assertEquals("Test Team", team.getName());
        assertEquals(2, team.size());
        assertTrue(team.containsPlayer("Warrior"));
        assertTrue(team.containsPlayer("Wizard"));
    }

}
