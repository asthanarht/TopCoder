public class DevuAndGame {

    public String canWin(int[] nextLevel) {
        int n = nextLevel.length;
        boolean[] visited = new boolean[n];
        int cur = 0;
        while (cur != -1) {
            if (visited[cur])
                return "Lose";
            visited[cur] = true;
            cur = nextLevel[cur];
        }
        return "Win";
    }

}
