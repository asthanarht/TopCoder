package srm614;

public class TorusSailingEasy {

	public double expectedTime(int N, int M, int goalX, int goalY) {
		boolean[][] check = new boolean[N][M];
		int x = 0, y = 0;
		int total = 0, move = 0;
		while (!check[x][y]) {
			check[x][y] = true;
			if (x == goalX && y == goalY)
				move = total;
			total++;
			x = (x + 1) % N;
			y = (y + 1) % M;
		}
		if (!check[goalX][goalY])
			return -1;

		return move * (total - move);
	}

}
