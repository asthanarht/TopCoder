package srm153;

public class Inventory {
    public int monthlyOrder(int[] sales, int[] daysAvailable) {
        int n = sales.length;
        int count = 0;
        double sum = 0;
        for (int i = 0; i < n; i++)
            if (daysAvailable[i] == 0)
                continue;
            else {
                count++;
                sum += sales[i] * 30D / daysAvailable[i];
            }
        sum /= count;
        double num = Double.parseDouble(String.format("%f", sum));
        int result = (int) num;
        if (result < num)
            result++;
        return result;
    }
}
