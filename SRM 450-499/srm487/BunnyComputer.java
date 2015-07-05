public class BunnyComputer {
    public int getMaximum(int[] preference, int k) {
        int max = 0, n = preference.length;
        for (int i = 0; i <= k; i++) {
            int m = (n - i + k) / (k + 1);
            int[] nums = new int[m];
            for (int index = i, j = 0; index < n && j < m; index += (k + 1), j++)
                nums[j] = preference[index];
            int sum = 0, min = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                sum += nums[j];
                if (j % 2 == 0)
                    min = Math.min(min, nums[j]);
            }
            if (m % 2 == 1)
                sum -= min;
            max += sum;
        }
        return max;
    }
}
