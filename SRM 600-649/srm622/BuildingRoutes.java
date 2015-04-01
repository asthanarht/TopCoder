package srm622;

public class BuildingRoutes {

    public int build(String[] dist, int T) {
        int n = dist.length;
        int[][] len = new int[n][n];
        int[][] floyd = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                len[i][j] = dist[i].charAt(j) - '0';
                floyd[i][j] = len[i][j];
            }

        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k]
                            + floyd[k][j]);

        int[][] cnt = new int[n][n];
        for (int from = 0; from < n; from++)
            for (int to = 0; to < n; to++)
                for (int s = 0; s < n; s++)
                    for (int t = 0; t < n; t++)
                        if (s != t
                                && floyd[s][t] == floyd[s][from]
                                        + len[from][to] + floyd[to][t])
                            cnt[from][to]++;

        int res = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j && cnt[i][j] >= T)
                    res += len[i][j];

        return res;
    }

}
