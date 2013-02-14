package srm570;

public class RobotHerb {
    public long getdist(int T, int[] a) {
        int n = a.length;
        long times = T * (long) n;
        long[][] move = new long[n * 4][4];
        for (int i = 0, d = 0; i < n * 4; i++) {
            int ai = a[i % n];
            move[i][d] = ai;
            d = (d + ai) % 4;
        }
        long[] to = new long[4];
        for (int i = 0; i < times % (n * 4); i++)
            for (int j = 0; j < 4; j++)
                to[j] += move[i][j];
        long[] total = new long[4];
        for (int i = 0; i < n * 4; i++)
            for (int j = 0; j < 4; j++)
                total[j] += move[i][j];
        long result = 0;
        long repeat = times / (n * 4);
        long up = total[0] * repeat + to[0];
        long down = total[2] * repeat + to[2];
        result += Math.abs(up - down);
        long right = total[1] * repeat + to[1];
        long left = total[3] * repeat + to[3];
        result += Math.abs(right - left);
        return result;
    }
}
