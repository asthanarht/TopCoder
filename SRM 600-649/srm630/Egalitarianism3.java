package srm630;

import java.util.ArrayList;
import java.util.HashMap;

public class Egalitarianism3 {

	public int maxCities(int n, int[] a, int[] b, int[] len) {
		int[][] dis = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				dis[i][j] = dis[j][i] = 100000;
		for (int i = 0; i < a.length; i++)
			dis[b[i] - 1][a[i] - 1] = dis[a[i] - 1][b[i] - 1] = len[i];
		for (int k = 0; k < n; k++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					dis[i][j] = Math.min(dis[i][j], dis[i][k] + dis[k][j]);
		int max = n == 1 ? 1 : 2;
		for (int i = 0; i < n; i++) {
			HashMap<Integer, ArrayList<Integer>> group = new HashMap<Integer, ArrayList<Integer>>();
			for (int j = 0; j < n; j++) {
				if (!group.containsKey(dis[i][j]))
					group.put(dis[i][j], new ArrayList<Integer>());
				group.get(dis[i][j]).add(j);
			}
			for (Integer d : group.keySet()) {
				int cnt = 0;
				boolean[] select = new boolean[n];
				for (Integer u : group.get(d)) {
					boolean ok = true;
					for (Integer v : group.get(d))
						if (select[v] && dis[u][v] != d + d)
							ok = false;
					if (ok) {
						cnt++;
						select[u] = true;
					}
				}
				max = Math.max(max, cnt);
			}
		}
		return max;
	}
}
