public class DancingFoxes {
    public int minimalDays(String[] friendship) {
        int n = friendship.length;
        int[][] relation = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (friendship[i].charAt(j) == 'Y')
                    relation[i][j] = 1;
                else
                    relation[i][j] = 100000;
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (relation[i][k] >= 0 && relation[k][j] >= 0)
                        relation[i][j] = Math.min(relation[i][j],
                                relation[i][k] + relation[k][j]);
        if (relation[0][1] == 100000)
            return -1;
        int result = 0, nodes = relation[0][1] + 1;
        while (nodes > 3) {
            result++;
            nodes -= nodes / 3;
        }
        return result + (nodes == 3 ? 1 : 0);
    }
}
