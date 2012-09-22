package srm352;

import java.util.Arrays;

public class FibonacciKnapsack {
    private static long max = 0;
    private static long[] ws, cs;
    private static int n;
    private static Item[] itemx;
    private static boolean[] tick;

    public long maximalCost(String[] items, String C) {
        n = items.length;
        itemx = new Item[n];
        for (int i = 0; i < n; i++) {
            String[] info = items[i].split(" ");
            itemx[i] = new Item(Long.parseLong(info[0]),
                    Long.parseLong(info[1]));
        }
        Arrays.sort(itemx);
        ws = new long[n];
        cs = new long[n];
        for (int i = n - 1; i >= 0; i--) {
            ws[i] = itemx[i].w + (i == n - 1 ? 0 : ws[i + 1]);
            cs[i] = itemx[i].c + (i == n - 1 ? 0 : cs[i + 1]);
        }

        tick = new boolean[n];
        dfs(0, Long.parseLong(C), 0);
        return max;
    }

    private void dfs(int k, long remain, long c) {
        max = Math.max(max, c);
        if (k == n)
            return;
        if (remain >= ws[k]) // if could hold all the other one
            dfs(n, 0, c + cs[k]); // extra capacity does not matter
        if (c + cs[k] <= max) // if remaining items can not make a better answer
            return;
        for (int i = k; i < n; i++) {
            // if item a and b weight the same, then if the higher cost one is
            // not
            // used, the lower cost one should not be considered.
            if (i > 0 && itemx[i].w == itemx[i - 1].w && !tick[i - 1])
                continue;
            if (remain >= itemx[i].w) {
                tick[i] = true;
                dfs(i + 1, remain - itemx[i].w, c + itemx[i].c);
                tick[i] = false;
            }
        }
    }
}

class Item implements Comparable<Object> {
    long w, c;

    public Item(long w, long c) {
        super();
        this.w = w;
        this.c = c;
    }

    public int compareTo(Object arg0) {
        long compare = ((Item) arg0).w - w;
        if (compare == 0)
            compare = ((Item) arg0).c - c;
        return compare > 0 ? 1 : compare == 0 ? 0 : -1;
    }
}
