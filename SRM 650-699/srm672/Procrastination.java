import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Procrastination {

    public long findFinalAssignee(long n) {
        HashMap<Long, ArrayList<Long>> map = new HashMap<Long, ArrayList<Long>>();
        ArrayList<Long> fs;
        long hour = 2;
        while (true) {
            long h1 = -1, h2 = -1;
            if (!map.containsKey(n))
                map.put(n, factor(n));
            fs = map.get(n);
            for (int i = 0; i < fs.size(); i++)
                if (fs.get(i) >= hour) {
                    h1 = fs.get(i);
                    break;
                }
            if (!map.containsKey(n - 1))
                map.put(n - 1, factor(n - 1));
            fs = map.get(n - 1);
            for (int i = 0; i < fs.size(); i++)
                if (fs.get(i) >= hour) {
                    h2 = fs.get(i);
                    break;
                }
            if (h1 == -1 && h2 == -1)
                break;
            if (h1 != -1) {
                if (h2 == -1 || h2 > h1) {
                    hour = h1 + 1;
                    n++;
                }
            }
            if (h2 != -1) {
                if (h1 == -1 || h1 > h2) {
                    hour = h2 + 1;
                    n--;
                }
            }
        }
        return n;
    }

    private ArrayList<Long> factor(long n) {
        ArrayList<Long> f = new ArrayList<Long>();
        HashSet<Long> set = new HashSet<Long>();
        for (long a = 2; a * a <= n; a++)
            if (n % a == 0) {
                long b = n / a;
                if (!set.contains(a)) {
                    set.add(a);
                    f.add(a);
                }
                if (!set.contains(b)) {
                    set.add(b);
                    f.add(b);
                }
            }
        Collections.sort(f);
        return f;
    }

}
