public class CyclicGame {
    public double estimateBank(int[] cells) {
        int n = cells.length;
        double[] expected = new double[n];
        while (true) {
            boolean updated = false;
            for (int i = 0; i < n; i++) {
                double e = 0;
                for (int j = 1; j <= 6; j++)
                    e += cells[(i + j) % n] + expected[(i + j) % n];
                e /= 6.0;
                if (e > expected[i]) {
                    expected[i] = e;
                    updated = true;
                }
            }
            if (!updated)
                break;
        }
        return expected[0];
    }
}
