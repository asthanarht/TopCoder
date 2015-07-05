public class Islands {
    public int beachLength(String[] kingdom) {
        int n = kingdom.length, m = kingdom[0].length();
        int result = 0;
        for (int i = 0; i < n; i++)
            for (int j = 1; j < m; j++)
                if (kingdom[i].charAt(j) != kingdom[i].charAt(j - 1))
                    result++;
        for (int i = 1; i < n; i += 2)
            for (int j = 0; j < m; j++) {
                int[] rows = { i - 1, i + 1 };
                int[] cols = { j, j + 1 };
                for (int r = 0; r < 2; r++)
                    for (int c = 0; c < 2; c++)
                        if (rows[r] >= 0 && rows[r] < n 
                                && cols[c] >= 0 && cols[c] < m
                                && kingdom[rows[r]].charAt(cols[c]) != kingdom[i].charAt(j))
                            result++;
            }
        return result;
    }
}
