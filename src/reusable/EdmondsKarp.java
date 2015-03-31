package reusable;

import java.util.Arrays;
import java.util.LinkedList;

public class EdmondsKarp {
	private int[][] capacity;
	private int[][] flow;
	private int source;
	private int sink;
	private int n;

	public EdmondsKarp(int[][] cap, int s, int t) {
		n = cap.length;
		capacity = new int[n][n];
		flow = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				capacity[i][j] = cap[i][j];
		source = s;
		sink = t;
	}

	public int maxflow() {
		loop: while (true) {
			int[] parent = new int[n]; // to store augmenting path
			Arrays.fill(parent, -1);
			parent[source] = source;
			int[] min = new int[n]; // to compute/store residual capacity
			min[source] = Integer.MAX_VALUE;
			LinkedList<Integer> front = new LinkedList<Integer>();
			front.add(source);
			while (!front.isEmpty()) {
				int u = front.poll();
				// augmenting paths of same length accessed lexicographically
				for (int v = 0; v < n; v++)
					if (capacity[u][v] - flow[u][v] > 0 && parent[v] == -1) {
						parent[v] = u;
						min[v] = Math.min(min[u], capacity[u][v] - flow[u][v]);
						if (v != sink)
							front.add(v);
						else {
							// String path = "" + sink;
							while (parent[v] != v) {
								u = parent[v];
								// path = u + " -> " + path;
								flow[u][v] += min[sink];
								flow[v][u] -= min[sink];
								v = u;
							}
							// path = "( " + min[sink] + " ) " + path;
							// System.out.println(path);
							continue loop;
						}
					}
			}
			// no more augmenting path can be found
			int total = 0;
			for (int i = 0; i < n; i++)
				total += flow[source][i];
			return total;
		}
	}

}
