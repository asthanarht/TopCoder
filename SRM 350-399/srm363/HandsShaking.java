public class HandsShaking {
    public long countPerfect(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1; // help multiplication
        dp[2] = 1;
        for (int i = 4; i <= n; i += 2) { // how many people in total
            /*
             * pick one person, there must be even number people between him and
             * his hand shaker, both to his left hand side and right hand side.
             */
            int left = i - 2; // left hand side
            int right = 0; // right hand side
            /*
             * there are i/2 options for this person to shake hand with
             */
            for (int j = 0; j < i / 2; j++) {
                dp[i] += dp[left] * dp[right];
                left -= 2;
                right += 2;
            }
        }
        return dp[n];
    }
}
