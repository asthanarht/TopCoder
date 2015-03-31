package reusable;

public class FlowNetworkMain {
	public static void main(String[] args) {
		int n = 6;
		int[][] cap = new int[n][n];
		cap[0][1] = 16;
		cap[0][2] = 13;
		cap[1][3] = 12;
		cap[2][1] = 4;
		cap[2][4] = 14;
		cap[3][2] = 9;
		cap[3][5] = 20;
		cap[4][3] = 7;
		cap[4][5] = 4;
		EdmondsKarp maxflow = new EdmondsKarp(cap, 0, n - 1);
		int mf = maxflow.maxflow();
		System.out.println(mf);
	}
}
