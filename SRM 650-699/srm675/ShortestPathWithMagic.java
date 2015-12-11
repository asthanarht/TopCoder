public class ShortestPathWithMagic {

    public double getTime(String[] dist, int k) {
        n = dist.length;
        d = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                d[i][j] = dist[i].charAt(j) - '0';
        res = k > 0 ? d[0][1] * 0.5 : d[0][1];
        visited = new boolean[n];
        dfs(0, 0, k);
        return res;
    }

    private int n;
    private double[][] d;
    private double res;
    private boolean[] visited;

    private void dfs(double p, int u, int k) {
        if (p >= res)
            return;
        if (u == 1) {
            res = p;
            return;
        }
        for (int v = 1; v < n; v++)
            if (u != v && !visited[v]) {
                visited[v] = true;
                dfs(p + d[u][v], v, k);
                if (k > 0)
                    dfs(p + d[u][v] * 0.5, v, k - 1);
                visited[v] = false;
            }
    }
}
