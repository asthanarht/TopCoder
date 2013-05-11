package srm482;

import java.util.ArrayList;

public class LockersDivOne {
    public int lastOpened(int N) {
        if (N == 1)
            return N;
        ArrayList<Integer> queue = new ArrayList<Integer>();
        for (int i = 2; i <= N; i += 2)
            queue.add(i);
        int n = 2;
        while (queue.size() > 1 && queue.size() > n) {
            n++;
            ArrayList<Integer> next = new ArrayList<Integer>();
            for (int i = 0; i < queue.size(); i++)
                if (i % n != 0)
                    next.add(queue.get(i));
            queue = next;
        }
        return queue.get(queue.size() - 1);
    }
}