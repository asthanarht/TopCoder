public class TheAlmostLuckyNumbersDivOne {
    private long n1;
    private long n2;

    public long find(long a, long b) {
        n1 = a;
        n2 = b;
        return check(0, false);
    }

    private long check(long n, boolean isUsed) {
        if (n > n2)
            return 0;
        long count = 0;
        if (n >= n1)
            count++;
        if (isUsed) {
            count += check(n * 10 + 4, true);
            count += check(n * 10 + 7, true);
        }
        else {
            if (n == 0)
                for (int i = 1; i < 10; i++)
                    if (i == 4 || i == 7)
                        count += check(n * 10 + i, false);
                    else
                        count += check(n * 10 + i, true);
            else
                for (int i = 0; i < 10; i++)
                    if (i == 4 || i == 7)
                        count += check(n * 10 + i, false);
                    else
                        count += check(n * 10 + i, true);
        }
        return count;
    }
}
