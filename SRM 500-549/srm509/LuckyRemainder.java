package srm509;

public class LuckyRemainder {

    public int getLuckyRemainder(String X) {

        int n = X.length();

        int m = 1;
        for (int i = 0; i < n - 1; i++)
            m = m * 2 % 9;

        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += X.charAt(i) - '0';
        m = m * sum % 9;

        return m;
    }

}
