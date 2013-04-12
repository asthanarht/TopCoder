package srm576;

import java.util.Arrays;
import java.util.LinkedList;

public class ArcadeManao {
    public int shortestLadder(String[] level, int coinRow, int coinColumn) {
        int m = level.length;
        int n = level[0].length();
        boolean[][] grid = new boolean[m][n]; // platform
        for (int ir = 0; ir < m; ir++)
            for (int ic = 0; ic < n; ic++)
                if (level[ir].charAt(ic) == 'X')
                    grid[ir][ic] = true;

        int[][] min = new int[m][n]; // min ladder to reach
        for (int ir = 0; ir < m; ir++)
            Arrays.fill(min[ir], m - coinRow);
        boolean[][] visit = new boolean[m][n]; // reached
        Arrays.fill(visit[m - 1], true);
        Arrays.fill(min[m - 1], 0);

        Cell[][] join = new Cell[m][n]; // start cell of each platform
        for (int ir = 0; ir < m; ir++)
            for (int ic = 0; ic < n; ic++)
                if (grid[ir][ic]) {
                    if (ic == 0 || !grid[ir][ic - 1])
                        join[ir][ic] = new Cell(ir, ic);
                    else
                        join[ir][ic] = join[ir][ic - 1];
                }
        Cell goal = join[coinRow - 1][coinColumn - 1];

        // from the shortest ladder
        for (int len = 1; len < m - 1 - goal.row && !visit[goal.row][goal.col]; len++) {
            // reached cells
            LinkedList<Integer> row = new LinkedList<Integer>();
            LinkedList<Integer> col = new LinkedList<Integer>();
            // start from reachable cell by ladder of len - 1
            for (int ir = 0; ir < m; ir++)
                for (int ic = 0; ic < n; ic++)
                    if (visit[ir][ic]) {
                        row.add(ir);
                        col.add(ic);
                    }
            while (!row.isEmpty()) {
                int r = row.poll();
                int c = col.poll();
                // for each newly reachable cell
                for (int rnew = Math.max(0, r - len); rnew <= Math.min(m - 1, r
                        + len); rnew++)
                    if (grid[rnew][c] && !visit[rnew][c]) {
                        // get the start of the belonging platform
                        Cell neighbour = join[rnew][c];
                        // mark the entire platform of reachable by len
                        for (int cnew = neighbour.col; cnew < n
                                && grid[rnew][cnew]; cnew++) {
                            visit[rnew][cnew] = true;
                            min[rnew][cnew] = len;
                            row.add(rnew);
                            col.add(cnew);
                        }
                    }
            }
        }

        return min[goal.row][goal.col];
    }
}

class Cell {
    int row;
    int col;

    public Cell(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }
}
