package srm361;

public class WhiteHats {
    boolean[] white; // true means must be white
    boolean[] black; // true means must be black

    public int whiteNumber(int[] count) {
        int n = count.length;
        white = new boolean[n];
        black = new boolean[n];
        for (int i = 0; i < n; i++) {
            int w = count[i];
            int count_nn = 0, count_n = 0, count_np = 0;
            for (int j = 0; j < n; j++)
                if (j != i)
                    if (count[j] == w - 1)
                        count_nn++;
                    else if (count[j] == w)
                        count_n++;
                    else if (count[j] == w + 1)
                        count_np++;
                    else
                        return -1;
            boolean wh = false, bh = false;
            if (count_nn == w && count_n == n - 1 - w && count_np == 0)
                bh = true;
            if (count_nn == 0 && count_n == w && count_np == n - 1 - w)
                wh = true;
            if (!bh && !wh)
                return -1;
            if (bh && !wh) {
                black[i] = true;
                for (int j = 0; j < n; j++)
                    if (j != i)
                        if (count[j] == w - 1)
                            white[j] = true;
                        else
                            black[j] = true;
            }
            if (!bh && wh) {
                white[i] = true;
                for (int j = 0; j < n; j++)
                    if (j != i)
                        if (count[j] == w)
                            white[j] = true;
                        else
                            black[j] = true;
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++)
            if (white[i]) {
                res++;
                if (black[i])
                    return -1;
            }
        return res;
    }
}
