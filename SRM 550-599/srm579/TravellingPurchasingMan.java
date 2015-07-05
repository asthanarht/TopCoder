import java.util.Arrays;

public class TravellingPurchasingMan {
    public int maxStores(int N, String[] interestingStores, String[] roads) {
        int INF = 100000000;

        int M = interestingStores.length;
        int[] opens = new int[M];
        int[] closes = new int[M];
        int[] durations = new int[M];
        for (int i = 0; i < M; i++) {
            String[] info = interestingStores[i].split(" ");
            opens[i] = Integer.parseInt(info[0]);
            closes[i] = Integer.parseInt(info[1]);
            durations[i] = Integer.parseInt(info[2]);
        }

        int[][] graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        for (int i = 0; i < roads.length; i++) {
            String[] info = roads[i].split(" ");
            int s1 = Integer.parseInt(info[0]);
            int s2 = Integer.parseInt(info[1]);
            int length = Integer.parseInt(info[2]);
            graph[s1][s2] = length;
            graph[s2][s1] = length;
        }
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    graph[i][j] = Math.min(graph[i][j], graph[i][k]
                            + graph[k][j]);

        int result = 0;
        int[][] dp = new int[1 << M][M];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], INF);
        dp[0][0] = 0;
        for (int visited = 0; visited < (1 << M); visited++) {
            int count = Integer.bitCount(visited);
            for (int last = 0; last < M; last++) {
                int tFinish = dp[visited][last];
                if (tFinish >= INF)
                    continue;
                result = Math.max(result, count);
                int cur = (visited == 0 ? N - 1 : last);
                for (int next = 0; next < M; next++)
                    if ((visited & (1 << next)) == 0) {
                        int tBuy = tFinish + graph[cur][next];
                        if (tBuy > closes[next])
                            continue;
                        tBuy = Math.max(tBuy, opens[next]) + durations[next];
                        int visited2 = (visited ^ (1 << next));
                        dp[visited2][next] = Math.min(dp[visited2][next], tBuy);
                    }
            }
        }
        return result;
    }
}
