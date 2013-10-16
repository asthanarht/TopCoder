package srm594;

public class AstronomicalRecords {
    public int minimalPlanets(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int min = m + n;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                long lcm = lcm(A[i], B[j]);
                long coeA = lcm / (long) A[i], coeB = lcm / (long) B[j];
                long[] Ac = new long[m];
                for (int k = 0; k < m; k++)
                    Ac[k] = A[k] * coeA;
                long[] Bc = new long[n];
                for (int k = 0; k < n; k++)
                    Bc[k] = B[k] * coeB;
                min = Math.min(min, m + n - lcs(Ac, Bc));
            }
        return min;

    }

    // largest common subsequence
    private int lcs(long[] A, long[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (A[i] == B[j])
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                else
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
        return dp[m][n];
    }

    private long lcm(long a, long b) {
        long gcd = gcd(a, b);
        return a * b / gcd;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long hold = b;
            b = a % b;
            a = hold;
        }
        return a;
    }
}
