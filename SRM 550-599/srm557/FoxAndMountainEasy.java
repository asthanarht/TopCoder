public class FoxAndMountainEasy {
    public String possible(int n, int h0, int hn, String history) {
        int loc = h0, min = 0;
        for (int i = 0; i < history.length(); i++, min = Math.min(min, loc))
            if (history.charAt(i) == 'U')
                loc++;
            else
                loc--;
        int trip = hn - loc;
        int len = Math.abs(trip);
        int steps = n - history.length();
        if (len > steps || (steps - len) % 2 == 1)
            return "NO";
        int up = (steps - len) / 2;
        up += (trip > 0 ? trip : 0);
        if (min < 0 && up + min < 0)
            return "NO";
        return "YES";
    }
}
