package eu.telecomnancy.rpg.characters;

public class TeamDirector {

    // Méthode pour construire une équipe équilibrée
    public Team constructBalancedTeam() {
        return new TeamBuilder("Balanced Team")
                .addPlayer(new Warrior("Strong Warrior"))
                .addPlayer(new Wizard("Smart Wizard"))
                .addPlayer(new Healer("Kind Healer"))
                .build();
    }

    // Méthode pour construire une équipe uniquement composée de combattants
    public Team constructCombatTeam() {
        return new TeamBuilder("Combat Team")
                .addPlayer(new Warrior("Fighter 1"))
                .addPlayer(new Warrior("Fighter 2"))
                .addPlayer(new Warrior("Fighter 3"))
                .build();
    }

    // Méthode pour construire une équipe uniquement composée de Wizard
    public Team constructWizardTeam() {
        return new TeamBuilder("Magic Team")
                .addPlayer(new Wizard("Wizard 1"))
                .addPlayer(new Wizard("Wizard 2"))
                .addPlayer(new Wizard("Wizard 3"))
                .build();
    }
}
