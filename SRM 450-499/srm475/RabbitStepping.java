package srm475;

import java.util.Arrays;

public class RabbitStepping {
    private boolean[] check;
    private boolean[] remove;
    private int[] cur;
    private int[] pre;
    private int[] next;
    private int len;
    private int n;
    private char[] cell;

    public double getExpected(String field, int r) {
        cell = field.toCharArray();
        n = field.length();
        int total = 0, count = 0;
        // for all possible initial positions
        for (int mask = 0; mask < (1 << n); mask++)
            if (Integer.bitCount(mask) == r) {
                cur = new int[r]; // current location of all rabbits
                for (int in = 0, ir = 0; in < n; in++)
                    if ((mask & (1 << in)) > 0)
                        cur[ir++] = in;
                for (len = n; len > 2; len--) {
                    next = new int[r]; // next location of all rabbits
                    Arrays.fill(next, -1);
                    check = new boolean[n]; // if a position has a rabbit
                    remove = new boolean[n]; // if a position should be removed
                    for (int ir = 0; ir < r; ir++)
                        if (cur[ir] != -1)
                            move(ir);
                    for (int ir = 0; ir < r; ir++)
                        if (next[ir] != -1 && remove[next[ir]] == true)
                            next[ir] = -1;
                    pre = cur;
                    cur = next;
                }
                for (int ir = 0; ir < r; ir++)
                    if (cur[ir] != -1)
                        total++;
                count++;
            }
        return total * 1.0 / count;
    }

    private void move(int ir) {
        int nextir;
        if (cur[ir] == 0)
            nextir = 1;
        else if (cur[ir] >= len - 2)
            nextir = cur[ir] - 1;
        else {
            if (cell[cur[ir]] == 'B')
                nextir = cur[ir] + 1;
            else if (cell[cur[ir]] == 'W' || len == n)
                nextir = cur[ir] - 1;
            else
                nextir = pre[ir];
        }
        next[ir] = nextir;
        if (check[nextir])
            remove[nextir] = true;
        check[nextir] = true;
    }
}
