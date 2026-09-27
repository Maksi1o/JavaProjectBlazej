package pd1;

public class Player {
    private String name;
    private int[] scores;

    public Player(String name, int[] scores) {
        this.name = name;
        this.scores = scores;
    }
    public String getName() {
        return name;
    }
    public int getTotalScore() {
        int totalScore = 0;
        for (int score : scores) {
            totalScore += score;
        }
        return totalScore;
    }
    public double getAverageScore() {
        double averageCalculation = (double) getTotalScore() / (double) scores.length;
        return averageCalculation;
    }
    public int getMinScore() {
        int minScore = scores[0];
        for (int score : scores) {
            if (score < minScore) {
                minScore = score;
            }
        }
        return minScore;
    }
    public int getMaxScore() {
        int maxScore = scores[0];
        for (int score : scores) {
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }
}
