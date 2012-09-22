package srm152;

public class FixedPointTheorem {
    private double r;

    public double cycleRange(double R) {
        r = R;
        double y = 0.25;
        for (int i = 0; i < 200000; i++) {
            y = f(y);
        }
        double min = 1;
        double max = 0;
        for (int i = 0; i < 1000; i++) {
            y = f(y);
            min = y < min ? y : min;
            max = y > max ? y : max;
        }
        return max - min;
    }

    public double f(double x) {
        return r * x * (1 - x);
    }
}
