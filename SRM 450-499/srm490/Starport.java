package srm490;

public class Starport {
    public double getExpectedTime(int N, int M) {
        double gcd = gcd(M, N);
        double p = N / gcd;
        return (p - 1) * N / p * 0.5;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int tem = a % b;
            a = b;
            b = tem;
        }
        return a;
    }
}
