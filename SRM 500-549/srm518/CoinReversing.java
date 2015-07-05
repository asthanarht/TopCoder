public class CoinReversing {
    public double expectedHeads(int N, int[] a) {
        double h = N;
        double t = 0;
        for (int i = 0; i < a.length; i++) {
            double change = (t - h) * a[i] / N;
            h += change;
            t -= change;
        }
        return h;
    }
}
