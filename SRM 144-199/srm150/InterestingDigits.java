import java.util.ArrayList;

public class InterestingDigits {
    private int B;// base
    private int N;// max integer to test in base 10
    private int max;// max value of a digit in the given base

    public int[] digits(int base) {
        B = base;
        max = base - 1;
        N = (int) (max * Math.pow(B, 0) + max * Math.pow(B, 1) + max
                * Math.pow(B, 2));
        ArrayList<Integer> set = new ArrayList<Integer>();
        for (int i = 2; i < B; i++) {
            if (check(i))
                set.add(i);
        }
        int[] array = new int[set.size()];
        for (int i = 0; i < array.length; i++)
            array[i] = set.get(i).intValue();
        return array;
    }

    private boolean check(int num) {
        for (int i = 0; i <= N; i++) {
            int multi = i * num;
            int sum = 0;
            while (multi > 0) {
                sum += multi % B;
                multi /= B;
            }
            if (sum % num != 0)
                return false;
        }
        return true;
    }
}
