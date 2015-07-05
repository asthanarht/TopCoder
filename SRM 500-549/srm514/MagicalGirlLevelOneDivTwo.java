public class MagicalGirlLevelOneDivTwo {
    public double theMinDistance(int d, int x, int y) {
        double m = Math.abs(x) - d;
        double n = Math.abs(y) - d;
        if (m < 0)
            m = 0;
        if (n < 0)
            n = 0;
        return Math.sqrt(m * m + n * n);
    }
}
