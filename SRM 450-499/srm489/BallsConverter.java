package srm489;

public class BallsConverter {
    public String theGood(String[] convert) {
        int n = convert.length;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                char c = convert[i].charAt(j);
                if (c >= 'A' && c <= 'Z')
                    grid[i][j] = c - 'A';
                else
                    grid[i][j] = 26 + (c - 'a');
            }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    if (grid[grid[i][j]][k] != grid[i][grid[j][k]])
                        return "Bad";
        return "Good";
    }
}
