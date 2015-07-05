import java.util.Arrays;

public class EllysFractions {
    public long getCount(int N) {
        boolean[] prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        for (int i = 2; i <= N; i++)
            if (prime[i])
                for (int j = i + i; j <= N; j += i)
                    prime[j] = false;
        long result = 0;
        for (int i = 2; i <= N; i++) {
            int count = 0;
            for (int j = 3; j <= i; j++)
                count += prime[j] ? 1 : 0;
            result += 1L << count;
        }
        return result;
    }
}
