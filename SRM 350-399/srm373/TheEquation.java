package srm373;

public class TheEquation {
    public int leastSum(int X, int Y, int P) {
        int min = P * 2;
        for (int a = 1; a <= P; a++)
            for (int b = 1; b <= P; b++)
                if ((a * X + b * Y) % P == 0)
                    min = Math.min(min, a + b);
        return min;
    }
}
