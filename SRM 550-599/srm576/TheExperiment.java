public class TheExperiment {
    public int countPlacements(String[] intensity, int M, int L, int A, int B) {
        String intensitys = "";
        for (int i = 0; i < intensity.length; i++)
            intensitys += intensity[i];
        int n = intensitys.length();
        int[] drop = new int[n];
        for (int i = 0; i < n; i++)
            drop[i] = intensitys.charAt(i) - '0';
        long mod = 1000000009;

        // sum[i][j] == sum[i,j).isValid()
        boolean[][] sum = new boolean[n][n + 1];
        for (int start = 0; start < n; start++) {
            int num = 0;
            for (int end = start; end < n; end++) {
                num += drop[end];
                if (num >= A && num <= B)
                    sum[start][end + 1] = true;
            }
        }

        // at least one full length sponge in each group (overlapped sponges)
        long[][][] dp = new long[n + 1][M + 1][2]; // count[end][m][valid]
        for (int end = 0; end <= n; end++)
            dp[end][0][0] = 1;
        for (int curEnd = 0; curEnd < n; curEnd++)
            for (int m = 0; m < M; m++) {
                // mod here guarantees that no overflow
                // maximum mod * 300 * 300
                dp[curEnd][m][0] %= mod;
                dp[curEnd][m][1] %= mod;
                // end the current group
                for (int end = curEnd + 1; end <= n; end++) {
                    dp[end][m][0] += dp[curEnd][m][1];
                    // dp[end][m][0] %= mod;
                }
                // continue the current group
                for (int end = curEnd + 1, len = 1; end <= n && len <= L; end++, len++)
                    if (sum[curEnd][end]) {
                        if (len == L) {
                            dp[end][m + 1][1] += dp[curEnd][m][0]
                                    + dp[curEnd][m][1];
                            // dp[end][m + 1][1] %= mod;
                        }
                        else {
                            dp[end][m + 1][0] += dp[curEnd][m][0];
                            // dp[end][m + 1][0] %= mod;
                            dp[end][m + 1][1] += dp[curEnd][m][1];
                            // dp[end][m + 1][1] %= mod;
                        }
                    }
            }

        long result = 0;
        for (int end = 0; end <= n; end++) {
            result += dp[end][M][1];
            result %= mod;
        }
        return (int) result;
    }
}
