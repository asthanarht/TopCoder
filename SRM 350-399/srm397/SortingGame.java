import java.util.ArrayList;
import java.util.HashSet;

public class SortingGame {
    private static HashSet<String> visited = new HashSet<String>();
    private static ArrayList<String> frontier = new ArrayList<String>();

    public static int fewestMoves(int[] board, int k) {
        String boards = "";
        for (int i = 0; i < board.length; i++)
            boards += board[i];
        frontier.add(boards);
        visited.add(boards);
        int count = 0;
        while (frontier.size() > 0) {
            if (iter(count, k) != Integer.MAX_VALUE)
                return count;
            count++;
        }
        return -1;
    }

    private static int iter(int move, int k) {
        ArrayList<String> childs = new ArrayList<String>();
        for (int i = 0; i < frontier.size(); i++) {
            String s = frontier.get(i);
            if (isOK(s))
                return move;
            for (int j = 0; j <= s.length() - k; j++) {
                String child = shift(s, j, k);
                if (!visited.contains(child)) {
                    childs.add(child);
                    visited.add(child);
                }
            }
        }
        frontier = childs;
        return Integer.MAX_VALUE;
    }

    private static String shift(String s, int index, int k) {
        String s1 = s.substring(0, index);
        String s2 = s.substring(index + k);
        String work = s.substring(index, index + k);
        String to = "";
        char[] chars = work.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--)
            to += chars[i];
        return s1 + to + s2;
    }

    private static boolean isOK(String s) {
        for (int i = 0; i < s.length() - 1; i++)
            if (s.charAt(i) > s.charAt(i + 1))
                return false;
        return true;
    }
}