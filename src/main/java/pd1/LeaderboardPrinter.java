package pd1;

public class LeaderboardPrinter {
    public static void printLeaderboard(Player[] players, int highestSingleScore) {
        System.out.println("\n========== LEADERBOARD ==========");
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            System.out.printf(
                    "%d. %s | SUMA: %d | ŚREDNIA: %.2f | MIN: %d | MAX: %d%n",
                    i + 1,
                    player.getName() + (player.getMaxScore() == highestSingleScore ? " *" : ""),
                    player.getTotalScore(),
                    player.getAverageScore(),
                    player.getMinScore(),
                    player.getMaxScore()
            );
        }
        System.out.println("\n* Najwyższy pojedynczy wynik turnieju");
    }
}
