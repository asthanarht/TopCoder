public class LuckyXor {

    public int construct(int a) {
        for (int i = a + 1; i <= 100; i++)
            if (lucky(a, i))
                return i;
        return -1;
    }

    private boolean lucky(int a, int b) {
        int xor = a ^ b;
        while (xor > 0) {
            int d = xor % 10;
            if (d != 4 && d != 7)
                return false;
            xor /= 10;
        }
        return true;
    }

}
