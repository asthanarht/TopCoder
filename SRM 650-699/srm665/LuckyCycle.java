public class LuckyCycle {

    public int[] getEdge(int[] edge1, int[] edge2, int[] weight) {
        int n = edge1.length + 1;
        int[][][] floyd = new int[n + 1][n + 1][2];
        for (int i = 0; i < n - 1; i++) {
            if (weight[i] == 4)
                floyd[edge1[i]][edge2[i]][0] = floyd[edge2[i]][edge1[i]][0] = 1;
            else
                floyd[edge1[i]][edge2[i]][1] = floyd[edge2[i]][edge1[i]][1] = 1;
        }
        for (int k = 1; k <= n; k++)
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    if (i != j && floyd[i][j][0] + floyd[i][j][1] == 0
                            && floyd[i][k][0] + floyd[i][k][1] > 0
                            && floyd[k][j][0] + floyd[k][j][1] > 0) {
                        floyd[i][j][0] = floyd[i][k][0] + floyd[k][j][0];
                        floyd[i][j][1] = floyd[i][k][1] + floyd[k][j][1];
                    }
        for (int i = 1; i <= n; i++)
            for (int j = i + 1; j <= n; j++)
                if (floyd[i][j][0] + floyd[i][j][1] > 1) {
                    if (floyd[i][j][0] == floyd[i][j][1] + 1)
                        return new int[] { i, j, 7 };
                    else if (floyd[i][j][0] + 1 == floyd[i][j][1])
                        return new int[] { i, j, 4 };
                }
        return new int[] {};
    }

}
