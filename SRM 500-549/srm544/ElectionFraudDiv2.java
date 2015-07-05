public class ElectionFraudDiv2 {
    public String IsFraudulent(int[] percentages) {
        int n = percentages.length;
        int[] min = new int[n], max = new int[n];
        for (int i = 0; i < n; i++) {
            min[i] = Math.max(0, 100 * percentages[i] - 50);
            max[i] = 100 * percentages[i] + 49;
        }
        int summin = 0, summax = 0;
        for (int i = 0; i < n; i++) {
            summin += min[i];
            summax += max[i];
        }
        if (summin <= 10000 && summax >= 10000)
            return "NO";
        return "YES";
    }
}
