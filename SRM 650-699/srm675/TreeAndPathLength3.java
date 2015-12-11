public class TreeAndPathLength3 {

    // o              o
    //  \            /
    //   \          /
    // o--o        o--o
    //   / \      / \
    //  /   \    /   \
    // o  o--o--o--o  o
    //      /    \
    //     /      \
    //    o        o
    // i  j  1  1  m  n

    public int[] construct(int s) {
        int max = 1, r = 10000;
        for (; max <= 125 && r > 0; max++)
            r = remain(max);
        for (int i = 0; i <= max; i++)
            for (int j = 1; j <= max; j++)
                for (int m = 1; m <= max; m++)
                    for (int n = 0; n <= max; n++) {
                        int cnt = i * j + m * n + m * j;
                        if (cnt == s) {
                            int nnode = 2 + i + j + m + n;
                            int[] res = new int[nnode + nnode - 2];
                            int ri = 0;
                            for (int k = 0; k < i; res[ri++] = i, k++)
                                res[ri++] = k;
                            for (int k = 0; k < j; res[ri++] = nnode - 1, k++)
                                res[ri++] = i + k;
                            for (int k = 0; k < m; res[ri++] = nnode - 2, k++)
                                res[ri++] = i + j + k;
                            for (int k = 0; k < n; res[ri++] = i + j, k++)
                                res[ri++] = i + j + m + k;
                            res[ri++] = nnode - 2;
                            res[ri++] = nnode - 1;
                            return res;
                        }
                    }
        return new int[] {};
    }

    private int remain(int max) {
        int r = 10000;
        boolean[] pool = new boolean[10001];
        for (int i = 0; i <= max; i++)
            for (int j = 1; j <= max; j++)
                for (int m = 1; m <= max; m++)
                    for (int n = 0; n <= max; n++) {
                        int cnt = i * j + m * n + m * j;
                        if (cnt >= 1 && cnt <= 10000) {
                            if (!pool[cnt])
                                r--;
                            if (r == 0)
                                return 0;
                            pool[cnt] = true;
                        }
                    }
        return r;
    }

}
