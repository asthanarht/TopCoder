package r2C;

import java.util.Arrays;

public class GreedyTravelingSalesman {
    public int worstDistance(String[] thousands, String[] hundreds,
            String[] tens, String[] ones) {
        int n = thousands.length;
        int[][] length = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                length[i][j] += thousands[i].charAt(j) - '0';
                length[i][j] *= 10;
                length[i][j] += hundreds[i].charAt(j) - '0';
                length[i][j] *= 10;
                length[i][j] += tens[i].charAt(j) - '0';
                length[i][j] *= 10;
                length[i][j] += ones[i].charAt(j) - '0';
            }
        boolean[] visited = new boolean[n];
        int dis, max = 0, next, nextIndex, cur, hold;
        for (int start = 0; start < n; start++)
            for (int end = 0; end < n; end++)
                if (start != end)
                    for (int change = 0; change < n; change++) {
                        hold = length[start][end];
                        if (change == 0 || change == start)
                            length[start][end] = 1;
                        else if (change == end)
                            length[start][end] = 9999;
                        else if (change < end)
                            length[start][end] = Math.max(1,
                                    length[start][change] - 1);
                        else
                            length[start][end] = length[start][change];

                        Arrays.fill(visited, false);
                        dis = 0;
                        cur = 0;
                        for (int turn = 1; turn < n; turn++) {
                            next = 10000;
                            nextIndex = 0;
                            for (int i = 1; i < n; i++)
                                if (!visited[i] && length[cur][i] < next) {
                                    next = length[cur][i];
                                    nextIndex = i;
                                }
                            dis += next;
                            cur = nextIndex;
                            visited[nextIndex] = true;
                        }

                        max = Math.max(max, dis);
                        length[start][end] = hold;
                    }
        return max;
    }
}
