import java.math.BigInteger;
import java.util.HashSet;

public class CuttingBitString {
    public int getmin(String S) {
        int n = S.length();
        HashSet<String> segs = new HashSet<String>();
        for (long i = 1; i <= Long.MAX_VALUE / 5; i *= 5)
            segs.add(BigInteger.valueOf(i).toString(2));
        boolean[][] check = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                if (segs.contains(S.substring(i, j + 1)))
                    check[i][j] = true;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = (check[0][i] ? 1 : 0);
            for (int j = 0; j < i; j++)
                if (dp[j] > 0 && check[j + 1][i]) {
                    int num = dp[j] + 1;
                    if (dp[i] == 0 || dp[i] > num)
                        dp[i] = num;
                }
        }
        return dp[n - 1] == 0 ? -1 : dp[n - 1];
    }
}
