public class DoubleXor {
    public int calculate(int N) {
        int result = N;
        for (int i = N - 1; i > 0; i--)
            result = go(result, i);
        return result;
    }

    public int go(int n1, int n2) {
        int result = 0;
        for (int i = 1; n1 > 0 || n2 > 0; i *= 10, n1 /= 10, n2 /= 10) {
            int c1 = n1 % 10;
            int c2 = n2 % 10;
            int c3 = (c1 ^ c2) % 10;
            result += i * c3;
        }
        return result;
    }
}
