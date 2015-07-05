import java.util.ArrayList;

// two coordinates are independent, can be solved independently 
//
// let fun[i][x] be the profit lose accumulated up to e[i] at x,
// fun[i] is a piecewise linear function of U/V shape (i.e., 3 parts):
//    left part: monotonically decreasing
//    middle part: a point or a horizontal segment
//    right part: monotonically increasing
//
// from fun[i-1] to fun[i]:
// (1)let newfun[i-1] be:
//    shift left part of fun[i-1] to left by d[i-1], 
//    and shift right part of fun[i-1] to right by d[i-1], 
//    and stratch the middle part of fun[i-1] to left by d[i-1] and to right by d[i-1].
// (2)fun[i] = newfun[i-1] + plfun[i]
//    here, plfun[i] = Math.abs(x - e[i])
public class MiningGoldHard {
	public int GetMaximumGold(int n, int m, int[] event_i, int[] event_j,
	        int[] event_di, int[] event_dj) {
		return (n + m) * event_i.length - solve(n, event_i, event_di)
		        - solve(m, event_j, event_dj);
	}

	private int solve(int n, int[] e, int[] d) {
		ArrayList<Point> fun = new ArrayList<Point>();
		if (e[0] != 0)
			fun.add(new Point(0, e[0]));
		fun.add(new Point(e[0], 0));
		if (e[0] != n)
			fun.add(new Point(n, n - e[0]));
		for (int i = 1; i < e.length; i++) {
			ArrayList<Point> newFun = new ArrayList<Point>();
			// shift accumulated profit lose up to e[i-1]
			if (d[i - 1] > 0) {
				int low = 0;
				while (low + 1 < fun.size()
				        && fun.get(low).lose > fun.get(low + 1).lose)
					low++;
				for (int j = 0; j <= low; j++) {
					Point point = fun.get(j);
					if (j + 1 < fun.size() && fun.get(j + 1).loc < 0)
						continue; // keep only the last point with loc < 0
					newFun.add(new Point(point.loc - d[i - 1], point.lose));
				}
				while (low + 1 < fun.size()
				        && fun.get(low).lose == fun.get(low + 1).lose)
					low++;
				for (int j = low; j < fun.size(); j++) {
					Point point = fun.get(j);
					if (j > 0 && fun.get(j - 1).loc > n)
						break; // keep only the first point with loc > n
					newFun.add(new Point(point.loc + d[i - 1], point.lose));
				}
				fun = newFun;
				newFun = new ArrayList<Point>();
			}
			// insert e[i]
			for (int j = 0; j + 1 < fun.size(); j++) {
				Point p1 = fun.get(j);
				Point p2 = fun.get(j + 1);
				if (p1.loc < e[i] && e[i] < p2.loc)
					fun.add(j + 1, new Point(e[i], p1.lose
					        + (p2.lose - p1.lose) / (p2.loc - p1.loc)
					        * (e[i] - p1.loc)));
			}
			// adds profit lose of e[i]
			for (int j = 0; j < fun.size(); j++) {
				Point point = fun.get(j);
				newFun.add(new Point(point.loc, point.lose
				        + Math.abs(point.loc - e[i])));
			}
			fun = newFun;
		}
		int lose = Integer.MAX_VALUE;
		for (Point point : fun)
			if (point.loc >= 0 && point.loc <= n)
				lose = Math.min(lose, point.lose);
		return lose;
	}

	class Point {
		int loc;
		int lose;

		public Point(int loc, int lose) {
			this.loc = loc;
			this.lose = lose;
		}
	}
}