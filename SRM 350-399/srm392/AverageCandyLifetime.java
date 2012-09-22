package srm392;

public class AverageCandyLifetime {
    public double getAverage(int[] eatenCandies) {
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        int[] day = new int[12];
        double total = 0;
        int count = 0;
        for (int i = 0; i < eatenCandies.length; i++) {
            if (i == 0)
                day[i] = days[i];
            else
                day[i] = day[i - 1] + days[i];
            total += eatenCandies[i] * day[i];
            count += eatenCandies[i];
        }
        return total / count;
    }
}
