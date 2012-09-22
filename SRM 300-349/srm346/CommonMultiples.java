package srm346;

public class CommonMultiples {
    public int countCommMult(int[] a, int lower, int upper) {
        long lcm = a[0];
        for (int i = 1; i < a.length; i++) {
            lcm = lcm(lcm, a[i]);
            if (lcm > Integer.MAX_VALUE)
                return 0;
        }
        long first = (lower + lcm - 1) / lcm * lcm;
        if (first <= upper)
            return (int) ((upper - first) / lcm + 1);
        else
            return 0;
    }

    public long gcd(long x, long y) {
        long hold;
        while (y != 0) {
            hold = x % y;
            x = y;
            y = hold;
        }
        return x;
    }

    public long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
