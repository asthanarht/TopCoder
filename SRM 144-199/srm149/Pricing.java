package srm149;

import java.util.Arrays;

public class Pricing {
    public int maxSales(int[] price) {
        int n = price.length;
        int max = 0;
        Arrays.sort(price);
        for (int g1 = 0; g1 < n; g1++)
            for (int g2 = g1; g2 < n; g2++)
                for (int g3 = g2; g3 < n; g3++)
                    for (int g4 = g3; g4 < n; g4++) {
                        int total = 0;
                        total += price[g1] * (g2 - g1);
                        total += price[g2] * (g3 - g2);
                        total += price[g3] * (g4 - g3);
                        total += price[g4] * (n - g4);
                        if (total > max)
                            max = total;
                    }
        return max;
    }
}
