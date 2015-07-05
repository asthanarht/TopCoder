import java.util.Arrays;

public class DiceGames {
    public long countFormations(int[] sides) {
        /*
         * Combination not permutation, order is not important. Equivalent to
         * the number of nondecreasing sequences.
         */
        Arrays.sort(sides);
        int n = sides.length, max = sides[sides.length - 1];
        long[][] dp = new long[n][max]; // number[the ith dice][value]
        for (int i = 0; i < sides[0]; i++)
            // the first dice values
            dp[0][i] = 1;
        for (int i = 1; i < n; i++)
            // for the ith dice
            for (int j = 0; j < sides[i - 1]; j++)
                // for the last dice value
                for (int k = j; k < sides[i]; k++)
                    // the ith dice could have value k
                    dp[i][k] += dp[i - 1][j];
        long res = 0;
        for (int i = 0; i < max; i++)
            res += dp[n - 1][i];
        return res;
    }
}
