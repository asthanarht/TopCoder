import java.util.Arrays;
import java.util.PriorityQueue;

public class SkiResorts {
    public long minCost(String[] road, int[] altitude) {
        int n = altitude.length;
        int[] heights = altitude.clone();
        Arrays.sort(heights);
        long[][] best = new long[n][n];
        for (int i = 0; i < n; i++)
            Arrays.fill(best[i], Long.MAX_VALUE);
        boolean[][] graph = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (road[i].charAt(j) == 'Y')
                    graph[i][j] = true;
        PriorityQueue<State> pqueue = new PriorityQueue<State>();

        for (int j = 0; j < n; j++) {
            // raise the 0th to heights[j]
            best[0][j] = Math.abs(altitude[0] - heights[j]);
            pqueue.add(new State(best[0][j], 0, j));
        }
        while (!pqueue.isEmpty()) {
            State cur = pqueue.poll();
            if (cur.cost > best[cur.node][cur.height])
                continue;
            if (cur.node == n - 1)
                return cur.cost;
            for (int next = 0; next < n; next++)
                if (graph[cur.node][next])
                    for (int height = 0; height <= cur.height; height++) {
                        // raise the next to heights[height] < cur.height
                        long cost = cur.cost
                                + Math.abs(altitude[next] - heights[height]);
                        if (cost < best[next][height]) {
                            best[next][height] = cost;
                            pqueue.add(new State(cost, next, height));
                        }
                    }
        }
        return -1;
    }
}

class State implements Comparable<State> {
    long cost;
    int node;
    int height;

    public State(long cost, int node, int height) {
        super();
        this.cost = cost;
        this.node = node;
        this.height = height;
    }

    public int compareTo(State s) {
        if (cost == s.cost)
            return 0;
        return cost < s.cost ? -1 : 1;
    }
}
