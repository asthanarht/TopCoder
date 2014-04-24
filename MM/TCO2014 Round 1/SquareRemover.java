import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Scanner;

public class SquareRemover {
	private static final int MOV = 10000;

	private static Board board;
	private static int[] res = new int[(MOV << 1) + MOV];

	// private static PrintWriter step;
	// private static PrintWriter log;

	public static int[] playIt(int c, String[] b, int s) {
		board = new Board(b.length, new Buffer(c, s));
		board.init(b);
		go();
		return res;
	}

	private static void go() {
		int cnt = 0;
		for (int i = 0; i < res.length;) {
			board.removeSquares();

			Scorer scorer = new OneCellRemoveScorer(board);
			Move move = scorer.getMove();
			if (move == null) {
				scorer = new TwoCellRemoveScorer(board);
				move = scorer.getMove();
				if (move == null) {
					scorer = new ThreeCellRemoveScorer(board);
					move = scorer.getMove();
					if (move == null) {
						cnt++;
						scorer = new TwoStepScorer(board);
						move = scorer.getMove();
					}
				}
			}

			// step.println("=== " + (1 + i / 3) + " ===");
			// step.print(board.board[move.i][move.j] + " ");
			// board.print(1 + i / 3, log);

			res[i++] = move.i;
			res[i++] = move.j;
			res[i++] = move.dir;
			board.applyMove(move.i, move.j, move.dir);

			// step.println(board.board[move.i][move.j]);
		}
		System.err.println("null " + cnt);
	}

	public static void main(String[] args) throws IOException {
		// step = new PrintWriter(new BufferedWriter(new
		// FileWriter("step.txt")));
		// log = new PrintWriter(new BufferedWriter(new FileWriter("log.txt")));
		Scanner in = new Scanner(System.in);
		int c = Integer.parseInt(in.nextLine());
		int n = Integer.parseInt(in.nextLine());
		String[] b = new String[n];
		for (int i = 0; i < n; i++)
			b[i] = in.nextLine();
		int s = Integer.parseInt(in.nextLine());
		in.close();
		playIt(c, b, s);
		// step.close();
		// log.close();
		for (int i = 0; i < res.length; i++)
			System.out.println(res[i]);
	}
}

class Buffer {
	private static final long MOD = 2147483647;
	private static final long MUL = 48271;

	protected int colors, startSeed;

	ArrayList<Integer> buffer = new ArrayList<Integer>();

	public Buffer(int colors, int startSeed) {
		super();
		this.colors = colors;
		this.startSeed = startSeed;
		fillBuffer(128);
	}

	public void fillBuffer(int n) {
		for (int i = 0; i < n; i++) {
			int next = (int) (startSeed % colors);
			startSeed = (int) ((startSeed * MUL) % MOD);
			buffer.add(next);
		}
	}

	public int next() {
		if (buffer.size() == 0)
			fillBuffer(128);
		return buffer.remove(0);
	}
}

class Board {
	final static int[] DR = new int[] { -1, 0, 1, 0 };
	final static int[] DC = new int[] { 0, 1, 0, -1 };

	int N;
	int[][] board;

	Buffer buffer;

	int score = 0;

	public Board(int n, Buffer b) {
		N = n;
		buffer = b;
		board = new int[N][N];
	}

	public void init(int[][] B, int r, int c) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				board[i][j] = B[r + i][c + j];
	}

	public void init(String[] B) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				board[i][j] = B[i].charAt(j) - '0';
	}

	public void removeSquares() {
		while (true) {
			boolean find = false;
			for (int i = 0; i < N - 1 && !find; i++)
				for (int j = 0; j < N - 1 && !find; j++)
					if (board[i][j] == board[i][j + 1]
							&& board[i][j] == board[i + 1][j]
							&& board[i][j] == board[i + 1][j + 1]) {
						find = true;
						board[i][j] = buffer.next();
						board[i][j + 1] = buffer.next();
						board[i + 1][j] = buffer.next();
						board[i + 1][j + 1] = buffer.next();
						score++;
					}
			if (!find)
				break;
		}
	}

	public void applyMove(int mi, int mj, int md) {
		int r = mi + DR[md];
		int c = mj + DC[md];
		int tmp = board[mi][mj];
		board[mi][mj] = board[r][c];
		board[r][c] = tmp;
	}

	public void swap(int mi, int mj, int md) {
		int r = mi + DR[md];
		if (r < 0 || r >= N)
			return;
		int c = mj + DC[md];
		if (c < 0 || c >= N)
			return;
		int tmp = board[mi][mj];
		board[mi][mj] = board[r][c];
		board[r][c] = tmp;
	}
}

class Move {
	int i, j, dir;
	int i2, j2, dir2;
	int i3, j3, dir3;
	int score;

	public Move(int i, int j, int dir) {
		super();
		this.i = i;
		this.j = j;
		this.dir = dir;
	}

	public Move(int i, int j, int dir, int score) {
		super();
		this.i = i;
		this.j = j;
		this.dir = dir;
		this.score = score;
	}

	public Move(int i, int j, int dir, int i2, int j2, int dir2) {
		super();
		this.i = i;
		this.j = j;
		this.dir = dir;
		this.i2 = i2;
		this.j2 = j2;
		this.dir2 = dir2;
	}

	public Move(int i, int j, int dir, int i2, int j2, int dir2, int i3,
			int j3, int dir3, int score) {
		super();
		this.i = i;
		this.j = j;
		this.dir = dir;
		this.i2 = i2;
		this.j2 = j2;
		this.dir2 = dir2;
		this.i3 = i3;
		this.j3 = j3;
		this.dir3 = dir3;
		this.score = score;
	}
}

abstract class Scorer {
	static final int[] DR = new int[] { -1, 0, 1, 0 };
	static final int[] DC = new int[] { 0, 1, 0, -1 };

	final static int SQUARE = 1000000;
	final static int NEXT = 500000;
	final static int TRIPLE = 1000;
	final static int PAIR = 1;

	Board Bo;
	Buffer Bu;
	int N;
	int[][] board;
	ArrayList<Integer> buffer;
	boolean[][] mark;

	public Scorer(Board b) {
		super();
		Bo = b;
		Bu = b.buffer;
		N = b.N;
		board = b.board;
		buffer = b.buffer.buffer;
		mark = new boolean[N][N];
	}

	abstract public Move getMove();

	private static final int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
	private static final int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

	protected int srounding(int r, int c) {
		boolean[] check = new boolean[8];
		for (int i = 0; i < 8; i++) {
			int rr = r + dr[i];
			int cc = c + dc[i];
			if (rr >= 0 && rr < N && cc >= 0 && cc < N)
				check[i] = (board[rr][cc] == board[r][c]);
		}
		int sum = 0;
		if (check[1]) {
			sum += PAIR;
			if (check[2]) {
				sum += TRIPLE;
				if (check[3])
					sum += SQUARE;
			}
		}
		if (check[2]) {
			sum += PAIR;
			if (check[3])
				sum += TRIPLE;
		}
		if (check[3]) {
			sum += PAIR;
			if (check[4]) {
				sum += TRIPLE;
				if (check[5])
					sum += SQUARE;
			}
		}
		if (check[4]) {
			sum += PAIR;
			if (check[5])
				sum += TRIPLE;
		}
		if (check[5]) {
			sum += PAIR;
			if (check[6]) {
				sum += TRIPLE;
				if (check[7])
					sum += SQUARE;
			}
		}
		if (check[6]) {
			sum += PAIR;
			if (check[7])
				sum += TRIPLE;
		}
		if (check[7]) {
			sum += PAIR;
			if (check[0]) {
				sum += TRIPLE;
				if (check[1])
					sum += SQUARE;
			}
		}
		if (check[0]) {
			sum += PAIR;
			if (check[1])
				sum += TRIPLE;
		}
		return sum;
	}

	protected boolean check(int mini, int maxi, int minj, int maxj) {
		for (int i = mini; i < maxi; i++)
			for (int j = minj; j < maxj; j++)
				if (board[i][j] == board[i][j + 1]
						&& board[i][j] == board[i + 1][j]) {
					if (i + 2 <= maxi && board[i][j] == board[i + 2][j + 1])
						return true;
					if (j + 2 <= maxj && board[i][j] == board[i + 1][j + 2])
						return true;
				}
		for (int i = mini; i < maxi; i++)
			for (int j = minj; j < maxj; j++)
				if (board[i][j] == board[i][j + 1]
						&& board[i][j] == board[i + 1][j + 1]) {
					if (i + 2 <= maxi && board[i][j] == board[i + 2][j])
						return true;
					if (j > minj && board[i][j] == board[i + 1][j - 1])
						return true;
				}
		for (int i = mini; i < maxi; i++)
			for (int j = minj; j < maxj; j++)
				if (board[i + 1][j + 1] == board[i][j + 1]
						&& board[i + 1][j + 1] == board[i + 1][j]) {
					if (i > mini && board[i + 1][j + 1] == board[i - 1][j])
						return true;
					if (j > minj && board[i + 1][j + 1] == board[i][j - 1])
						return true;
				}
		for (int i = mini; i < maxi; i++)
			for (int j = minj; j < maxj; j++)
				if (board[i][j] == board[i + 1][j]
						&& board[i][j] == board[i + 1][j + 1]) {
					if (i > mini && board[i][j] == board[i - 1][j + 1])
						return true;
					if (j + 2 <= maxj && board[i][j] == board[i][j + 2])
						return true;
				}
		return false;
	}
}

class TwoStepScorer extends Scorer {

	public TwoStepScorer(Board b) {
		super(b);
	}

	@Override
	public Move getMove() {
		Move move = null;
		int best = 0;
		for (int i = 1; i < N - 1; i++)
			for (int j = 1; j < N - 1; j++)
				for (int dir = 0; dir < 4; dir++) {
					int cur = score(i, j, dir);
					if (cur > best) {
						best = cur;
						move = new Move(i, j, dir);
					}
				}
		return move;
	}

	private int score(int i, int j, int dir) {
		int toi = i + DR[dir], toj = j + DC[dir];
		if (board[i][j] == board[toi][toj])
			return -1;
		int score0 = status(i - 2, i + 2, j - 2, j + 2);
		Bo.applyMove(i, j, dir);
		int score1 = status(i - 2, i + 2, j - 2, j + 2);
		int score2 = Integer.MIN_VALUE;
		for (int d = 0; d < 4; d++)
			if (d != dir) {
				if (board[i][j] == board[i + DR[d]][j + DC[d]]) {
					score2 = Math.max(score2, score1);
					continue;
				}
				Bo.applyMove(i, j, d);
				score2 = Math.max(score2, status(i - 2, i + 2, j - 2, j + 2));
				Bo.applyMove(i, j, d);
			}
		Bo.applyMove(i, j, dir);

		return ((score1 - score0) << 1) + score2 - score0;
	}

	private int status(int mini, int maxi, int minj, int maxj) {
		int status = 0;
		if (mini < 0)
			mini = 0;
		if (maxi >= N)
			maxi = N - 1;
		if (minj < 0)
			minj = 0;
		if (maxj >= N)
			maxj = N - 1;
		for (int r = mini; r < maxi; r++)
			for (int c = minj; c < maxj; c++) {
				int cnt = 0;
				if (board[r][c] == board[r][c + 1])
					cnt++;
				if (board[r][c] == board[r + 1][c])
					cnt++;
				if (board[r][c] == board[r + 1][c + 1])
					cnt++;
				if (cnt == 3)
					status += SQUARE;
				if (cnt == 2)
					status += TRIPLE;
				if (cnt == 1)
					status += PAIR;
			}
		return status;
	}

}

class OneCellRemoveScorer extends Scorer {
	Deque<Integer> temp = new ArrayDeque<Integer>();
	Deque<Integer> R = new ArrayDeque<Integer>();
	Deque<Integer> C = new ArrayDeque<Integer>();

	public OneCellRemoveScorer(Board b) {
		super(b);
	}

	@Override
	public Move getMove() {
		Move move = null;
		int best = SQUARE;
		for (int i = 1; i < N; i++)
			for (int j = 0; j < N; j++) {
				int cur = score(i, j, 0);
				if (cur > best) {
					best = cur;
					move = new Move(i, j, 0);
				}
			}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N - 1; j++) {
				int cur = score(i, j, 1);
				if (cur > best) {
					best = cur;
					move = new Move(i, j, 1);
				}
			}
		return move;
	}

	private int score(int mi, int mj, int dir) {
		int toi = mi + DR[dir], toj = mj + DC[dir];
		if (board[mi][mj] == board[toi][toj])
			return -1;
		Bo.applyMove(mi, mj, dir);
		int score = status(mi - 2, mi + 2, mj - 2, mj + 2);
		Bo.applyMove(mi, mj, dir);

		return score + 1;
	}

	private int status(int mini, int maxi, int minj, int maxj) {
		int status = 0;
		if (mini < 0)
			mini = 0;
		if (maxi >= N)
			maxi = N - 1;
		if (minj < 0)
			minj = 0;
		if (maxj >= N)
			maxj = N - 1;
		for (int r = mini; r < maxi; r++)
			for (int c = minj; c < maxj; c++)
				if (board[r][c] == board[r][c + 1]
						&& board[r][c] == board[r + 1][c]
						&& board[r][c] == board[r + 1][c + 1]) {
					R.add(r);
					C.add(c);
					status = SQUARE;
					store();
					status += status(Math.min(mini, r - 1),
							Math.max(maxi, r + 2), Math.min(minj, c - 1),
							Math.max(maxj, c + 2));
					restore();
					return status;
				}
		status = 0;
		if (check(mini, maxi, minj, maxj))
			status += NEXT;
		for (Iterator<Integer> itrR = R.iterator(), itrC = C.iterator(); itrR
				.hasNext();) {
			int r = itrR.next(), c = itrC.next();
			mark[r][c] = false;
			mark[r][c + 1] = false;
			mark[r + 1][c] = false;
			mark[r + 1][c + 1] = false;
		}
		for (Iterator<Integer> itrR = R.iterator(), itrC = C.iterator(); itrR
				.hasNext();) {
			int r = itrR.next(), c = itrC.next();
			if (!mark[r][c]) {
				status += srounding(r, c);
				mark[r][c] = true;
			}
			if (!mark[r][c + 1]) {
				status += srounding(r, c + 1);
				mark[r][c + 1] = true;
			}
			if (!mark[r + 1][c]) {
				status += srounding(r + 1, c);
				mark[r + 1][c] = true;
			}
			if (!mark[r + 1][c + 1]) {
				status += srounding(r + 1, c + 1);
				mark[r + 1][c + 1] = true;
			}
		}
		return status;
	}

	private void store() {
		int index = temp.size();
		int r = R.peekLast(), c = C.peekLast();
		temp.add(board[r][c]);
		temp.add(board[r][c + 1]);
		temp.add(board[r + 1][c]);
		temp.add(board[r + 1][c + 1]);
		if (buffer.size() < index + 4)
			Bu.fillBuffer(20);
		board[r][c] = buffer.get(index);
		board[r][c + 1] = buffer.get(index + 1);
		board[r + 1][c] = buffer.get(index + 2);
		board[r + 1][c + 1] = buffer.get(index + 3);
	}

	private void restore() {
		int r = R.pollLast(), c = C.pollLast();
		board[r + 1][c + 1] = temp.pollLast();
		board[r + 1][c] = temp.pollLast();
		board[r][c + 1] = temp.pollLast();
		board[r][c] = temp.pollLast();
	}

}

class TwoCellRemoveScorer extends Scorer {
	Deque<Integer> temp = new ArrayDeque<Integer>();
	Deque<Integer> R = new ArrayDeque<Integer>();
	Deque<Integer> C = new ArrayDeque<Integer>();

	public TwoCellRemoveScorer(Board b) {
		super(b);
	}

	@Override
	public Move getMove() {
		Move move = null;
		int best = 0, cur = 0;
		for (int i = 0; i < N - 1; i++)
			for (int j = 0; j < N - 1; j++) {
				// fix top two
				if (board[i][j] == board[i][j + 1]) {
					// change bottom left
					for (int d1 : new int[] { 2, 3 }) {
						Bo.swap(i + 1, j, d1);
						if (board[i][j] == board[i + 1][j]) {
							// change bottom right
							for (int d2 : new int[] { 1, 2 }) {
								Bo.swap(i + 1, j + 1, d2);
								if (board[i][j] == board[i + 1][j + 1])
									cur = Math.max(cur,
											status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 1, j + 1, d2);
							}
							if (cur > best) {
								best = cur;
								move = new Move(i + 1, j, d1);
							}
						}
						Bo.swap(i + 1, j, d1);
					}
				}

				// fix bottom two
				if (board[i + 1][j] == board[i + 1][j + 1]) {
					// change top left
					for (int d1 : new int[] { 0, 3 }) {
						Bo.swap(i, j, d1);
						if (board[i + 1][j] == board[i][j]) {
							// change top right
							for (int d2 : new int[] { 0, 1 }) {
								Bo.swap(i, j + 1, d2);
								if (board[i + 1][j] == board[i][j + 1])
									cur = Math.max(cur,
											status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i, j + 1, d2);
							}
							if (cur > best) {
								best = cur;
								move = new Move(i, j, d1);
							}
						}
						Bo.swap(i, j, d1);
					}
				}

				// fix left two
				if (board[i][j] == board[i + 1][j]) {
					// change top right
					for (int d1 : new int[] { 0, 1 }) {
						Bo.swap(i, j + 1, d1);
						if (board[i][j] == board[i][j + 1]) {
							// change bottom right
							for (int d2 : new int[] { 1, 2 }) {
								Bo.swap(i + 1, j + 1, d2);
								if (board[i][j] == board[i + 1][j + 1])
									cur = Math.max(cur,
											status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 1, j + 1, d2);
							}
							if (cur > best) {
								best = cur;
								move = new Move(i, j + 1, d1);
							}
						}
						Bo.swap(i, j + 1, d1);
					}
				}

				// fix right two
				if (board[i][j + 1] == board[i + 1][j + 1]) {
					// change top left
					for (int d1 : new int[] { 0, 3 }) {
						Bo.swap(i, j, d1);
						if (board[i][j + 1] == board[i][j]) {
							// change bottom left
							for (int d2 : new int[] { 2, 3 }) {
								Bo.swap(i + 1, j, d2);
								if (board[i][j + 1] == board[i + 1][j])
									cur = Math.max(cur,
											status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 1, j, d2);
							}
							if (cur > best) {
								best = cur;
								move = new Move(i, j, d1);
							}
						}
						Bo.swap(i, j, d1);
					}
				}

				// fix diagonal two
				if (board[i][j] == board[i + 1][j + 1]) {
					// change bottom left
					for (int d1 : new int[] { 2, 3 }) {
						Bo.swap(i + 1, j, d1);
						if (board[i][j] == board[i + 1][j]) {
							// change top right
							for (int d2 : new int[] { 0, 1 }) {
								Bo.swap(i, j + 1, d2);
								if (board[i][j] == board[i][j + 1])
									cur = Math.max(cur,
											status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i, j + 1, d2);
								if (cur > best) {
									best = cur;
									move = new Move(i + 1, j, d1);
								}
							}
						}
						Bo.swap(i + 1, j, d1);
					}
				}

				// fix inverse diagonal two
				if (board[i + 1][j] == board[i][j + 1]) {
					// change top left
					for (int d1 : new int[] { 0, 3 }) {
						Bo.swap(i, j, d1);
						if (board[i + 1][j] == board[i][j]) {
							// change bottom right
							for (int d2 : new int[] { 1, 2 }) {
								Bo.swap(i + 1, j + 1, d2);
								if (board[i + 1][j] == board[i + 1][j + 1])
									cur = Math.max(cur,
											status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 1, j + 1, d2);
							}
							if (cur > best) {
								best = cur;
								move = new Move(i, j, d1);
							}
						}
						Bo.swap(i, j, d1);
					}
				}

				// fix three
				if (board[i][j] == board[i][j + 1]
						&& board[i][j] == board[i + 1][j]) {
					// change bottom right from right
					if (j + 2 < N)
						for (int d1 : new int[] { 0, 1, 2 }) {
							Bo.swap(i + 1, j + 2, d1);
							if (board[i][j] == board[i + 1][j + 2]) {
								Bo.swap(i + 1, j + 2, 3);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 1, j + 2, 3);
								if (cur > best) {
									best = cur;
									move = new Move(i + 1, j + 2, d1);
								}
							}
							Bo.swap(i + 1, j + 2, d1);
						}
					// change bottom right from bottom
					if (i + 2 < N)
						for (int d1 : new int[] { 1, 2, 3 }) {
							Bo.swap(i + 2, j + 1, d1);
							if (board[i][j] == board[i + 2][j + 1]) {
								Bo.swap(i + 2, j + 1, 0);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 2, j + 1, 0);
								if (cur > best) {
									best = cur;
									move = new Move(i + 2, j + 1, d1);
								}
							}
							Bo.swap(i + 2, j + 1, d1);
						}
				}

				// fix three
				if (board[i][j] == board[i][j + 1]
						&& board[i][j] == board[i + 1][j + 1]) {
					// change bottom left from left
					if (j > 0)
						for (int d1 : new int[] { 0, 2, 3 }) {
							Bo.swap(i + 1, j - 1, d1);
							if (board[i][j] == board[i + 1][j - 1]) {
								Bo.swap(i + 1, j - 1, 1);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 1, j - 1, 1);
								if (cur > best) {
									best = cur;
									move = new Move(i + 1, j - 1, d1);
								}
							}
							Bo.swap(i + 1, j - 1, d1);
						}
					// change bottom left from bottom
					if (i + 2 < N)
						for (int d1 : new int[] { 1, 2, 3 }) {
							Bo.swap(i + 2, j, d1);
							if (board[i][j] == board[i + 2][j]) {
								Bo.swap(i + 2, j, 0);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i + 2, j, 0);
								if (cur > best) {
									best = cur;
									move = new Move(i + 2, j, d1);
								}
							}
							Bo.swap(i + 2, j, d1);
						}
				}

				// fix three
				if (board[i + 1][j + 1] == board[i][j + 1]
						&& board[i + 1][j + 1] == board[i + 1][j]) {
					// change top left from left
					if (j > 0)
						for (int d1 : new int[] { 0, 2, 3 }) {
							Bo.swap(i, j - 1, d1);
							if (board[i + 1][j + 1] == board[i][j - 1]) {
								Bo.swap(i, j - 1, 1);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i, j - 1, 1);
								if (cur > best) {
									best = cur;
									move = new Move(i, j - 1, d1);
								}
							}
							Bo.swap(i, j - 1, d1);
						}
					// change top left from top
					if (i > 0)
						for (int d1 : new int[] { 0, 1, 3 }) {
							Bo.swap(i - 1, j, d1);
							if (board[i + 1][j + 1] == board[i - 1][j]) {
								Bo.swap(i - 1, j, 2);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i - 1, j, 2);
								if (cur > best) {
									best = cur;
									move = new Move(i - 1, j, d1);
								}
							}
							Bo.swap(i - 1, j, d1);
						}
				}

				// fix three
				if (board[i][j] == board[i + 1][j]
						&& board[i][j] == board[i + 1][j + 1]) {
					// change top right from top
					if (i > 0)
						for (int d1 : new int[] { 0, 1, 3 }) {
							Bo.swap(i - 1, j + 1, d1);
							if (board[i][j] == board[i - 1][j + 1]) {
								Bo.swap(i - 1, j + 1, 2);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i - 1, j + 1, 2);
								if (cur > best) {
									best = cur;
									move = new Move(i - 1, j + 1, d1);
								}
							}
							Bo.swap(i - 1, j + 1, d1);
						}
					// change top right from right
					if (j + 2 < N)
						for (int d1 : new int[] { 0, 1, 2 }) {
							Bo.swap(i, j + 2, d1);
							if (board[i][j] == board[i][j + 2]) {
								Bo.swap(i, j + 2, 3);
								cur = Math.max(cur,
										status(i - 1, i + 2, j - 1, j + 2));
								Bo.swap(i, j + 2, 3);
								if (cur > best) {
									best = cur;
									move = new Move(i, j + 2, d1);
								}
							}
							Bo.swap(i, j + 2, d1);
						}
				}
			}
		if (best < SQUARE)
			return null;
		move.score = best;
		return move;
	}

	private int status(int mini, int maxi, int minj, int maxj) {
		int status = 0;
		if (mini < 0)
			mini = 0;
		if (maxi >= N)
			maxi = N - 1;
		if (minj < 0)
			minj = 0;
		if (maxj >= N)
			maxj = N - 1;
		for (int r = mini; r < maxi; r++)
			for (int c = minj; c < maxj; c++)
				if (board[r][c] == board[r][c + 1]
						&& board[r][c] == board[r + 1][c]
						&& board[r][c] == board[r + 1][c + 1]) {
					R.add(r);
					C.add(c);
					status = SQUARE;
					store();
					status += status(Math.min(mini, r - 1),
							Math.max(maxi, r + 2), Math.min(minj, c - 1),
							Math.max(maxj, c + 2));
					restore();
					return status;
				}
		status = 0;
		for (Iterator<Integer> itrR = R.iterator(), itrC = C.iterator(); itrR
				.hasNext();) {
			int r = itrR.next(), c = itrC.next();
			mark[r][c] = false;
			mark[r][c + 1] = false;
			mark[r + 1][c] = false;
			mark[r + 1][c + 1] = false;
		}
		for (Iterator<Integer> itrR = R.iterator(), itrC = C.iterator(); itrR
				.hasNext();) {
			int r = itrR.next(), c = itrC.next();
			if (!mark[r][c]) {
				status += srounding(r, c);
				mark[r][c] = true;
			}
			if (!mark[r][c + 1]) {
				status += srounding(r, c + 1);
				mark[r][c + 1] = true;
			}
			if (!mark[r + 1][c]) {
				status += srounding(r + 1, c);
				mark[r + 1][c] = true;
			}
			if (!mark[r + 1][c + 1]) {
				status += srounding(r + 1, c + 1);
				mark[r + 1][c + 1] = true;
			}
		}
		return status;
	}

	private void store() {
		int index = temp.size();
		int r = R.peekLast(), c = C.peekLast();
		temp.add(board[r][c]);
		temp.add(board[r][c + 1]);
		temp.add(board[r + 1][c]);
		temp.add(board[r + 1][c + 1]);
		if (buffer.size() < index + 4)
			Bu.fillBuffer(20);
		board[r][c] = buffer.get(index);
		board[r][c + 1] = buffer.get(index + 1);
		board[r + 1][c] = buffer.get(index + 2);
		board[r + 1][c + 1] = buffer.get(index + 3);
	}

	private void restore() {
		int r = R.pollLast(), c = C.pollLast();
		board[r + 1][c + 1] = temp.pollLast();
		board[r + 1][c] = temp.pollLast();
		board[r][c + 1] = temp.pollLast();
		board[r][c] = temp.pollLast();
	}

}

class ThreeCellRemoveScorer extends Scorer {
	Deque<Integer> temp = new ArrayDeque<Integer>();
	Deque<Integer> R = new ArrayDeque<Integer>();
	Deque<Integer> C = new ArrayDeque<Integer>();

	public ThreeCellRemoveScorer(Board b) {
		super(b);
	}

	@Override
	public Move getMove() {
		Move move = null, cur;
		for (int i = 1; i < N; i++)
			for (int j = 0; j < N; j++) {
				cur = score(i, j, 0);
				if (cur != null && (move == null || cur.score > move.score))
					move = cur;
			}
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N - 1; j++) {
				cur = score(i, j, 1);
				if (cur != null && (move == null || cur.score > move.score))
					move = cur;
			}
		return move;
	}

	private Move score(int mi, int mj, int dir) {
		int toi = mi + DR[dir], toj = mj + DC[dir];
		if (board[mi][mj] == board[toi][toj])
			return null;
		Bo.applyMove(mi, mj, dir);
		Scorer scorer = new TwoCellRemoveScorer(Bo);
		Move move = scorer.getMove();
		Bo.applyMove(mi, mj, dir);

		if (move == null)
			return null;
		Move one = new Move(mi, mj, dir, move.score);
		return one;
	}

}