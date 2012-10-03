package srm494;

public class Painting {
    public int largestBrush(String[] picture) {
        int R = picture.length, C = picture[0].length();
        boolean[][] pic = new boolean[R][C];
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (picture[i].charAt(j) == 'B')
                    pic[i][j] = true;
        for (int max = Math.min(R, C); max > 1; max--) {
            boolean[][] test =  new boolean[R][C];
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    if (picture[i].charAt(j) == 'B')
                        test[i][j] = true;
            for (int r = 0; r + max <= R; r++)
                for (int c = 0; c + max <= C; c++)
                    if (pic[r][c]) {
                        boolean ok = true;
                        for (int i = 0; i < max && ok; i++)
                            for (int j = 0; j < max && ok; j++)
                                if (!pic[r + i][c + j])
                                    ok = false;
                        if (ok)
                            for (int i = 0; i < max; i++)
                                for (int j = 0; j < max; j++)
                                    test[r + i][c + j] = false;
                    }
            boolean remain = false;
            for (int i = 0; i < R && !remain; i++)
                for (int j = 0; j < C && !remain; j++)
                    if (test[i][j])
                        remain = true;
            if (!remain)
                return max;
        }
        return 1;
    }
}