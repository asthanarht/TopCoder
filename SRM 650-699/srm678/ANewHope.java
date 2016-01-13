public class ANewHope {
    // I want the old look of TC back
    public int count(int[] firstWeek, int[] lastWeek, int D) {
        int n = firstWeek.length;
        int[] o1 = new int[n + 1];
        for (int i = 0; i < n; i++)
            o1[firstWeek[i]] = i;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sid = lastWeek[i];
            int loc = o1[sid];
            int cnt = 1;
            while (n - 1 - loc + i + 1 <= D) {
                loc = (loc + D) % n;
                cnt++;
            }
            if (loc > i)
                cnt++;
            max = Math.max(max, cnt);
        }
        return max;
    }
}
