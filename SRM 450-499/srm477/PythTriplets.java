import java.util.Arrays;

public class PythTriplets {
    private int n;
    private long[] length;
    private boolean[][] match;
    private boolean[] visited;
    private int[] pair;

    public int findMax(String[] stick) {
        String input = "";
        for (int i = 0; i < stick.length; i++)
            input += stick[i];
        String[] info = input.split(" ");
        n = info.length;
        length = new long[n];
        for (int i = 0; i < n; i++)
            length[i] = Integer.parseInt(info[i]);
        match = new boolean[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (length[i] % 2 == 0 || length[j] % 2 == 1)
                    continue;
                if (gcd(length[i], length[j]) != 1)
                    continue;
                long num = length[i] * length[i] + length[j] * length[j];
                if ((long) Math.sqrt(num) * (long) Math.sqrt(num) == num)
                    match[i][j] = true;
            }

        int result = 0;
        visited = new boolean[n];
        pair = new int[n];
        Arrays.fill(pair, -1);
        for (int ai = 0; ai < n; ai++) {
            Arrays.fill(visited, false);
            if (find(ai))
                result++;
        }
        return result;
    }

    private boolean find(int ai) {
        for (int bi = 0; bi < n; bi++)
            if (match[ai][bi] && !visited[bi]) {
                visited[bi] = true;
                if (pair[bi] == -1 || find(pair[bi])) {
                    pair[bi] = ai;
                    return true;
                }
            }
        return false;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}
