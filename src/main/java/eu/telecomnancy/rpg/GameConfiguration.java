package eu.telecomnancy.rpg;

public class GameConfiguration {
    private static GameConfiguration instance;

    private int level;
    private final int maxLevel = 3;
    private final int minLevel = 1;
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
    public void setLevel(int level) {
        if (level <= this.maxLevel && level >= this.minLevel) {
            this.level = level;
        }
       else {
        System.err.println("Erreur : Niveau " + level + " hors des limites (" + this.minLevel + "-" + this.maxLevel + ")");
       }
    }
    public void setMaxSizeTeam(int maxSizeTeam) {
        this.maxSizeTeam = maxSizeTeam;
    }

    //Getter
    public int getLevel() {
        return this.level;
    }
    public int getMaxSizeTeam() {
        return this.maxSizeTeam;
    }
}
