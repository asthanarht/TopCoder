import java.util.Arrays;

public class TheFrog {
    private double EPS = 1e-10;

    public double getMinimum(int D, int[] L, int[] R) {
        Arrays.sort(L);
        Arrays.sort(R);
        int minLen = R[0] - L[0];
        int n = L.length;
        for (int i = 1; i < n; i++)
            minLen = Math.max(R[i] - L[i], minLen);
        double result = D;
        for (int i = 0; i < n; i++) {
            double dis = R[i];
            for (int step = 1; step * minLen < dis + EPS; step++) {
                double len = dis / step;
                boolean ok = true;
                for (int j = 0; j < n; j++)
                    if ((int) Math.floor((L[j] + EPS) / len) != (int) Math
                            .floor((R[j] - EPS) / len)) {
                        ok = false;
                        break;
                    }
                if (ok)
                    result = Math.min(result, len);
            }
        }
        return result;
    }
}
