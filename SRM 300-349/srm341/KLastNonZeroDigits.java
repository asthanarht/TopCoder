package srm341;

public class KLastNonZeroDigits {
    public String getKDigits(int N, int K) {
        long res = 1;
        for (int i = 2; i <= N; i++) {
            res %= 1000000000000L;
            res *= i;
            while (res % 10 == 0)
                res /= 10;
        }
        String s = String.valueOf(res);
        return s.substring(Math.max(0, s.length() - K));
    }
}
