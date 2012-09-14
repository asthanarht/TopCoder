package srm504_5;

public class TheNumbersWithLuckyLastDigit {

    public int find(int n) {

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            int sum = 7 * i;
            if (sum <= n && (n - sum) % 10 == 0)
                if (i < min && i != 0)
                    min = i;
            for (int j = 1; j < 10; j++) {
                int sum2 = sum + 4 * j;
                if (sum2 <= n && (n - sum2) % 10 == 0)
                    if (i + j < min)
                        min = i + j;
            }
        }

        if (min < Integer.MAX_VALUE)
            return min;

        return -1;

    }

}
