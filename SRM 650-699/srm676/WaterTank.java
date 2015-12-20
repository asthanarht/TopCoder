public class WaterTank {

    public double minOutputRate(int[] t, int[] x, int C) {
        double EPS = 1e-8;
        double left = 0, right = 1000000;
        while (right - left > EPS) {
            double mid = (left + right) / 2;
            if (test(mid, t, x, C))
                right = mid;
            else
                left = mid;
        }
        return right;
    }

    private boolean test(double mid, int[] t, int[] x, int C) {
        double c = 0;
        for (int i = 0; i < t.length; i++) {
            c = Math.max(0, c + t[i] * (x[i] - mid));
            if (c > C)
                return false;
        }
        return true;
    }

}
