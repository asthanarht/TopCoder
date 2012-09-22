package srm425;

public class InverseFactoring {
    public int getTheNumber(int[] factors) {
        int lcm = factors[0];
        if (factors.length == 1)
            return lcm * lcm;
        for (int i = 1; i < factors.length; i++)
            lcm = (int) lcm(lcm, factors[i]);
        int min = factors[0];
        boolean equal = false;
        for (int i = 0; i < factors.length; i++) {
            min = Math.min(min, factors[i]);
            equal = lcm == factors[i] ? true : equal;
        }
        if (equal)
            return lcm * min;
        return lcm;
    }

    private long gcd(long a, long b) {
        while (b > 1) {
            long big = a >= b ? a : b;
            long small = a >= b ? b : a;
            a = small;
            b = big % small;
        }
        if (b == 0)
            return a;
        else
            return 1;
    }

    private long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}
