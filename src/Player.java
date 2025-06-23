import java.util.Scanner; 

public class Player {
    private String name;
    private int gamesWon;
    private int gamesLost;
    private int score;

    public Player() {
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.score = 0;
    }

    public String setName(Scanner scan) {
        this.name = scan.nextLine(); 
        return this.name;
    }

    public void incrementGamesWon() {
        this.gamesWon++;
    }

    public void incrementGamesLost() {
        this.gamesLost++;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void displayStats() {
        System.out.println("\n--- Estatísticas do Jogador ---");
        System.out.println("Nome: " + this.name);
        System.out.println("Jogos Ganhos: " + this.gamesWon);
        System.out.println("Jogos Perdidos: " + this.gamesLost);
        System.out.println("Pontuação Total: " + this.score);
        System.out.println("--------------------------------");
    }
}