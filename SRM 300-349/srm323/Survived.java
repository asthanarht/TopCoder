package srm323;

public class Survived {
    public double minTime(int x, int y, int V, int U) {
        double a = U * U - V * V;
        double b = -2 * x * U;
        double c = x * x + y * y;
        if (a == 0) {
            if (b == 0)
                return c == 0 ? 0 : -1;
            double t = -c / b;
            return t >= 0 ? t : -1;
        }
        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0)
            return -1;
        double d = Math.sqrt(discriminant);
        double r1 = (-b + d) / 2.0 / a;
        double r2 = (-b - d) / 2.0 / a;
        double min = Math.min(r1, r2);
        double max = Math.max(r1, r2);
        if (min >= 0)
            return min;
        if (max >= 0)
            return max;
        return -1;
    }
}
