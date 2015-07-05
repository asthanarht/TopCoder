public class TheMountain {
    private static int N, M;
    private static long[][] given;
    private static long[][][] sum; // sum starting from 4 corners
    private static long[][][] linesum; // sum from both ends of line and column
    private static long[][][] height; // min height - helper for sum[][][]
    private static final long INFINITE = 1000000000;

    public static int minSum(int n, int m, int[] rowIndex, int[] columnIndex,
            int[] element) {
        N = n;
        M = m;
        given = new long[N + 2][M + 2];
        for (int i = 0; i < rowIndex.length; i++)
            given[rowIndex[i] + 1][columnIndex[i] + 1] = element[i];
        height = new long[4][N + 2][M + 2];
        sum = new long[4][N + 2][M + 2];
        minHeight(0, 1, 1, 1, 1);
        minHeight(1, 1, M, 1, -1);
        minHeight(2, N, 1, -1, 1);
        minHeight(3, N, M, -1, -1);
        sumline();
        long minsum = INFINITE;
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= M; j++) {
                // hill = point + 4 bolck in 4 corners + 4 bars on cross
                long hill = Math.max(
                        Math.max(height[0][i][j], height[1][i][j]),
                        Math.max(height[2][i][j], height[3][i][j]))
                        + sum[0][i - 1][j - 1]
                        + sum[1][i - 1][j + 1]
                        + sum[2][i + 1][j - 1]
                        + sum[3][i + 1][j + 1]
                        + linesum[0][i][j - 1]
                        + linesum[1][i][j + 1]
                        + linesum[2][i - 1][j] + linesum[3][i + 1][j];
                minsum = Math.min(minsum, hill);
            }
        return (int) (minsum >= INFINITE ? -1 : minsum);
    }

    private static void minHeight(int corner, int startx, int starty,
            int xchange, int ychange) {
        for (int i = startx; i >= 1 && i <= N; i += xchange)
            for (int j = starty; j >= 1 && j <= M; j += ychange) {
                height[corner][i][j] = Math.max(height[corner][i - xchange][j],
                        height[corner][i][j - ychange]) + 1;
                if (given[i][j] > 0)
                    height[corner][i][j] = given[i][j] >= height[corner][i][j] ? given[i][j]
                            : INFINITE;
                sum[corner][i][j] = height[corner][i][j]
                        + sum[corner][i - xchange][j]
                        + sum[corner][i][j - ychange]
                        - sum[corner][i - xchange][j - ychange];
            }
    }

    private static void sumline() {
        linesum = new long[4][N + 2][M + 2];
        // row sum
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++)
                linesum[0][i][j] = Math.max(height[0][i][j], height[2][i][j])
                        + linesum[0][i][j - 1];
            for (int j = M; j > 0; j--)
                linesum[1][i][j] = Math.max(height[1][i][j], height[3][i][j])
                        + linesum[1][i][j + 1];
        }
        // column sum
        for (int j = 1; j <= M; j++) {
            for (int i = 1; i <= N; i++)
                linesum[2][i][j] = Math.max(height[0][i][j], height[1][i][j])
                        + linesum[2][i - 1][j];
            for (int i = N; i > 0; i--)
                linesum[3][i][j] = Math.max(height[2][i][j], height[3][i][j])
                        + linesum[3][i + 1][j];
        }
    }
}