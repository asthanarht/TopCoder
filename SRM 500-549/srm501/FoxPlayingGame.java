public class FoxPlayingGame {

    public double theMax(int nA, int nB, int paramA, int paramB) {

        double sA = paramA / 1000.0;
        double sB = paramB / 1000.0;

        double score = 0;
        boolean ab = true;
        boolean multi1 = false;
        boolean multiodd = false;
        boolean multieven = false;

        if (sA == 0)
            return 0;

        if (sB >= 1) {
            if (sA < 0)
                ab = false;
        }
        else if (sB >= 0 && sB < 1) {
            if (sA > 0)
                ab = false;
        }
        else if (sB >= -1 && sB < 0) {
            ab = false;
            if (sA < 0)
                multi1 = true;
        }
        else if (sB < -1) {
            if (sA > 0)
                multieven = true;
            else
                multiodd = true;
        }

        if (ab) {
            for (int i = 0; i < nA; i++)
                score += sA;
            if (multiodd) {
                if (nB % 2 == 0)
                    nB--;
            }
            else if (multieven) {
                if (nB % 2 != 0)
                    nB--;
            }
            for (int i = 0; i < nB; i++)
                score *= sB;
        }
        else {
            for (int i = 0; i < nA; i++)
                score += sA;
            if (multi1 && nB > 0)
                score *= sB;
        }

        return score;
    }

}
