package srm411;

import java.util.Arrays;

public class SentenceDecomposition {

    private int len;

    public int decompose(String sentence, String[] validWords) {
        len = sentence.length();
        int[] dp = new int[55];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            if (dp[i] == -1)
                continue;
            for (String word : validWords) {
                int wlen = word.length();
                if (i + wlen > len)
                    continue;
                int cost = wordcost(word, sentence.substring(i, i + wlen));
                if (cost == -1)
                    continue;
                cost += dp[i];
                int index = i + wlen;
                if (dp[index] == -1 || cost < dp[index])
                    dp[index] = cost;
            }
        }
        return dp[len];
    }

    private int wordcost(String word, String code) {
        int nw = word.length();
        char[] wcs = word.toCharArray();
        char[] ccs = code.toCharArray();
        Arrays.sort(wcs);
        Arrays.sort(ccs);
        for (int i = 0; i < nw; i++)
            if (wcs[i] != ccs[i])
                return -1;
        int cost = 0;
        for (int i = 0; i < nw; i++)
            if (word.charAt(i) != code.charAt(i))
                cost++;
        return cost;
    }
}
