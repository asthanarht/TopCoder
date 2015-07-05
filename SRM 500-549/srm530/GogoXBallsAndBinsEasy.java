import java.util.Arrays;

public class GogoXBallsAndBinsEasy {
    public int solve(int[] T) {
        int total = 0;
        Arrays.sort(T);
        for (int i = 0; i < T.length / 2; i++)
            total += T[T.length - 1 - i] - T[i];
        return total;
    }
}
