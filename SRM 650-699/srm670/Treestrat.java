import java.util.ArrayList;
import java.util.Arrays;

public class Treestrat {

    @SuppressWarnings("unchecked")
    public int roundcnt(int[] tree, int[] A, int[] B) {
        n = tree.length + 1;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<Integer>();
        for (int i = 0; i < n - 1; i++) {
            graph[i + 1].add(tree[i]);
            graph[tree[i]].add(i + 1);
        }
        bs = new boolean[n];
        db = new int[n];
        Arrays.fill(db, -1);
        ArrayList<Integer> queue = new ArrayList<Integer>();
        for (Integer i : B) {
            bs[i] = true;
            db[i] = 0;
            queue.add(i);
        }
        while (!queue.isEmpty()) {
            int node = queue.remove(0);
            for (Integer adj : graph[node])
                if (db[adj] == -1) {
                    db[adj] = db[node] + 1;
                    queue.add(adj);
                }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++)
            min = Math.min(min, go(A[i]));
        return min;
    }

    int n;
    ArrayList<Integer>[] graph;
    boolean[] bs;
    int[] db;

    private int go(int r) {
        int[] da = new int[n];
        Arrays.fill(da, -1);
        da[r] = 0;
        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(r);
        while (!queue.isEmpty()) {
            int node = queue.remove(0);
            if (da[node] >= db[node])
                continue;
            for (Integer adj : graph[node])
                if (!bs[adj] && da[adj] == -1) {
                    da[adj] = da[node] + 1;
                    queue.add(adj);
                }
        }
        int max = 0;
        for (int i = 0; i < n; i++)
            if (da[i] != -1 && db[i] >= da[i])
                max = Math.max(max, db[i]);
        return max;
    }

}
