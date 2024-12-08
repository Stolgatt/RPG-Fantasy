package eu.telecomnancy.rpg;

/**
 * Singleton class for managing the game's global configuration.
 * Holds settings such as difficulty level and maximum team size, ensuring
 * that only one instance of the configuration exists throughout the application.
 */
public class GameConfiguration {
    private static GameConfiguration instance;

    private int difficulty; // current game difficulty level
    private final int maxDifficulty = 3; // Maximum allowed difficulty level
    private final int minDifficulty = 1; // Minimum allowed difficulty level
    private int maxSizeTeam; // Maximum allowed team size

    //constructor
    /**
     * Private constructor to prevent instantiation from outside.
     * Use getGameConfiguration() to access the single instance.
     */
    private GameConfiguration(){}

    /**
     * Retrieves the singleton instance of the GameConfiguration
     * Creating it in the case the instance doesn't exist yet.
     *
     * @return the single instance of GameConfiguration.
     */
    public static GameConfiguration getGameConfiguration() {
        if (GameConfiguration.instance == null) {
            GameConfiguration.instance = new GameConfiguration();
        }
        return GameConfiguration.instance;
    }

    //Setter
    /**
     * Sets the difficulty level for the game.
     * Ensures the value is within the allowed range.
     * If not, the state of the configuration doesn't change.
     *
     * @param difficulty the desired difficulty level.
     */
    public void setDifficulty(int difficulty) {
        if (difficulty <= this.maxDifficulty && difficulty >= this.minDifficulty) {
            this.difficulty = difficulty;
        }
       else {
        System.err.println("Erreur : Niveau " + difficulty + " hors des limites (" + this.minDifficulty + "-" + this.maxDifficulty + ")");
       }
    }

    public void setMaxSizeTeam(int maxSizeTeam) {
        this.maxSizeTeam = maxSizeTeam;
    }

    //Getter
    public int getDifficulty() {
        return this.difficulty;
    }
    public int getMaxSizeTeam() {
        return this.maxSizeTeam;
    }
}
