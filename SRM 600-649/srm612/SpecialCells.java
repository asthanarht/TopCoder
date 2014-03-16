package srm612;

import java.util.Arrays;

public class SpecialCells {

	public int guess(int[] x, int[] y) {
		int[] dx = distinct(x), dy = distinct(y);
		int n = dx.length + dy.length + 2, s = n - 2, t = n - 1;
		int[][] capacity = new int[n][n], cost = new int[n][n];
		for (int i = 0; i < dx.length; i++)
			for (int j = 0; j < dy.length; j++)
				capacity[i][dx.length + j] = 1;
		for (int i = 0; i < x.length; i++) {
			int xi = Arrays.binarySearch(dx, x[i]);
			int yi = Arrays.binarySearch(dy, y[i]);
			capacity[s][xi]++;
			capacity[dx.length + yi][t]++;
			cost[xi][dx.length + yi]++;
			cost[dx.length + yi][xi]--;
		}
		return minCostMaxFlow(n, s, t, capacity, cost);
	}

	private int minCostMaxFlow(int n, int s, int t, int[][] capacity,
			int[][] cost) {
		int[] price = new int[n];
		int res = 0;
		while (true) {
			int[] dist = new int[n], pre = new int[n];
			Arrays.fill(dist, Integer.MAX_VALUE);
			Arrays.fill(pre, -1);
			dist[s] = 0;
			boolean[] visited = new boolean[n];
			while (true) {
				int besti = -1;
				for (int i = 0; i < n; i++)
					if (!visited[i] && dist[i] < Integer.MAX_VALUE
							&& (besti == -1 || dist[i] < dist[besti]))
						besti = i;
				if (besti == -1)
					break;
				visited[besti] = true;
				for (int i = 0; i < n; i++)
					if (!visited[i] && capacity[besti][i] > 0) {
						int delta = cost[besti][i] + price[besti] - price[i];
						if (dist[i] > dist[besti] + delta) {
							dist[i] = dist[besti] + delta;
							pre[i] = besti;
						}
					}
			}
			if (!visited[t])
				break;
			int min = Integer.MAX_VALUE;
			int cur = t;
			while (cur != s) {
				int from = pre[cur];
				min = Math.min(min, capacity[from][cur]);
				cur = from;
			}
			res += (price[t] - price[s] + dist[t]) * min;
			cur = t;
			while (cur != s) {
				int from = pre[cur];
				capacity[from][cur] -= min;
				capacity[cur][from] += min;
				cur = from;
			}
			for (int i = 0; i < n; i++)
				if (dist[i] == Integer.MAX_VALUE)
					price[i]++;
				else
					price[i] += dist[i];
		}
		return res;
	}

	private int[] distinct(int[] x) {
		x = x.clone();
		Arrays.sort(x);
		int cnt = 1;
		for (int i = 1; i < x.length; i++)
			if (x[i] > x[i - 1])
				x[cnt++] = x[i];
		return Arrays.copyOf(x, cnt);
	}

}
