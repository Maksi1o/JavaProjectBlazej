package pd1;

public class ScoreAnalyzer {
    public static int findHighestSingleScore(Player[] players) {
        int highestSingleScore = players[0].getMaxScore();
        for (Player player : players) {
            if (player.getMaxScore() > highestSingleScore) {
                highestSingleScore = player.getMaxScore();
            }
        }
        return highestSingleScore;
    }
}
