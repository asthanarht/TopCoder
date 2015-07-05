public class TheNumberGameDivOne {
    public String find(long n) {
        long checker = 2;
        while (checker < n)
            checker *= 4;
        return (n % 2 == 1 || n == checker) ? "Brus" : "John";
    }

    // below for finding rules only
    private static int N = 200000;
    private static boolean[] done = new boolean[N];
    private static boolean[] winner = new boolean[N];

    public static void main(String[] args) {
        for (int i = 2; i < N; i += 2)
            if (find2(i).compareTo("John") != 0)
                System.out.println(i);
    }

    private static String find2(int n) {
        return (go(n, true) ? "John" : "Brus");
    }

    private static boolean go(int n, boolean turn) {
        if (done[n])
            return winner[n] ? turn : !turn;
        else {
            boolean win = !turn;
            for (int i = 2; i * i <= n; i++)
                if (n % i == 0) {
                    if (go(n - i, !turn) == turn
                            || go(n - n / i, !turn) == turn)
                        win = turn;
                }
            done[n] = true;
            winner[n] = (win == turn);
            return win;
        }
    }
}
