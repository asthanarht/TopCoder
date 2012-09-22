package srm150;

public class WidgetRepairs {
    public int days(int[] arrivals, int numPerDay) {
        int count = 0;
        int pool = 0;
        int n = arrivals.length;
        for (int i = 0; i < n || pool > 0; i++) {
            if (i < n)
                pool += arrivals[i];
            if (pool > 0) {
                count++;
                pool = (pool >= numPerDay ? pool - numPerDay : 0);
            }
        }
        return count;
    }
}
