import java.util.Arrays;

public class YetAnotherCardGame {
    int n;
    int[][][][] dp = new int[51][51][51][51];

    public int maxCards(int[] petr, int[] snuke) {
        n = petr.length;
        Arrays.sort(petr);
        Arrays.sort(snuke);
        for (int i = 0; i <= 50; i++)
            for (int j = 0; j <= 50; j++)
                for (int k = 0; k <= 50; k++)
                    Arrays.fill(dp[i][j][k], 200);
        dp[0][0][0][0] = 0;
        go(petr, snuke, 0, 0, 0, 0);
        int max = 0;
        for (int pput = 0; pput <= 50; pput++)
            for (int peat = 0; peat <= 50; peat++)
                for (int sput = 0; sput <= 50; sput++)
                    for (int seat = 0; seat <= 50; seat++)
                        if (dp[pput][peat][sput][seat] > 0
                                && dp[pput][peat][sput][seat] < 200)
                            max = Math.max(max, pput + sput);
        return max;
    }

    private void go(int[] p, int[] s, int pput, int peat, int sput, int seat) {
        // snuke
        if (sput + seat < pput + peat) {
            // put
            for (int i = sput; i < n; i++)
                if (s[i] > dp[pput][peat][sput][seat])
                    if (s[i] < dp[pput][peat][sput + 1][seat]) {
                        dp[pput][peat][sput + 1][seat] = s[i];
                        go(p, s, pput, peat, sput + 1, seat);
                        break;
                    }
            // eat
            if (dp[pput][peat][sput][seat + 1] > dp[pput][peat][sput][seat]) {
                dp[pput][peat][sput][seat + 1] = dp[pput][peat][sput][seat];
                go(p, s, pput, peat, sput, seat + 1);
            }
        }
        // petr
        else if (pput + peat < n) {
            // put
            for (int i = pput; i < n; i++)
                if (p[i] > dp[pput][peat][sput][seat])
                    if (p[i] < dp[pput + 1][peat][sput][seat]) {
                        dp[pput + 1][peat][sput][seat] = p[i];
                        go(p, s, pput + 1, peat, sput, seat);
                        break;
                    }
            // eat
            if (dp[pput][peat + 1][sput][seat] > dp[pput][peat][sput][seat]) {
                dp[pput][peat + 1][sput][seat] = dp[pput][peat][sput][seat];
                go(p, s, pput, peat + 1, sput, seat);
            }
        }
    }

}
