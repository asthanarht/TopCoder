public class ImportantSequence {
    private static int[] bs;
    private static char[] ops;
    private static int n;

    public int getCount(int[] B, String operators) {
        n = B.length + 1;
        bs = B;
        ops = operators.toCharArray();
        long start = -1 + (1 - check(-1));
        if (check(start) < 1)
            return 0;
        long left = start, right = 100000000000L;
        if (check(right) >= 1)
            return -1;
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            if (check(mid) >= 1)
                left = mid;
            else
                right = mid;
        }
        return (int) (left - start + 1);
    }

    private long check(long first) {
        long[] nums = new long[n];
        nums[0] = first;
        for (int i = 0; i < n - 1; i++) {
            if (ops[i] == '-')
                nums[i + 1] = nums[i] - bs[i];
            else
                nums[i + 1] = bs[i] - nums[i];
        }
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++)
            min = Math.min(min, nums[i]);
        return min;
    }
}
