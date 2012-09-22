package srm392;

import java.util.Arrays;

public class QuasiLatinSquares {
    private static int[][] square;

    public static String[] makeSquare(int n, int d) {
        square = new int[n][n];
        for (int i = 0; i <= n - d; i++)
            for (int j = n - d; j < n; j++) {
                square[i][j] = Math.abs(j + d - n);
                square[j][i] = square[i][j];
            }

        boolean[][] rCheck = new boolean[d][d];
        boolean[][] cCheck = new boolean[d][d];
        int offset = n - d;
        Arrays.fill(rCheck[0], true);
        Arrays.fill(cCheck[0], true);
        for (int i = 1; i < d; i++) {
            rCheck[i][square[offset][i + offset]] = true;
            cCheck[i][square[i + offset][offset]] = true;
        }
        dfs(1, 1, offset, d, rCheck, cCheck);

        // to String
        String[] ans = new String[n];
        for (int i = 0; i < n; i++)
            ans[i] = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                ans[i] += square[i][j] + " ";
            ans[i] = ans[i].trim();
        }
        return ans;
    }

    private static boolean dfs(int r, int c, int offset, int n,
            boolean[][] rCheck, boolean[][] cCheck) {
        if (c >= n) {
            r++;
            c = 1;
        }
        if (r >= n)
            return true;
        for (int value = 0; value < n; value++)
            if (rCheck[r][value] || cCheck[c][value])
                continue;
            else {
                square[offset + r][offset + c] = value;
                rCheck[r][value] = true;
                cCheck[c][value] = true;
                if (dfs(r, c + 1, offset, n, rCheck, cCheck))
                    return true;
                rCheck[r][value] = false;
                cCheck[c][value] = false;
            }
        return false;
    }
}
