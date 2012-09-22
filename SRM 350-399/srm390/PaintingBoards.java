package srm390;

import java.util.Arrays;

public class PaintingBoards {
    public double minimalTime(int[] boardLength, int[] painterSpeed) {
        int m = boardLength.length; // m boards
        int n = painterSpeed.length; // n painter
        int[] len = new int[m + 1]; // accumulated length of boards
        for (int i = 0; i < m; i++)
            len[i + 1] = len[i] + boardLength[i];

        int total = (m + 1) * m / 2 * n;
        double[] time = new double[total];
        int count = 0;
        // how much time does the ith painter need start from [jth board to kth
        // board)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int k = j + 1; k <= m; k++)
                    time[count++] = (len[k] - len[j])
                            / (double) painterSpeed[i];

        Arrays.sort(time);

        int left = 0, right = total - 1;
        int[][] paintTo = new int[n][m + 1];
        int[] dp = new int[1 << n];
        while (left < right) {
            int middle = (left + right) / 2;
            double t = time[middle];

            // where could the ith painter start from jth board could paint to
            // in t
            // time
            for (int i = 0; i < n; i++)
                for (int j = 0; j <= m; j++) {
                    paintTo[i][j] = j;
                    for (int k = j + 1; k <= m; k++)
                        if ((len[k] - len[j]) / (double) painterSpeed[i] <= t)
                            paintTo[i][j] = k;
                        else
                            break;
                }

            // mask = combination of painters
            for (int mask = 1; mask < 1 << n; mask++) {
                // the best could be done with these painters
                dp[mask] = 0;
                for (int i = 0; i < n; i++)
                    if ((mask & (1 << i)) > 0)
                        dp[mask] = Math.max(dp[mask], paintTo[i][dp[mask
                                ^ (1 << i)]]);
                // dp[mask ^ (1 << i)] best could be done without the ith
                // painter
            }

            if (dp[dp.length - 1] == m)
                right = middle;
            else
                left = middle + 1;
        }

        return time[left];
    }
}