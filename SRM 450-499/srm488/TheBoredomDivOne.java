package srm488;

public class TheBoredomDivOne {
    public double find(int n, int m) {
        int total = n + m;
        double[] dp = new double[total + 2];
        for (int x = 1; x <= m; x++) {
            double all = total * (total - 1) / 2;
            double allbored = (total - x) * (total - x - 1) / 2;
            double allnot = x * (x - 1) / 2;
            double half = all - allbored - allnot;
            dp[x + 2] = (1 + half / all * dp[x + 1] + allnot / all * dp[x])
                    / (1 - allbored / all);
        }
        return dp[m + 2];
    }
}
