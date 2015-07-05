import java.util.Arrays;
import java.util.HashSet;

public class StringGame {
    private static int[][] count;
    private static int N;
    private static HashSet<Integer> set = new HashSet<Integer>();

    public static int[] getWinningStrings(String[] S) {
        N = S.length;
        count = new int[N][26];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < S[i].length(); j++)
                count[i][S[i].charAt(j) - 'a']++;
        for (int i = 0; i < N; i++)
            if (check(i))
                set.add(i);
        int[] result = new int[set.size()];
        int i = 0;
        for (Integer index : set)
            result[i++] = index;
        Arrays.sort(result);
        return result;
    }

    private static boolean check(int index) {
        boolean[] alive = new boolean[N];
        Arrays.fill(alive, true);
        alive[index] = false;
        boolean update = false;
        do {
            update = false;
            alpha: for (int j = 0; j < 26; j++) {
                for (int i = 0; i < N; i++)
                    if (alive[i] && count[i][j] > count[index][j])
                        continue alpha;
                for (int i = 0; i < N; i++)
                    if (alive[i] && count[i][j] < count[index][j]) {
                        alive[i] = false;
                        update = true;
                    }
            }
        } while (update);
        for (int i = 0; i < N; i++)
            if (alive[i])
                return false;
        return true;
    }
}
