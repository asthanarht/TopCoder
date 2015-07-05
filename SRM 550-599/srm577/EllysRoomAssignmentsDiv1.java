import java.util.Arrays;

public class EllysRoomAssignmentsDiv1 {
    public double getAverage(String[] ratings) {
        String rating = "";
        for (int i = 0; i < ratings.length; i++)
            rating += ratings[i];
        String[] info = rating.split(" ");
        int n = info.length;
        int[] rates = new int[n];
        for (int i = 0; i < n; i++)
            rates[i] = Integer.parseInt(info[i]);

        int elly = rates[0];
        int room = n / 20 + (n % 20 == 0 ? 0 : 1);
        int maxPeople = (n + room - 1) / room;

        Arrays.sort(rates);
        int[] rate = new int[n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            rate[i] = rates[n - 1 - i];
            if (rate[i] == elly)
                index = i;
        }

        double[] posTotal = new double[maxPeople];
        int[] posCount = new int[maxPeople];
        int ellyPos = index / room;
        posTotal[ellyPos] = elly;
        posCount[ellyPos] = 1;
        for (int i = 0; i < n; i++)
            if (i / room != ellyPos) {
                posTotal[i / room] += rate[i];
                posCount[i / room]++;
            }

        if (ellyPos == maxPeople - 1) {
            double result = 0;
            for (int i = 0; i < maxPeople; i++)
                result += posTotal[i] / posCount[i];
            return result / maxPeople;
        }
        else {
            double p1 = n % room;
            if (p1 == 0)
                p1 = 1;
            else
                p1 /= room;
            double p0 = 1 - p1;
            double result = 0;
            for (int i = 0; i < maxPeople - 1; i++) {
                double expect = posTotal[i] / posCount[i];
                result += expect / maxPeople * p1 + expect / (maxPeople - 1)
                        * p0;
            }
            result += posTotal[maxPeople - 1] / posCount[maxPeople - 1]
                    / maxPeople * p1;
            return result;
        }
    }
}
