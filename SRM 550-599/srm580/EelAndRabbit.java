package srm580;

public class EelAndRabbit {
    public int getmax(int[] l, int[] t) {
        int n = l.length;
        long[] sig = new long[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (t[i] >= t[j] && t[i] <= t[j] + l[j])
                    sig[i] |= 1 << j;
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                max = Math.max(max, Long.bitCount(sig[i] | sig[j]));
        return max;
    }
}

//public class EelAndRabbit {
//    public int getmax(int[] l, int[] t) {
//        int n = l.length;
//        boolean[][] num = new boolean[n][n];
//        // if (num[i][j]) head of ell[i] in [ell[j].tail, ell[j].head]
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                if (t[i] >= t[j] && t[i] <= t[j] + l[j])
//                    num[i][j] = true;
//        int max = 0;
//        // catch at the head of ell[i] and ell[j]
//        // if (i == j) catch once
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++) {
//                int count = 0;
//                for (int k = 0; k < n; k++)
//                    if (num[i][k] || num[j][k])
//                        count++;
//                max = Math.max(max, count);
//            }
//        return max;
//    }
//}
