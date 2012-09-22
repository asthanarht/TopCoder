package srm421;

public class EquilibriumPoints {
    public double[] getPoints(int[] x, int[] m) {
        double[] e = new double[x.length - 1];
        for (int i = 0; i < x.length - 1; i++) {
            double left = x[i];
            double right = x[i + 1];
            while (right - left >= 1e-10) {
                double mid = (left + right) / 2;
                double f_left = 0;
                double f_right = 0;
                for (int j = 0; j <= i; j++)
                    f_left += m[j] / Math.pow((mid - x[j]), 2);
                for (int j = i + 1; j < x.length; j++)
                    f_right += m[j] / Math.pow((x[j] - mid), 2);
                if (f_left > f_right)
                    left = mid;
                else if (f_left < f_right)
                    right = mid;
                else
                    break;
            }
            e[i] = (left + right) / 2;
        }
        return e;
    }
}
