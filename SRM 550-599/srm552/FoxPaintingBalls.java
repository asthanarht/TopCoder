package srm552;

public class FoxPaintingBalls {
    public long theMax(long R, long G, long B, int N) {
        if (N == 1)
            return R + G + B;
        long total = N * (long) (N + 1) / 2;
        long extra = total % 3;
        long each = total / 3;
        long result = Math.min(R / each, Math.min(G / each, B / each));
        if (extra == 0)
            return result;
        long remain = R + G + B - each * 3 * result;
        if (remain < result)
            result -= (result - remain + 3 * each) / (3 * each + 1);
        return result;
    }
}
