package srm556;

import java.util.LinkedList;
import java.util.Queue;

public class XorTravelingSalesman {
    public int maxProfit(int[] cityValues, String[] roads) {
        int n = cityValues.length;
        boolean[][] map = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (roads[i].charAt(j) == 'Y')
                    map[i][j] = true;
        boolean[][] visited = new boolean[n][1024];
        Queue<Integer> city = new LinkedList<Integer>();
        Queue<Integer> profit = new LinkedList<Integer>();
        city.add(0);
        profit.add(cityValues[0]);
        visited[0][cityValues[0]] = true;
        while (city.size() > 0) {
            int c = city.poll();
            int p = profit.poll();
            for (int i = 0; i < n; i++)
                if (map[c][i]) {
                    int np = p ^ cityValues[i];
                    if (visited[i][np])
                        continue;
                    city.add(i);
                    profit.add(np);
                    visited[i][np] = true;
                }
        }
        for (int p = 1023; p >= cityValues[0]; p--)
            for (int i = 0; i < n; i++)
                if (visited[i][p])
                    return p;
        return cityValues[0];
    }
}
