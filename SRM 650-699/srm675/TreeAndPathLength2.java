public class TreeAndPathLength2 {

    public String possible(int n, int s) {
        return dfs(n - 2, 0, s) ? "Possible" : "Impossible";
    }

    private boolean dfs(int n, int cur, int s) {
        if (cur == s)
            return true;
        if (cur + n > s)
            return false;
        if (cur + n * (n + 1) / 2 < s)
            return false;
        for (int u = 1; u <= n; u++)
            if (dfs(n - u, cur + u * (u + 1) / 2, s))
                return true;
        return false;
    }

}
