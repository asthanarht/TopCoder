import java.util.Arrays;

public class LittleElephantAndIntervalsDiv1 {
    public long getNumber(int M, int[] L, int[] R) {
        for (int i = 0; i < L.length; L[i]--, R[i]--, i++)
            ;

        int[] balls = new int[M];
        Arrays.fill(balls, -1);

        for (int i = 0; i < L.length; i++)
            for (int j = L[i]; j <= R[i]; j++)
                balls[j] = i;

        long res = 1;

        for (int i = 0; i < L.length; i++) {
            boolean found = false;
            for (int j = 0; j < M & !found; j++)
                if (balls[j] == i)
                    found = true;
            res *= (found ? 2 : 1);
        }

        return res;
    }
}
