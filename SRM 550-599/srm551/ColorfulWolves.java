package srm551;

public class ColorfulWolves {
    public int getmin(String[] colormap) {
        int N = colormap.length;
        boolean[][] map = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (colormap[i].charAt(j) == 'Y')
                    map[i][j] = true;
        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0, cost = 0; j < N; j++)
                if (map[i][j]) {
                    graph[i][j] = cost;
                    cost++;
                }
                else
                    graph[i][j] = 10000;
        }
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]
                            + graph[k][j]);
        return graph[0][N - 1] == 10000 ? -1 : graph[0][N - 1];
    }
}
