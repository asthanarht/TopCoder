package srm519;

import java.util.StringTokenizer;

public class ThreeTeleports {
    private int x1[] = new int[3];
    private int y1[] = new int[3];
    private int x2[] = new int[3];
    private int y2[] = new int[3];
    private int X;
    private int Y;
    private int dis;

    public int shortestDistance(int xMe, int yMe, int xHome, int yHome,
            String[] teleports) {
        X = xHome;
        Y = yHome;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(teleports[i]);
            x1[i] = Integer.parseInt(st.nextToken());
            y1[i] = Integer.parseInt(st.nextToken());
            x2[i] = Integer.parseInt(st.nextToken());
            y2[i] = Integer.parseInt(st.nextToken());
        }
        dis = (int) go(0, xMe, yMe, true, true, true);
        return dis;
    }

    private long go(long dis, int x, int y, boolean t1, boolean t2, boolean t3) {
        long disH = dis + Math.abs(X - x) + Math.abs(Y - y);
        long dist1a = Integer.MAX_VALUE;
        long dist1b = Integer.MAX_VALUE;
        long dist2a = Integer.MAX_VALUE;
        long dist2b = Integer.MAX_VALUE;
        long dist3a = Integer.MAX_VALUE;
        long dist3b = Integer.MAX_VALUE;
        if (t1) {
            long d1 = dis + Math.abs(x1[0] - x) + Math.abs(y1[0] - y) + 10;
            dist1a = go(d1, x2[0], y2[0], false, t2, t3);
            long d2 = dis + Math.abs(x2[0] - x) + Math.abs(y2[0] - y) + 10;
            dist1b = go(d2, x1[0], y1[0], false, t2, t3);
        }
        if (t2) {
            long d1 = dis + Math.abs(x1[1] - x) + Math.abs(y1[1] - y) + 10;
            dist2a = go(d1, x2[1], y2[1], t1, false, t3);
            long d2 = dis + Math.abs(x2[1] - x) + Math.abs(y2[1] - y) + 10;
            dist2b = go(d2, x1[1], y1[1], t1, false, t3);
        }
        if (t3) {
            long d1 = dis + Math.abs(x1[2] - x) + Math.abs(y1[2] - y) + 10;
            dist3a = go(d1, x2[2], y2[2], t1, t2, false);
            long d2 = dis + Math.abs(x2[2] - x) + Math.abs(y2[2] - y) + 10;
            dist3b = go(d2, x1[2], y1[2], t1, t2, false);
        }
        long min = Math.min(dist1a, dist1b);
        min = Math.min(min, dist2a);
        min = Math.min(min, dist2b);
        min = Math.min(min, dist3a);
        min = Math.min(min, dist3b);
        min = Math.min(min, disH);
        return min;
    }
}