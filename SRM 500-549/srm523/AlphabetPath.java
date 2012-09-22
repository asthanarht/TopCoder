package srm523;

public class AlphabetPath {
    public String doesItExist(String[] letterMaze) {
        int[] x = { 1, 0, -1, 0 };
        int[] y = { 0, 1, 0, -1 };
        next: for (char c = 'A'; c < 'Z'; c++) {
            for (int i = 0; i < letterMaze.length; i++)
                for (int j = 0; j < letterMaze[0].length(); j++)
                    if (letterMaze[i].charAt(j) == c) {
                        int ci = 0, cj = 0;
                        for (int k = 0; k < 4; k++) {
                            ci = i + x[k];
                            cj = j + y[k];
                            if (ci >= 0 && ci < letterMaze.length && cj >= 0
                                    && cj < letterMaze[0].length())
                                if (letterMaze[ci].charAt(cj) == c + 1)
                                    continue next;
                        }
                        return "NO";
                    }
        }
        return "YES";
    }
}
