public class MagicDiamonds {
    public long minimalTransfer(long n) {
        if (n <= 3)
            return n;
        for (long i = 2; i * i <= n; i++)
            if (n % i == 0)
                return 1;
        return 2;
    }
}
