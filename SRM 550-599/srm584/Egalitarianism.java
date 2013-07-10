package srm584;

public class Egalitarianism {
    public int maxDifference(String[] isFriend, int d) {
        int n = isFriend.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (isFriend[i].charAt(j) == 'Y')
                    graph[i][j] = 1;
                else
                    graph[i][j] = Integer.MAX_VALUE;
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (graph[i][k] != Integer.MAX_VALUE
                            && graph[k][j] != Integer.MAX_VALUE)
                        graph[i][j] = Math.min(graph[i][j], graph[i][k]
                                + graph[k][j]);
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (i != j)
                    max = Math.max(max, graph[i][j]);
        if (max == Integer.MAX_VALUE)
            return -1;
        return max * d;
    }
}
