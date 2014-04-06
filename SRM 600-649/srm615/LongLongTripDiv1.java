package srm615;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class LongLongTripDiv1 {

	@SuppressWarnings("unchecked")
	public String isAble(int N, int[] A, int[] B, int[] D, long T) {
		ArrayList<Edge>[] graph = new ArrayList[N];
		for (int i = 0; i < N; i++)
			graph[i] = new ArrayList<Edge>();
		for (int i = 0; i < A.length; i++) {
			graph[A[i]].add(new Edge(A[i], B[i], D[i]));
			graph[B[i]].add(new Edge(B[i], A[i], D[i]));
		}

		if (graph[0].size() == 0)
			return "Impossible";

		int MOD = graph[0].get(0).len * 2;
		long[][] minTime = new long[N][MOD];
		for (int i = 0; i < N; i++)
			Arrays.fill(minTime[i], Long.MAX_VALUE);

		PriorityQueue<Status> pq = new PriorityQueue<Status>();
		pq.add(new Status(0, 0, 0));
		while (pq.size() > 0) {
			Status s = pq.poll();
			int from = s.node, mod = s.mod;
			long time = s.time;
			for (int i = 0; i < graph[from].size(); i++) {
				Edge e = graph[from].get(i);
				int next = e.to, nextMod = (mod + e.len) % MOD;
				long nextTime = time + e.len;
				if (nextTime < minTime[next][nextMod]) {
					minTime[next][nextMod] = nextTime;
					pq.add(new Status(next, nextMod, nextTime));
				}
			}
		}

		return minTime[N - 1][(int) (T % MOD)] <= T ? "Possible" : "Impossible";
	}

}

class Edge {
	int from, to, len;

	public Edge(int from, int to, int len) {
		super();
		this.from = from;
		this.to = to;
		this.len = len;
	}
}

class Status implements Comparable<Status> {
	int node, mod;
	long time;

	public Status(int node, int mod, long time) {
		super();
		this.node = node;
		this.mod = mod;
		this.time = time;
	}

	@Override
	public int compareTo(Status o) {
		return Long.compare(time, o.time);
	}
}