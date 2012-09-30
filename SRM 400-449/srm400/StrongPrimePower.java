package srm400;

public class StrongPrimePower {
    public int[] baseAndExponent(String n) {
        Long N = Long.parseLong(n);
        for (int q = 2; q <= 59; q++) {
            int p = (int) Math.round(Math.pow(N, 1.0 / q));
            if (isPrime(p)) {
                long num = 1;
                for (int i = 1; i <= q; i++)
                    num *= p;
                if (num == N)
                    return new int[] { p, q };
            }
        }
        return new int[] {};
    }

    public boolean isPrime(int p) {
        for (int i = 2; i <= Math.sqrt(p); i++)
            if (p % i == 0)
                return false;
        return true;
    }
}
