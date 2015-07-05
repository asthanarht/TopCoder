public class TheTree {
    public int maximumDiameter(int[] cnt) {
        int m = cnt.length, n = cnt.length + 1;
        int[] tree = new int[n];
        tree[0] = 1;
        for (int i = 0; i < m; i++)
            tree[i + 1] = cnt[i];
        int max = 0;
        for (int turn = 0; turn < n; turn++)
            if (tree[turn] == 1) {
                boolean found = false;
                for (int end = turn; end < m && !found; end++)
                    if (tree[end + 1] == 1) {
                        max = Math.max(max, end - turn + m - turn);
                        found = true;
                    }
                if (!found)
                    max = Math.max(max, (m - turn) << 1);
            }
        return max;
    }
}

//public class TheTree {
//    public int maximumDiameter(int[] cnt) {
//        int[] tree = new int[cnt.length + 1];
//        tree[0] = 1;
//        for (int i = 0; i < cnt.length; i++)
//            tree[i + 1] = cnt[i];
//        int max = 0;
//        for (int start = 0; start < tree.length; start++)
//            for (int end = 0; end < tree.length; end++)
//                for (int turn = 0; turn <= Math.min(start, end); turn++) {
//                    boolean ok = true;
//                    for (int i = turn + 1; i <= Math.min(start, end); i++)
//                        if (tree[i] == 1)
//                            ok = false;
//                    if (ok)
//                        max = Math.max(max, start - turn + end - turn);
//                }
//        return max;
//    }
//}
