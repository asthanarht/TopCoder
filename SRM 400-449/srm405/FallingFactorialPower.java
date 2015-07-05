public class FallingFactorialPower {

    public double compute(int n, int k) {
        double num = 1;

        if (k == 0)
            return 1;

        else if (k > 0) {
            for (int i = 0; i < k; i++)
                num *= (n - i);
            return num;
        }

        else {
            for (int i = 0; i > k; i--)
                num /= (n - i + 1);
            return num;
        }
    }

}
