package pd1;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Scoreboard {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int playerCount;
        do {
            System.out.println("Podaj liczbę graczy (2-10): ");
            playerCount = sc.nextInt();

        } while (playerCount < 2 || playerCount > 10);
        Player[] players = new Player[playerCount];

        for (int i = 0; i < playerCount; i++) {
            System.out.println("Gracz numer: " + (i + 1));
            System.out.println("Podaj imię gracza: ");
            String name = sc.next();

            int[] scores = new int[3];
            for (int j = 0; j < 3; j++) {
                System.out.println("Podaj Wynik: " + (j + 1));
                scores[j] = sc.nextInt();
            }
            Player player = new Player(name, scores);
            players[i] = player;
        }
        for (int i = 0; i < players.length - 1; i++) {

            for (int j = 0; j < players.length - 1; j++) {

                if (players[j].getTotalScore() < players[j + 1].getTotalScore()) {
                    Player temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }
            }
        }
        int highestSingleScore = ScoreAnalyzer.findHighestSingleScore(players);

        LeaderboardPrinter.printLeaderboard(players, highestSingleScore);
    }
}



