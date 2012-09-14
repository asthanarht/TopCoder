package srm408;

public class TournamentJudging {

    public int getPoints(int[] rawScores, int[] conversionFactor) {

        int score = 0;
        int n1;
        int n2;
        int s;
        double num;
        double half = 0.5;

        for (int i = 0; i < rawScores.length; i++) {
            n1 = rawScores[i];
            n2 = conversionFactor[i];
            s = n1 / n2;
            score += s;
            num = n1 / (double) n2;
            if (num - s >= half)
                score++;
        }

        return score;

    }

}
