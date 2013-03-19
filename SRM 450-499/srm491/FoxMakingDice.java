package srm491;

public class FoxMakingDice {
    public long theCount(int N, int K) {
        long result = 0;
        for (int sum = K; sum <= 2 * N; sum++) {
            long pair = 0;
            for (int i = 1; i <= N; i++) {
                int other = sum - i;
                if (i < other && other <= N)
                    pair++;
            }
            result += pair * (pair - 1) * (pair - 2) / 3;
        }
        return result;
    }
}
