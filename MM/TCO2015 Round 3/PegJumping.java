import java.util.ArrayList;
import java.util.Scanner;

public class PegJumping {
	public static int[] pv; // pegValue
	public static int[][] grid; // board
	public static ArrayList<String> moves;
	public static String move;
	public static int score;

	public static char[] jump = { 'U', 'D', 'L', 'R' };
	public static int[] rc = { -1, 1, 0, 0 };
	public static int[] cc = { 0, 0, -1, 1 };

	public static void jump(int r, int c, int d, int s, int len, String path) {
		int r1 = r + rc[d], r2 = r1 + rc[d];
		int c1 = c + cc[d], c2 = c1 + cc[d];

		if (r2 < 0 || r2 >= N || c2 < 0 || c2 >= N || grid[r1][c1] < 0
				|| grid[r2][c2] >= 0) {
			if (s * len > score) {
				score = s * len;
				move = path;
			}
			return;
		}

		int peg = grid[r][c], over = grid[r1][c1];

		grid[r][c] = -1;
		grid[r1][c1] = -1;
		grid[r2][c2] = peg;

		int ns = s + pv[over];
		String npath = path + jump[d];
		for (int nd = 0; nd < 4; nd++)
			jump(r2, c2, nd, ns, len + 1, npath);

		grid[r][c] = peg;
		grid[r1][c1] = over;
		grid[r2][c2] = -1;
	}

	public static void findMove() {
		for (int r = 0; r < N; r++)
			for (int c = 0; c < N; c++)
				if (grid[r][c] >= 0) {
					String path = r + " " + c + " ";
					for (int d = 0; d < 4; d++)
						jump(r, c, d, 0, 0, path);
				}
	}

	public static void addMove() {
		moves.add(move);
		String[] info = move.split(" ");
		int r = Integer.parseInt(info[0]);
		int c = Integer.parseInt(info[1]);
		for (int j = 0; j < info[2].length(); j++) {
			int d = 0;
			while (jump[d] != info[2].charAt(j))
				d++;
			int r1 = r + rc[d], r2 = r1 + rc[d];
			int c1 = c + cc[d], c2 = c1 + cc[d];
			grid[r2][c2] = grid[r][c];
			grid[r1][c1] = -1;
			grid[r][c] = -1;
			r = r2;
			c = c2;
		}
	}

	public static void go() {
		while (true) {
			score = 0; // reset
			findMove();
			if (score == 0)
				break;
			addMove();
		}
	}

	public static String[] getMoves(int[] pegValue, String[] board) {
		pv = new int[pegValue.length];
		for (int i = 0; i < pegValue.length; i++)
			pv[i] = pegValue[i];
		grid = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				if (board[i].charAt(j) == '.')
					grid[i][j] = -1;
				else
					grid[i][j] = board[i].charAt(j) - '0';
		moves = new ArrayList<String>();

		go();

		String[] res = new String[moves.size()];
		for (int i = 0; i < moves.size(); i++)
			res[i] = moves.get(i);
		return res;
	}

	public static int M;
	public static int N;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		M = Integer.parseInt(in.nextLine());
		int[] pegValue = new int[M];
		for (int i = 0; i < M; i++)
			pegValue[i] = Integer.parseInt(in.nextLine());
		N = Integer.parseInt(in.nextLine());
		String[] board = new String[N];
		for (int i = 0; i < N; i++)
			board[i] = in.nextLine();
		in.close();
		String[] res = getMoves(pegValue, board);
		System.out.println(res.length);
		for (int i = 0; i < res.length; i++)
			System.out.println(res[i]);
	}
}
