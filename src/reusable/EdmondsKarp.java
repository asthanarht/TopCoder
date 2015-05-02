package reusable;

import java.util.LinkedList;

public class EdmondsKarp {
	private int[][] capacity;
	private int[][] flow;
	private int source;
	private int sink;
	private int n;

	public EdmondsKarp(int[][] cap, int s, int t) {
		capacity = cap;
		source = s;
		sink = t;
		n = cap.length;
		flow = new int[n][n];
	}

	public int maxflow() {
		int total = 0;

		while (true) {
			int[] parent = new int[n]; // to store augmenting path
			int[] min = new int[n]; // to compute/store residual capacity
			min[source] = Integer.MAX_VALUE;
			LinkedList<Integer> front = new LinkedList<Integer>();
			front.add(source);
			// BFS for the shortest augmenting path
			loop: while (!front.isEmpty()) {
				int u = front.poll();
				// augmenting paths of same length accessed lexicographically
				for (int v = 0; v < n; v++)
					if (capacity[u][v] - flow[u][v] > 0 && min[v] == 0) {
						parent[v] = u;
						min[v] = Math.min(min[u], capacity[u][v] - flow[u][v]);
						if (v == sink)
							break loop;
						front.add(v);
					}
			}

			// no more augmenting path can be found
			if (min[sink] == 0)
				return total;

			total += min[sink];
			// String path = "" + sink;
			for (int u = sink; u != source; u = parent[u]) {
				// path = parent[u] + " -> " + path;
				flow[parent[u]][u] += min[sink];
				flow[u][parent[u]] -= min[sink];
			}
			// path = "( " + min[sink] + " ) " + path;
			// System.out.println(path);
		}
	}

}
