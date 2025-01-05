package eu.telecomnancy.rpg;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameConfigurationTest {

    @Test
    public void testSingletonInstance() {
        GameConfiguration config1 = GameConfiguration.getGameConfiguration();
        GameConfiguration config2 = GameConfiguration.getGameConfiguration();

        assertSame(config1, config2);
    }

    @Test
    public void testSetAndGetLevelWithinLimits() {
        GameConfiguration config = GameConfiguration.getGameConfiguration();
        config.setDifficulty(2);

        assertEquals(2, config.getDifficulty());
    }

    @Test
    public void testSetLevelOutOfBounds() {
        GameConfiguration config = GameConfiguration.getGameConfiguration();
        int currentLevel = config.getDifficulty();

        config.setDifficulty(0);
        assertEquals(config.getDifficulty(), currentLevel);
        config.setDifficulty(4);
        assertEquals(config.getDifficulty(), currentLevel);
    }

    @Test
    public void testSetAndGetMaxSizeTeam() {
        GameConfiguration config = GameConfiguration.getGameConfiguration();
        config.setMaxSizeTeam(5);

        assertEquals(5, config.getMaxSizeTeam());
    }
}
