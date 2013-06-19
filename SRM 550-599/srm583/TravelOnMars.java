package srm583;

import java.util.Arrays;

public class TravelOnMars {
    public int minTimes(int[] range, int startCity, int endCity) {
        int n = range.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(graph[i], 100);
        for (int i = 0; i < n; i++)
            for (int j = i - range[i]; j <= i + range[i]; j++) {
                int to = j + n * 50;
                to %= n;
                graph[i][to] = 1;
            }
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]
                            + graph[k][j]);
        return graph[startCity][endCity];
    }
}
