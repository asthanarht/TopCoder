import java.util.Arrays;
import java.util.HashMap;

public class BifidSortMachine {
    public int countMoves(int[] a) {
        int n = a.length;
        // construct look up table of target index for each element
        int[] copy = a.clone();
        Arrays.sort(copy);
        HashMap<Integer, Integer> index = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++)
            index.put(copy[i], i);
        // looking for longest sequence with contiguous target index
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++)
                if (index.get(a[j]) + 1 == index.get(a[i]))
                    dp[i] = Math.max(dp[i], dp[j] + 1);
        }
        // result
        int result = n - 1;
        for (int i = 0; i < n; i++)
            result = Math.min(result, n - dp[i]);
        return result;
    }
}
