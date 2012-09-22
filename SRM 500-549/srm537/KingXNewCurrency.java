package srm537;

public class KingXNewCurrency {
    public int howMany(int A, int B, int X) {
        if ((int) gcd(A, B) % X == 0)
            return -1;
        int count = 0;
        for (int i = 1; i <= Math.max(A, B); i++) {
            boolean makeA = false;
            boolean makeB = false;
            for (int j = 0; j <= A; j += i)
                if ((A - j) % X == 0) {
                    makeA = true;
                    break;
                }
            for (int j = 0; j <= B; j += i)
                if ((B - j) % X == 0) {
                    makeB = true;
                    break;
                }
            if (makeA && makeB)
                count++;
        }
        return count;
    }

    public long gcd(long a, long b) {
        long big, small;
        while (b > 1) {
            if (a >= b) {
                big = a;
                small = b;
            }
            else {
                small = a;
                big = b;
            }
            a = small;
            b = big % small;
        }
        return b == 0 ? a : 1;
    }
}