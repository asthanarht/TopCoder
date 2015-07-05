import java.util.LinkedList;

public class BridgeCrossing {

    private int[] ts;
    private LinkedList<Node> frontier = new LinkedList<Node>();
    private Node bn = new Node();
    private int n;

    public int minTime(int[] time) {
        n = time.length;
        ts = time.clone();
        Node node = new Node();
        node.time = 0;
        for (int i = 0; i < n; i++)
            node.original[i] = true;
        frontier.add(node);

        if (n == 1)
            return time[0];

        while (frontier.size() > 0)
            go();

        return bn.time;
    }

    private void go() {
        Node node = frontier.poll();
        if (node.isFinish()) {
            if (node.time < bn.time)
                bn = node;
        }
        else {
            if (node.isGo()) {
                for (int i = 0; i < 5; i++)
                    for (int j = i + 1; j < 6; j++)
                        if (i != j && node.original[i] && node.original[j]) {
                            Node no = node.go(i, j, Math.max(ts[i], ts[j]));
                            frontier.add(no);
                        }
            }
            else {
                int min = -1;
                int minn = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++)
                    if (!node.original[i] && ts[i] < minn) {
                        min = i;
                        minn = ts[i];
                    }
                Node no = node.back(min, minn);
                frontier.add(no);
            }
        }
    }
}

class Node {
    int time = Integer.MAX_VALUE;
    boolean[] original = new boolean[6];
    boolean go = true;

    public boolean isGo() {
        return go;
    }

    public Node go(int i1, int i2, int cost) {
        Node node = new Node();
        node.time = this.time + cost;
        node.original = this.original.clone();
        node.original[i1] = false;
        node.original[i2] = false;
        node.go = false;
        return node;
    }

    public Node back(int i, int cost) {
        Node node = new Node();
        node.time = this.time + cost;
        node.original = this.original.clone();
        node.original[i] = true;
        node.go = true;
        return node;
    }

    public boolean isFinish() {
        for (int i = 0; i < 6; i++)
            if (original[i])
                return false;
        return true;
    }
}