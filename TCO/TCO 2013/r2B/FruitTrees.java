package r2B;

public class FruitTrees {
    public int maxDist(int apple, int kiwi, int grape) {
        int[] len = { apple, kiwi, grape };
        // gcd is the minimum gap between two types of trees
        int[] gcd = { gcd(len[0], len[1]), gcd(len[0], len[2]),
                gcd(len[1], len[2]) };
        if (gcd[0] == gcd[1] && gcd[1] == gcd[2])
            return gcd[0] / 3;
        return Math.min(gcd[0], Math.min(gcd[1], gcd[2])) / 2;
    }

    private static int gcd(long a, long b) {
        while (b != 0) {
            long c = a % b;
            a = b;
            b = c;
        }
        return (int) a;
    }
}
