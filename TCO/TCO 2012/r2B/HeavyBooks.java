package r2B;

import java.util.Arrays;

public class HeavyBooks {
    public int[] findWeight(int[] books, int[] moves) {
        int n = books.length, m = moves[0];

        // true if book[i/m] goes to tomek's backpack (greedy)
        boolean[] tomek = new boolean[m + 1];
        for (int turn = 1; turn < moves.length; turn++) {
            int count = 0;
            for (int i = m; i > 0 && count < moves[turn]; i--)
                if ((turn % 2 == 1 && !tomek[i]) || (turn % 2 == 0 && tomek[i])) {
                    tomek[i] = !tomek[i];
                    count++;
                }
        }

        Arrays.sort(books);
        // [n][m][0] : wojtek - tomek
        // [n][m][1] : wojtek + tomek
        int[][][] dp = new int[n + 1][m + 1][2];
        for (int i = 0; i <= n; i++)
            for (int j = 1; j <= m; j++)
                Arrays.fill(dp[i][j], Integer.MIN_VALUE);
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++) {
                if (tomek[j]) {
                    dp[i][j][0] = dp[i - 1][j - 1][0] - books[i - 1];
                    dp[i][j][1] = dp[i - 1][j - 1][1] + books[i - 1];
                }
                else {
                    dp[i][j][0] = dp[i - 1][j - 1][0] + books[i - 1];
                    dp[i][j][1] = dp[i - 1][j - 1][1] + books[i - 1];
                }
                if (dp[i - 1][j][1] > 0 // important
                        && (dp[i - 1][j][0] > dp[i][j][0] || (dp[i - 1][j][0] == dp[i][j][0] && dp[i - 1][j][1] > dp[i][j][1]))) {
                    dp[i][j][0] = dp[i - 1][j][0];
                    dp[i][j][1] = dp[i - 1][j][1];
                }
            }

        // result
        int[] result = new int[2];
        result[0] = (dp[n][m][1] - dp[n][m][0]) / 2;
        result[1] = (dp[n][m][1] + dp[n][m][0]) / 2;
        return result;
    }
}
