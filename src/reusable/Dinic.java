package reusable;

import java.util.Arrays;
import java.util.LinkedList;

public class Dinic {

	private int[][] capacity;
	private int[][] flow;
	private int[] level;
	private int source;
	private int sink;
	private int n;

	public Dinic(int[][] cap, int s, int t) {
		capacity = cap;
		source = s;
		sink = t;
		n = cap.length;
		flow = new int[n][n];
		level = new int[n];
	}

	private boolean levelGraph() {
		Arrays.fill(level, -1);
		level[source] = 1;
		// BFS for the level graph
		LinkedList<Integer> front = new LinkedList<Integer>();
		front.add(source);
		while (!front.isEmpty()) {
			int u = front.poll();
			for (int v = 0; v < n; v++)
				if (capacity[u][v] - flow[u][v] > 0 && level[v] == -1) {
					level[v] = level[u] + 1;
					front.add(v);
				}
		}
		return level[sink] == -1 ? false : true;
	}

	private int findflow(int u, int min) {
		if (u == sink)
			return min;
		for (int v = 0; v < n; v++)
			if (capacity[u][v] - flow[u][v] > 0 && level[v] == level[u] + 1) {
				int f = findflow(v, Math.min(min, capacity[u][v] - flow[u][v]));
				if (f > 0) {
					flow[u][v] += f;
					flow[v][u] -= f;
					return f;
				}
			}
		return 0;
	}

	public int maxflow() {
		int total = 0;
		while (levelGraph()) {
			int f;
			do {
				f = findflow(source, Integer.MAX_VALUE);
				total += f;
			} while (f > 0);
		}
		return total;
	}

}
