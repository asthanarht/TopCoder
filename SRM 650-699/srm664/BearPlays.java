public class BearPlays {

    public int pileSize(int A, int B, int K) {
        int a = (int) ((A * pow(2, K, A + B)) % (A + B)), b = A + B - a;
        return Math.min(a, b);
    }

    private long pow(long a, long k, long mod) {
        long res = 1, base = a;
        for (int i = 0; i < 32; i++) {
            if ((k & (1 << i)) > 0)
                res = (res * base) % mod;
            base = (base * base) % mod;
        }
        return res;
    }

}
