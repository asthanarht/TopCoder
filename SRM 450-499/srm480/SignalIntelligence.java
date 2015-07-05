import java.util.Arrays;

public class SignalIntelligence {
    public long encrypt(int[] numbers) {
        Arrays.sort(numbers);
        int n = numbers.length;
        long min = Long.MAX_VALUE;
        for (int last = 0; last < n; last++) {
            long p = 1;
            for (int i = 0; i < n; i++)
                if (i != last) {
                    long end = p + numbers[i];
                    while (p <= end)
                        p *= 2;
                }
            min = Math.min(min, p + numbers[last] - 1);
        }
        return min;
    }
}
