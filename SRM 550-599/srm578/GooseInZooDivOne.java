package srm578;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GooseInZooDivOne {
    private static int n, m, limit;
    private static boolean[][] grid;
    private static int[][] group;
    private static ArrayList<Integer> leaders = new ArrayList<Integer>();
    private static int[] count;
    private static LinkedList<Integer> queuer = new LinkedList<Integer>();
    private static LinkedList<Integer> queuec = new LinkedList<Integer>();

    public static int count(String[] field, int dist) {
        n = field.length;
        m = field[0].length();
        limit = dist;
        grid = new boolean[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                grid[i][j] = (field[i].charAt(j) == 'v');

        group = new int[n][m];
        for (int i = 0; i < n; i++)
            Arrays.fill(group[i], -1);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] && group[i][j] == -1) {
                    queuer.add(i);
                    queuec.add(j);
                    searchGroup(i * m + j);
                }

        count = new int[n * m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (grid[i][j] && group[i][j] != -1)
                    count[group[i][j]]++;

        int even = 0, odd = 0;
        for (int i = 0; i < n * m; i++)
            if (count[i] > 0) {
                if (count[i] % 2 == 0)
                    even++;
                else
                    odd++;
            }

        long mod = 1000000007;
        long[][] c = new long[leaders.size() + 1][leaders.size() + 1];
        for (int i = 0; i <= leaders.size(); i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
                c[i][j] %= mod;
            }
        }

        long result = 0;
        for (int e = 0; e <= even; e++)
            for (int o = 0; o <= odd; o += 2)
                if (e + o > 0) {
                    result += c[even][e] * c[odd][o];
                    result %= mod;
                }
        return (int) result;
    }

    private static void searchGroup(int leader) {
        leaders.add(leader);
        while (!queuer.isEmpty()) {
            int i = queuer.poll();
            int j = queuec.poll();
            group[i][j] = leader;
            for (int r = 0; r < n; r++)
                for (int c = 0; c < m; c++) {
                    if (Math.abs(r - i) + Math.abs(c - j) <= limit)
                        if (grid[r][c] && group[r][c] == -1) {
                            queuer.add(r);
                            queuec.add(c);
                            group[r][c] = leader;
                        }
                }
        }
    }
}
