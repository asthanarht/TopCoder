public class BirthNumbersValidator {
    private static int[] D = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public String[] validate(String[] test) {
        int n = test.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++)
            if (check(test[i]))
                res[i] = "YES";
            else
                res[i] = "NO";
        return res;
    }

    private static boolean check(String s) {
        long n = Long.parseLong(s);
        if (n % 11 != 0)
            return false;
        n = n / 10000;
        int d = (int) (n % 100);
        n = n / 100;
        int m = (int) (n % 100);
        n = n / 100;
        int y = (int) n;
        boolean leap = leap(y);
        if (m > 50)
            m -= 50;
        if (m < 1 || m > 12)
            return false;
        int expectD = (m == 2 && leap) ? 29 : D[m - 1];
        if (expectD < d || d <= 0)
            return false;
        return true;
    }

    private static boolean leap(int y) {
        if ((y % 400 == 0) || (y % 4 == 0 && y % 100 != 0))
            return true;
        return false;
    }
}
