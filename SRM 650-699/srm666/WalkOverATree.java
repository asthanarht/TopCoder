public class WalkOverATree {

    private int n;
    private int depth = 0;

    public int maxNodesVisited(int[] parent, int L) {
        n = parent.length;
        depth(parent, 0, 0);
        if (L <= depth)
            return 1 + L;
        else
            return Math.min((depth + 1) + (L - depth) / 2, n + 1);
    }

    private void depth(int[] parent, int pid, int d) {
        depth = Math.max(depth, d);
        for (int i = 0; i < n; i++)
            if (parent[i] == pid)
                depth(parent, i + 1, d + 1);
    }

}
