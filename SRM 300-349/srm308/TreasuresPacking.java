package srm308;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TreasuresPacking {
    public double maximizeCost(String[] treasures, int W) {
        int n = treasures.length;
        List<int[]> y = new ArrayList<int[]>();
        List<int[]> no = new ArrayList<int[]>();
        for (int i = 0; i < n; i++) {
            Scanner sc = new Scanner(treasures[i]);
            int w = sc.nextInt();
            int c = sc.nextInt();
            if (sc.next().charAt(0) == 'Y')
                y.add(new int[] { w, c });
            else
                no.add(new int[] { w, c });
            sc.close();
        }
        Collections.sort(y, new Comparator<int[]>() {
            public int compare(int[] arg0, int[] arg1) {
                return arg1[1] * arg0[0] - arg0[1] * arg1[0];
            }
        });
        int non = no.size();
        double[][] dp = new double[non + 1][W + 1];
        dp[0][0] = 0;
        int w = 0, index = 0;
        double v = 0;
        for (int i = 1; i <= W; i++) {
            if (w == 0) {
                if (index == y.size()) {
                    while (i <= W) {
                        dp[0][i] = dp[0][i - 1];
                        i++;
                    }
                    break;
                }
                w = y.get(index)[0];
                v = y.get(index++)[1];
                v /= w;
            }
            dp[0][i] = dp[0][i - 1] + v;
            w--;
        }
        for (int i = 1; i <= non; i++)
            for (int j = 1; j <= W; j++) {
                dp[i][j] = dp[i - 1][j];
                if (no.get(i - 1)[0] <= j) {
                    double value = no.get(i - 1)[1]
                            + dp[i - 1][j - no.get(i - 1)[0]];
                    dp[i][j] = Math.max(dp[i][j], value);
                }
            }
        return dp[non][W];
    }
}