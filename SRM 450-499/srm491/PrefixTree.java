import java.util.Arrays;

public class PrefixTree {
    public int getNumNodes(String[] words) {
        int n = words.length;
        int[][] count = new int[n][26];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < words[i].length(); j++)
                count[i][words[i].charAt(j) - 'a']++;
        int[] prefix = new int[1 << n];
        for (int mask = 0; mask < (1 << n); mask++) {
            int[] min = new int[26];
            Arrays.fill(min, Integer.MAX_VALUE);
            for (int i = 0; i < n; i++)
                if (((1 << i) & mask) > 0)
                    for (int j = 0; j < 26; j++)
                        min[j] = Math.min(min[j], count[i][j]);
            for (int j = 0; j < 26; j++)
                prefix[mask] += min[j];
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++)
            dp[1 << i] = prefix[1 << i] + 1;
        for (int mask = 0; mask < (1 << n); mask++)
            if (Integer.bitCount(mask) > 1)
                for (int i = (mask - 1) & mask; i > 0; i = (i - 1) & mask)
                    dp[mask] = Math.min(dp[mask], dp[i] + dp[mask ^ i]
                            - prefix[mask] - 1);
        return dp[(1 << n) - 1];
    }
}
