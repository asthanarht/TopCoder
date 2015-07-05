import java.util.HashMap;

public class RouteIntersection {
    public String isValid(int N, int[] coords, String moves) {
        HashMap<Integer, Integer> coordI = new HashMap<Integer, Integer>();
        int n = coords.length;
        for (int i = 0; i < n; i++)
            if (!coordI.containsKey(coords[i]))
                coordI.put(coords[i], coordI.size());
        int m = coordI.size();
        int[][] points = new int[n + 1][m];
        points[1][coordI.get(coords[0])] += (moves.charAt(0) == '+' ? 1 : -1);
        for (int i = 1; i < n; i++) {
            for (int k = 0; k < m; k++)
                points[i + 1][k] = points[i][k];
            points[i + 1][coordI.get(coords[i])] += (moves.charAt(i) == '+'
                    ? 1 : -1);
            for (int j = 0; j < i; j++) {
                boolean ok = false;
                for (int k = 0; k < m; k++)
                    if (points[i + 1][k] != points[j][k])
                        ok = true;
                if (!ok)
                    return "NOT VALID";
            }
        }
        return "VALID";
    }
}
