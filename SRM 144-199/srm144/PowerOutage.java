package srm144;

import java.util.HashMap;
import java.util.HashSet;

public class PowerOutage {

    public int estimateTimeOut(int[] fromJunction, int[] toJunction,
            int[] ductLength) {

        int n = fromJunction.length;
        Node[] nodes = new Node[50];
        int total = 0;
        int high = 0;

        for (int i = 0; i < 50; i++) {
            nodes[i] = new Node();
            nodes[i].num = i;
        }

        for (int i = 0; i < n; i++) {
            int index_from = fromJunction[i];
            int index_to = toJunction[i];
            int cost = ductLength[i];
            nodes[index_from].tos.add(index_to);
            nodes[index_from].costs.put(index_to, cost);
        }

        for (int i = 0; i < n; i++) {
            total += ductLength[i];
        }
        total *= 2;
        high = nodes[0].getHighest(nodes);
        total -= high;
        return total;

    }

}

class Node {
    public int num = 0;
    public HashSet<Integer> tos = new HashSet<Integer>();
    public HashMap<Integer, Integer> costs = new HashMap<Integer, Integer>();

    public int getHighest(Node[] nodes) {
        int max = 0;
        for (Integer index : tos) {
            Node node = nodes[index];
            int subcost = costs.get(index) + node.getHighest(nodes);
            if (subcost > max)
                max = subcost;
        }
        return max;
    }
}