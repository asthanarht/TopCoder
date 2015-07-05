import java.util.PriorityQueue;

public class ColorfulChocolates {
    public int maximumSpread(String chocolates, int maxSwaps) {
        int max = 1;
        int n = chocolates.length();
        char[] ch = chocolates.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = ch[i];
            PriorityQueue<Integer> candidates = new PriorityQueue<Integer>();
            for (int j = i - 1, to = i - 1; j >= 0; j--)
                if (ch[j] == c) {
                    candidates.add(to - j);
                    to--;
                }
            for (int j = i + 1, to = i + 1; j < n; j++)
                if (ch[j] == c) {
                    candidates.add(j - to);
                    to++;
                }
            int count = 1;
            int move = maxSwaps;
            while (candidates.size() > 0) {
                int candidate = candidates.poll();
                if (candidate > move)
                    break;
                count++;
                move -= candidate;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
