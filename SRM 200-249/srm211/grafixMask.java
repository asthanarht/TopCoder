package srm211;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class grafixMask {
	private int[] x = { 0, 0, 1, -1 };
	private int[] y = { 1, -1, 0, 0 };

	public int[] sortedAreas(String[] rectangles) {
		int m = rectangles.length;
		boolean[][] grid = new boolean[600][400];
		for (int i = 0; i < m; i++) {
			String[] info = rectangles[i].split(" ");
			int[] loc = new int[4];
			for (int j = 0; j < 4; j++)
				loc[j] = Integer.parseInt(info[j]);
			for (int j = loc[1]; j <= loc[3]; j++)
				for (int k = loc[0]; k <= loc[2]; k++)
					grid[j][k] = true;
		}
		ArrayList<Integer> area = new ArrayList<Integer>();
		for (int i = 0; i < 600; i++)
			for (int j = 0; j < 400; j++)
				if (!grid[i][j]) {
					int count = 0;
					LinkedList<Integer> queue = new LinkedList<Integer>();
					queue.add(i);
					queue.add(j);
					grid[i][j] = true;
					while (queue.size() > 0) {
						count++;
						int I = queue.poll();
						int J = queue.poll();
						for (int k = 0; k < 4; k++) {
							int r = I + x[k];
							int c = J + y[k];
							if (r >= 0 && r < 600 && c >= 0 && c < 400
									&& !grid[r][c]) {
								grid[r][c] = true;
								queue.add(r);
								queue.add(c);
							}
						}
					}
					area.add(count);
				}
		int[] res = new int[area.size()];
		for (int i = 0; i < area.size(); i++)
			res[i] = area.get(i);
		Arrays.sort(res);
		return res;
	}
}
