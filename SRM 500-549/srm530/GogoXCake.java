public class GogoXCake {
    char[][] ca;
    boolean[][] cu;
    int n = 0;

    public String solve(String[] cake, String[] cutter) {
        ca = new char[cake.length][cake[0].length()];
        cu = new boolean[cutter.length][cutter[0].length()];
        int count = 0;
        for (int i = 0; i < cake.length; i++)
            for (int j = 0; j < cake[0].length(); j++) {
                ca[i][j] = cake[i].charAt(j);
                if (ca[i][j] == '.')
                    count++;
            }
        for (int i = 0; i < cutter.length; i++)
            for (int j = 0; j < cutter[0].length(); j++) {
                cu[i][j] = cutter[i].charAt(j) == '.' ? true : false;
                if (cu[i][j])
                    n++;
            }
        if (dfs(ca, count))
            return "YES";
        return "NO";
    }

    private boolean dfs(char[][] cake, int count) {
        if (count == 0)
            return true;
        if (count < n)
            return false;
        for (int i = 0; i <= ca.length - cu.length; i++)
            for (int j = 0; j <= ca[0].length - cu[0].length; j++)
                if (fillok(cake, i, j))
                    if (dfs(fill(cake, i, j), count - n))
                        return true;
        return false;
    }

    private boolean fillok(char[][] cake, int m, int n) {
        if (m + cu.length > ca.length || n + cu[0].length > ca[0].length)
            return false;
        for (int i = 0; i < cu.length; i++)
            for (int j = 0; j < cu[0].length; j++) {
                if (cu[i][j] && cake[m + i][n + j] != '.')
                    return false;
            }
        return true;
    }

    private char[][] fill(char[][] cake, int m, int n) {
        char[][] filled = cake.clone();
        for (int i = 0; i < cu.length; i++)
            for (int j = 0; j < cu[0].length; j++)
                if (cu[i][j])
                    filled[m + i][n + j] = 'X';
        return filled;
    }
}
