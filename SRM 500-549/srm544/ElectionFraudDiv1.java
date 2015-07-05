public class ElectionFraudDiv1 {
    public int MinimumVoters(int[] percentages) {
        long start = System.currentTimeMillis();
        for (int total = 1; total < Integer.MAX_VALUE; total++) {
            long end = System.currentTimeMillis();
            if (end - start >= 1800)
                break;
            if (check(percentages, total))
                return total;
        }
        return -1;
    }

    public boolean check(int[] percentages, int total) {
        int n = percentages.length;
        int[] min = new int[n], max = new int[n];
        for (int i = 0; i < n; i++) {
            min[i] = Math.max(0,
                    (int) (total * (percentages[i] - 0.5) * 0.01) - 1);
            while (Math.round(100.0 * min[i] / total) < percentages[i])
                min[i]++;
            max[i] = (int) (total * (percentages[i] + 0.5) * 0.01) + 1;
            while (Math.round(100.0 * max[i] / total) > percentages[i])
                max[i]--;
        }
        int summin = 0, summax = 0;
        for (int i = 0; i < n; i++) {
            summin += min[i];
            summax += max[i];
        }
        if (summin <= total && summax >= total)
            return true;
        return false;
    }
}
