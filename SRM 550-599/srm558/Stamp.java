package srm558;

public class Stamp {
    public int getMinimumCost(String desiredColor, int stampCost, int pushCost) {
        int n = desiredColor.length();
        int min = Integer.MAX_VALUE;
        for (int L = n; L > 0; L--) {
            int cost = stampCost * L;
            int count = test(L, desiredColor);
            cost += count * pushCost;
            min = Math.min(min, cost);
        }
        return min;
    }

    private int test(int L, String desiredColor) {
        char[] color = { 'R', 'G', 'B' };
        int n = desiredColor.length();
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 3; j++)
                dp[i][j] = n;
        for (int i = n - L; i >= 0; i--) {
            for (int c = 0; c < 3; c++) {
                boolean ok = true;
                for (int j = i; j < i + L; j++)
                    if (desiredColor.charAt(j) != color[c]
                            && desiredColor.charAt(j) != '*')
                        ok = false;
                if (ok) {
                    for (int k = 0; k < 3; k++)
                        dp[i][c] = Math.min(dp[i][c], dp[i + L][k] + 1);
                    for (int k = i + 1; k < i + L; k++)
                        dp[i][c] = Math.min(dp[i][c], dp[k][c] + 1);
                }
            }
        }
        int min = Math.min(dp[0][0], dp[0][1]);
        min = Math.min(min, dp[0][2]);
        return min;
    }
}