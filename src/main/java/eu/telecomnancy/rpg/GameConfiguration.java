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
    private int maxSizeTeam; // Maximum team size chosen by the player
    private final int maxSizeTeamAllowed = 10; // Maximum allowed team size

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
            instance.setDifficulty(instance.getMinDifficulty());
            instance.setMaxSizeTeam(instance.getMaxSizeTeamAllowed());
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
        System.err.println("Error : Difficulty " + difficulty + " out of bounds (" + this.minDifficulty + "-" + this.maxDifficulty + ")");
       }
    }

    public void setMaxSizeTeam(int maxSizeTeam) {
        if (maxSizeTeam > 0 && maxSizeTeam <= this.maxSizeTeamAllowed) {
            this.maxSizeTeam = maxSizeTeam;
        }
        else{
            System.err.println("Error : maximum size of a team should be between 0 and " + (this.maxSizeTeamAllowed) +
                    ".\nPlease enter a valid integer.");
        }
    }

    //Getter
    public int getDifficulty() {
        return this.difficulty;
    }
    public int getMaxSizeTeam() {
        return this.maxSizeTeam;
    }

    public int getMaxSizeTeamAllowed(){
        return this.maxSizeTeamAllowed;
    }

    public int getMinDifficulty() {
        return this.minDifficulty;
    }
    public int getMaxDifficulty() {
        return this.maxDifficulty;
    }
}
