public class BusTrip {
    public static int returnTime(int N, String[] buses) {
        int n = buses.length;
        Stop[] bus = new Stop[n];
        for (int i = 0; i < n; i++) {
            bus[i] = new Stop();
            Stop cur = bus[i];
            String[] info = buses[i].split(" ");
            for (int j = 0; j < info.length; j++) {
                cur.number = Integer.parseInt(info[j]);
                if (j != info.length - 1) {
                    cur.next = new Stop();
                    cur = cur.next;
                }
                else
                    cur.next = bus[i];
            }
        }
        Stop[] loc = new Stop[n];
        for (int i = 0; i < n; i++)
            loc[i] = bus[i];
        int[] next = new int[n];
        int location = 0, on = -1;
        boolean ok = true;
        for (int t = 0; t <= 1000; t++) {
            int not = -1;
            boolean b = true;
            for (int i = 0; i < n; i++)
                if (t - next[i] == Math.abs(loc[i].next.number - loc[i].number)) {
                    loc[i] = loc[i].next;
                    next[i] = t;
                    if (on == i) {
                        ok = true;
                        on = -1;
                        location = loc[i].number;
                        if (location == 0)
                            return t;
                        not = i;
                        b = false;
                    }
                }
            if (ok && b)
                for (int i = 0; i < n; i++)
                    if (i != not && next[i] == t && loc[i].number == location) {
                        ok = false;
                        on = i;
                        break;
                    }
        }
        return -1;
    }
}

class Stop {
    int number;
    Stop next;
}