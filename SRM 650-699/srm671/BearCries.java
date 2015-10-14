public class BearCries {

    public int count(String message) {
        n = message.length();
        str = message.toCharArray();
        for (int i = 0; i < n; i++)
            if (str[i] == ';')
                m++;
        if (m < 2 || m % 2 == 1 || str[0] == '_')
            return 0;
        m /= 2;
        dp = new long[n][m + 1][m + 1];
        dp[0][1][0] = 1;
        for (int i = 0; i + 1 < n; i++)
            for (int s = 0; s <= m; s++)
                for (int h = 0; h <= m; h++)
                    if (str[i + 1] == ';') {
                        if (s < m)
                            update(i + 1, s + 1, h, dp[i][s][h]);
                        if (h > 0)
                            update(i + 1, s, h - 1, dp[i][s][h] * h);
                    }
                    else {
                        if (h > 0)
                            update(i + 1, s, h, dp[i][s][h] * h);
                        if (s > 0 && h < m)
                            update(i + 1, s - 1, h + 1, dp[i][s][h] * s);
                    }
        return (int) dp[n - 1][0][0];
    }

    private int n;
    private char[] str;
    private int m = 0;
    private long[][][] dp;
    private long mod = 1000000007;

    private void update(int i, int s, int h, long add) {
        dp[i][s][h] += add;
        dp[i][s][h] %= mod;
    }

}
