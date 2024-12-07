package eu.telecomnancy.rpg;

public class GameConfiguration {
    private static GameConfiguration instance;

    private int difficulty;
    private final int maxDifficulty = 3;
    private final int minDifficulty = 1;
    private int maxSizeTeam;

    //Builder
    private GameConfiguration(){
    }
    public static GameConfiguration getGameConfiguration() {
        if (GameConfiguration.instance == null) {
            GameConfiguration.instance = new GameConfiguration();
        }
        return GameConfiguration.instance;
    }

    //Setter
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
