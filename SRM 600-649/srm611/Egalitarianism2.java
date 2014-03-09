package srm611;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// minimum spanning tree depends only on the order of edge weight
// let w1 and w2 be the weight of two edges
// the order of the two edges changes when (w1-ave)^2=(w2-ave)^2
// => ave=(w1+w2)/2
public class Egalitarianism2 {
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private UnionFind mstree;
	private double[] dists;

	public double minStdev(int[] x, int[] y) {
		int n = x.length;
		dists = new double[n - 1];
		ArrayList<Double> dist = new ArrayList<Double>();
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++) {
				double d = dis(x[i], y[i], x[j], y[j]);
				dist.add(d);
				edges.add(new Edge(i, j, d));
			}
		double res = Double.POSITIVE_INFINITY;
		ArrayList<Double> average = new ArrayList<Double>();
		for (int i = 0; i + 1 < dist.size(); i++)
			for (int j = i + 1; j < dist.size(); j++)
				average.add(0.5 * (dist.get(i) + dist.get(j)));
		Collections.sort(average);
		average.add(0, average.get(0) - 1);
		average.add(average.size(), average.get(average.size() - 1) + 1);
		for (int i = 0; i + 1 < average.size(); i++) {
			double ave = 0.5 * (average.get(i) + average.get(i + 1));
			mstree = new UnionFind(n);
			res = Math.min(res, cal(ave));
		}
		return res;
	}

	private double cal(double ave) {
		for (Edge edge : edges)
			edge.update(ave);
		Collections.sort(edges);
		int i = 0;
		for (Edge edge : edges) {
			if (mstree.find(edge.i, edge.j))
				continue;
			mstree.union(edge.i, edge.j);
			dists[i++] = edge.d;
		}
		return stdev();
	}

	private double stdev() {
		double ave = 0;
		for (double d : dists)
			ave += d;
		ave /= dists.length;
		double sum = 0;
		for (double d : dists) {
			double diff = d - ave;
			sum += diff * diff;
		}
		return Math.sqrt(sum / dists.length);
	}

	private double dis(int x1, int y1, int x2, int y2) {
		double dx = x1 - x2, dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}

}

class Edge implements Comparable<Edge> {
	int i, j;
	double d;
	double diff;

	public Edge(int i, int j, double d) {
		super();
		this.i = i;
		this.j = j;
		this.d = d;
		this.diff = d;
	}

	public void update(double ave) {
		diff = Math.abs(d - ave);
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(diff, o.diff);
	}
}

class UnionFind {
	private final int[] tree;

	public UnionFind(int n) {
		this.tree = new int[n];
		Arrays.fill(tree, -1);
	}

	private int root(int x) {
		return tree[x] < 0 ? x : (tree[x] = root(tree[x]));
	}

	public boolean union(int x, int y) {
		x = root(x);
		y = root(y);
		if (x == y)
			return true;
		if (tree[x] > tree[y]) {
			tree[y] += tree[x];
			tree[x] = y;
		} else {
			tree[x] += tree[y];
			tree[y] = x;
		}
		return false;
	}

	public boolean find(int x, int y) {
		return root(x) == root(y);
	}
}