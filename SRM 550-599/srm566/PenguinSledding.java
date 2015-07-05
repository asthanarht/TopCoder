import java.math.BigInteger;

public class PenguinSledding {
    public long countDesigns(int numCheckpoints, int[] checkpoint1,
            int[] checkpoint2) {
        // no edge + only a single edge
        long result = 1 + checkpoint1.length;
        // sun light shape for each point
        int[] count = new int[numCheckpoints + 1];
        for (int i = 0; i < checkpoint1.length; i++) {
            count[checkpoint1[i]]++;
            count[checkpoint2[i]]++;
        }
        for (int i = 1; i <= numCheckpoints; i++)
            for (int j = count[i]; j >= 2; j--)
                result += c(count[i], j);
        // triangle
        boolean[][] graph = new boolean[numCheckpoints + 1][numCheckpoints + 1];
        for (int i = 0; i < checkpoint1.length; i++) {
            graph[checkpoint1[i]][checkpoint2[i]] = true;
            graph[checkpoint2[i]][checkpoint1[i]] = true;
        }
        for (int i = 1; i < numCheckpoints; i++)
            for (int j = i + 1; j < numCheckpoints; j++)
                for (int k = j + 1; k <= numCheckpoints; k++)
                    if (graph[i][j] && graph[j][k] && graph[k][i])
                        result++;
        return result;
    }

    private long c(int n, int k) {
        BigInteger result = BigInteger.ONE;
        for (int i = 0; i < k; i++)
            result = result.multiply(new BigInteger(String.valueOf(n - i)));
        for (int i = k; i > 1; i--)
            result = result.divide(new BigInteger(String.valueOf(i)));
        return result.longValue();
    }
}
