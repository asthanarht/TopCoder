package TCAP2012;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class TheUniverseUnravels {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int nCoords = Integer.parseInt(in.nextLine());
		String[] coords = new String[nCoords];
		for (int i = 0; i < nCoords; i++)
			coords[i] = in.nextLine();

		int nRanks = Integer.parseInt(in.nextLine());
		String[] ranks = new String[nRanks];
		for (int i = 0; i < nRanks; i++)
			ranks[i] = in.nextLine();

		int nMinDist = Integer.parseInt(in.nextLine());
		int[] minDist = new int[nMinDist];
		for (int i = 0; i < nMinDist; i++)
			minDist[i] = Integer.parseInt(in.nextLine());

		int nMaxDist = Integer.parseInt(in.nextLine());
		int[] maxDist = new int[nMaxDist];
		for (int i = 0; i < nMaxDist; i++)
			maxDist[i] = Integer.parseInt(in.nextLine());

		String[] res = predictCoordinates(coords, ranks, minDist, maxDist);
		System.out.println(res.length);
		for (int i = 0; i < res.length; i++)
			System.out.println(res[i]);
		System.out.flush();
	}

	private static int N;
	private static String[] res;
	private static Point[] points;
	private static int[][] rank;
	private static int[] minDis;
	private static int[] maxDis;
	private static double[][] dist;
	private static HashMap<Integer, HashSet<Integer>> group = new HashMap<Integer, HashSet<Integer>>();

	private static int ADJUST = 1, SEN = 8;

	public static String[] predictCoordinates(String[] coords, String[] ranks,
			int[] minDist, int[] maxDist) {
		long a = System.currentTimeMillis();

		N = coords.length;
		res = new String[N];
		points = new Point[N];
		rank = new int[N][N];
		minDis = minDist;
		maxDis = maxDist;
		dist = new double[N][N];

		if (N > 1250)
			SEN = 5;
		else if (N > 750)
			SEN = 6;

		for (int i = 0; i <= 10; i++)
			group.put(i, new HashSet<Integer>());

		for (int i = 0; i < N; i++) {
			points[i] = new Point(coords[i]);
			group.get(points[i].unknown).add(i);
			String[] info = ranks[i].split(" ");
			for (int j = 0; j < N; j++)
				rank[i][j] = Integer.parseInt(info[j]);
		}

		// calculation
		sortRank();

		long max = 0, time = System.currentTimeMillis(), time2, stop = a + 9800;
		int center = 0;
		while (time + max < stop) {
			if (center == 0)
				expectedDis();
			go(center);
			time2 = System.currentTimeMillis();
			max = Math.max(max, time2 - time);
			time = time2;
			center++;
			center %= SEN;
		}

		// done
		for (int i = 0; i < N; i++)
			res[i] = points[i].toString();
		return res;
	}

	private static void go(int center) {
		for (Integer cen : group.get(center))
			for (int unknow = 1; unknow <= 10; unknow++)
				for (Integer move : group.get(unknow))
					adjust(cen, move);
	}

	private static void sortRank() {
		for (int i = 0; i < N; i++) {
			Rank[] ranks = new Rank[N];
			for (int j = 0; j < N; j++)
				ranks[j] = new Rank(j, rank[i][j]);
			Arrays.sort(ranks);
			points[i].ranks = ranks;
		}
	}

	private static void expectedDis() {
		double[] expected;
		for (int center = 0; center < N; center++) {
			expected = new double[N];
			int dis = minDis[center];
			for (int rankA = 1; rankA < N; rankA++) {
				expected[rankA] = dis;
				int rankANext = rankA + 1;
				if (rankANext < N) {
					if (points[center].ranks[rankANext].rank == points[center].ranks[rankA].rank)
						continue;
					else {
						for (int rankB = rankANext; rankB < N; rankB++) {
							if (points[points[center].ranks[rankB].index].unknown == 0) {
								int index = points[center].ranks[rankB].index;
								int discb = dis(center, index);
								if (rankB - rankA > 1) {
									double diff = (discb - dis)
											/ (rankB - rankA);
									for (int rank = rankANext; rank < rankB; rank++)
										expected[rank] = expected[rank - 1]
												+ diff;
									rankA = rankB - 1;
								}
								dis = discb;
								break;
							} else if (points[center].ranks[rankB].rank == points[center].ranks[N - 1].rank) {
								if (rankB - rankA > 1) {
									double diff = (maxDis[center] - dis)
											/ (rankB - rankA);
									for (int rank = rankANext; rank < rankB; rank++)
										expected[rank] = expected[rank - 1]
												+ diff;
									rankA = rankB - 1;
								}
								dis = maxDis[center];
								break;
							}
						}
					}
				}
			}
			for (int i = 0; i < N; i++)
				dist[center][points[center].ranks[i].index] = expected[i];
		}
	}

	private static int dis(int a, int b) {
		int dis = 0;
		for (int i = 0; i < 10; i++)
			dis += (points[a].x[i] - points[b].x[i])
					* (points[a].x[i] - points[b].x[i]);
		return dis;
	}

	private static void adjust(int a, int b) {
		int dis = dis(a, b);
		if (Math.abs(dis - dist[a][b]) < 10)
			return;
		for (int i = 0, index; i < points[b].unknown; i++) {
			index = points[b].unknownD.get(i);
			if (points[b].x[index] >= points[a].x[index]) {
				if (dis < dist[a][b])
					points[b].x[index] += ADJUST;
				else
					points[b].x[index] -= ADJUST;
			} else {
				if (dis < dist[a][b])
					points[b].x[index] -= ADJUST;
				else
					points[b].x[index] += ADJUST;
			}
			if (points[b].x[index] < 5)
				points[b].x[index]++;
			if (points[b].x[index] > 995)
				points[b].x[index]--;
		}
	}
}

class Point {
	int[] x = new int[10];
	int unknown = 0;
	ArrayList<Integer> unknownD;
	Rank[] ranks;

	public Point(String coord) {
		String[] info = coord.split(" ");
		for (int i = 0; i < 10; i++) {
			x[i] = Integer.parseInt(info[i]);
			if (x[i] == -1) {
				x[i] = 500;
				unknown++;
				if (unknownD == null)
					unknownD = new ArrayList<Integer>();
				unknownD.add(i);
			}
		}
	}

	public String toString() {
		String point = "";
		for (int i = 0; i < 10; i++) {
			point += x[i];
			if (i != 9)
				point += ' ';
		}
		return point;
	}
}

class Rank implements Comparable<Rank> {
	int index;
	int rank;

	public Rank(int index, int rank) {
		this.index = index;
		this.rank = rank;
	}

	public int compareTo(Rank other) {
		return rank - other.rank;
	}
}
