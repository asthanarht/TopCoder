package srm623;

import java.util.ArrayList;
import java.util.Collections;

public class CatchTheBeat {

    public int maxCatched(int n, int x0, int y0, int a, int b, int c, int d,
            int mod1, int mod2, int offset) {
        long[] x = new long[n], y = new long[n];
        x[0] = x0;
        for (int i = 1; i < n; i++)
            x[i] = (x[i - 1] * a + b) % mod1;
        for (int i = 0; i < n; i++)
            x[i] -= offset;
        y[0] = y0;
        for (int i = 1; i < n; i++)
            y[i] = (y[i - 1] * c + d) % mod2;

        ArrayList<Point> point = new ArrayList<Point>();
        for (int i = 0; i < n; i++)
            if (Math.abs(x[i]) <= y[i])
                point.add(new Point(x[i] + y[i], y[i] - x[i]));

        Collections.sort(point);

        long[] h = new long[point.size()];
        for (int i = 0; i < point.size(); i++)
            h[i] = point.get(i).y;

        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        return lis.nlogn(h).length;
    }

}

class Point implements Comparable<Point> {
    long x, y;

    public Point(long x, long y) {
        super();
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        if (x == p.x)
            return Long.compare(y, p.y);
        return Long.compare(x, p.x);
    }

}

class LongestIncreasingSubsequence {

    public long[] nlogn(long[] x) {
        int n = x.length;
        int[] pre = new int[n];
        int[] endi = new int[n + 1];
        int len = 0;
        for (int i = 0; i < n; i++) {
            int left = 1, right = len;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (x[endi[mid]] <= x[i])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
            int nlen = left;
            pre[i] = endi[nlen - 1];
            endi[nlen] = i;
            if (nlen > len)
                len = nlen;
        }

        long[] lis = new long[len];
        int last = endi[len];
        for (int i = len - 1; i >= 0; i--) {
            lis[i] = x[last];
            last = pre[last];
        }

        return lis;
    }

}
