package r1C;

public class PasswordXGrid {
    public int minSum(String[] horizontal, String[] vertical) {
        int n = horizontal.length, m = vertical[0].length();
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                if (i > 0)
                    grid[i][j] = grid[i - 1][j]
                            + (vertical[i - 1].charAt(j) - '0');
                if (j > 0)
                    grid[i][j] = Math.max(grid[i][j], grid[i][j - 1]
                            + (horizontal[i].charAt(j - 1) - '0'));
            }
        return grid[n - 1][m - 1];
    }
}
