public class RotatingBot {
    public int minArea(int[] moves) {
        int n = moves.length;
        if (n == 1)
            return moves[0] + 1;
        if (n == 2)
            return (moves[0] + 1) * (moves[1] + 1);
        if (n == 3)
            return (Math.max(moves[0], moves[2]) + 1) * (moves[1] + 1);
        boolean[][] grid = new boolean[102][102];
        int[] dr = { 0, 1, 0, -1 };
        int[] dc = { 1, 0, -1, 0 };
        int r = 50, c = 50;
        int maxr = r + moves[1], minr = Math.min(r, maxr - moves[3]), maxc = c
                + moves[0], minc = Math.min(c, maxc - moves[2]);
        grid[r][c] = true;
        for (int i = 0, d = 0; i < moves.length; i++, d = (d + 1) % 4) {
            for (int j = 0; j < moves[i]; j++, grid[r][c] = true) {
                r += dr[d];
                c += dc[d];
                if (grid[r][c] || r < minr || r > maxr || c < minc || c > maxc)
                    return -1;
            }
            if (i != moves.length - 1) {
                int rn = r + dr[d];
                int cn = c + dc[d];
                if (rn >= minr && rn <= maxr && cn >= minc && cn <= maxc
                        && !grid[rn][cn])
                    return -1;
            }
        }
        return (maxc - minc + 1) * (maxr - minr + 1);
    }
}