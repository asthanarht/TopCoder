import java.util.Arrays;

public class AntsMeet {
    public int countAnts(int[] x, int[] y, String direction) {
        int n = x.length;
        char[] dir = direction.toCharArray();
        boolean[] dis = new boolean[n];
        Arrays.fill(dis, true);
        for (int i = 0; i < n; i++) {
            x[i] *= 2;
            y[i] *= 2;
        }
        for (int t = 0; t <= 4000; t++) {
            for (int i = 0; i < n; i++)
                if (dis[i]) {
                    if (dir[i] == 'E')
                        x[i]++;
                    else if (dir[i] == 'W')
                        x[i]--;
                    else if (dir[i] == 'S')
                        y[i]--;
                    else
                        y[i]++;
                }
            for (int i = 0; i < n; i++)
                if (dis[i])
                    for (int j = i + 1; j < n; j++)
                        if (dis[j] && x[i] == x[j] && y[i] == y[j]) {
                            dis[i] = false;
                            dis[j] = false;
                        }
        }
        int count = 0;
        for (int i = 0; i < n; i++)
            if (dis[i])
                count++;
        return count;
    }
}
