package srm549;

import java.util.Arrays;

public class PointyWizardHats {
	public static int[] pair = new int[55];
	public static boolean[] visited = new boolean[55];
	public static boolean[][] can = new boolean[55][55];
	public static int b, t;

	public int getNumHats(int[] topHeight, int[] topRadius, int[] bottomHeight,
			int[] bottomRadius) {
		t = topHeight.length;
		b = bottomHeight.length;
		for (int bi = 0; bi < b; bi++)
			for (int ti = 0; ti < t; ti++)
				can[bi][ti] = ((bottomRadius[bi] > topRadius[ti]) && (bottomRadius[bi]
						* topHeight[ti] > topRadius[ti] * bottomHeight[bi]));

		int count = 0;
		Arrays.fill(pair, -1);
		for (int ti = 0; ti < t; ti++) {
			Arrays.fill(visited, false);
			if (find(ti))
				count++;
		}
		return count;
	}

	public boolean find(int ti) {
		for (int bi = 0; bi < b; bi++)
			if (can[bi][ti] && !visited[bi]) {
				visited[bi] = true;
				if (pair[bi] == -1 || find(pair[bi])) {
					pair[bi] = ti;
					return true;
				}
			}
		return false;
	}
}
