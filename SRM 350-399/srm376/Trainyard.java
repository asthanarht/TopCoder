public class Trainyard {
    private int m;
    private int n;
    private int[][] grid;

    public int reachableSquares(String[] layout, int fuel) {
        m = layout.length;
        n = layout[0].length();
        grid = new int[m * n][m * n];
        int x = 0, y = 0;
        char c1, c2;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                c1 = layout[i].charAt(j);
                if (c1 == '-' || c1 == '+') {
                    if (valid(i, j - 1)) {
                        c2 = layout[i].charAt(j - 1);
                        if (c2 == '-' || c2 == '+' || c2 == 'S')
                            link(i, j, i, j - 1);
                    }
                    if (valid(i, j + 1)) {
                        c2 = layout[i].charAt(j + 1);
                        if (c2 == '-' || c2 == '+' || c2 == 'S')
                            link(i, j, i, j + 1);
                    }
                }
                if (c1 == '|' || c1 == '+') {
                    if (valid(i - 1, j)) {
                        c2 = layout[i - 1].charAt(j);
                        if (c2 == '|' || c2 == '+' || c2 == 'S')
                            link(i, j, i - 1, j);
                    }
                    if (valid(i + 1, j)) {
                        c2 = layout[i + 1].charAt(j);
                        if (c2 == '|' || c2 == '+' || c2 == 'S')
                            link(i, j, i + 1, j);
                    }
                }
                if (c1 == 'S') {
                    x = i;
                    y = j;
                }
            }

        for (int k = 0; k < m * n; k++)
            for (int i = 0; i < m * n; i++)
                for (int j = 0; j < m * n; j++)
                    if (grid[i][k] > 0 && grid[k][j] > 0)
                        grid[i][j] = grid[i][j] == 0 ? grid[i][k] + grid[k][j]
                                : Math.min(grid[i][k] + grid[k][j], grid[i][j]);

        int res = 1;
        int index = index(x, y);
        for (int i = 0; i < m * n; i++)
            if (index != i && grid[index][i] > 0 && grid[index][i] <= fuel)
                res++;

        return res;
    }

    private boolean valid(int i, int j) {
        if (i >= 0 && i < m && j >= 0 && j < n)
            return true;
        return false;
    }

    private void link(int i, int j, int p, int q) {
        if (p >= 0 && p < m && q >= 0 && q < n) {
            int index1 = index(i, j);
            int index2 = index(p, q);
            grid[index1][index2] = 1;
            grid[index2][index1] = 1;
        }
    }

    private int index(int i, int j) {
        return i * n + j;
    }
}
