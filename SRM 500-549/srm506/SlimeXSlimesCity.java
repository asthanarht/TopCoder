package srm506;

import java.util.Arrays;

public class SlimeXSlimesCity {

    public int merge(int[] population) {
        int count = 0;
        Arrays.sort(population);
        int n = population.length;
        boolean[] b = new boolean[n];
        b[n - 1] = true;

        for (int i = n - 2; i >= 0; i--) {
            long sum = 0;
            for (int j = 0; j <= i; j++)
                sum += population[j];
            for (int j = i + 1; j < n; j++) {
                if (b[j] == true) {
                    if (sum >= population[j]) {
                        b[i] = true;
                        break;
                    }
                    else {
                        b[i] = false;
                        break;
                    }
                }
                if (b[j] == false) {
                    if (sum >= population[j]) {
                        sum += population[j];
                        continue;
                    }
                    else {
                        b[i] = false;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
            if (b[i] == true)
                count++;

        return count;
    }
}
