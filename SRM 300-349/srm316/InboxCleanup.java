public class InboxCleanup {
    public int fewestClicks(String messages, int low, int high) {
        int n = messages.length();
        int min = Integer.MAX_VALUE;
        for (int num = low; num <= high; num++) {
            int total = 0;
            boolean newPage = false;
            for (int start = 0; start < n; start += num) {
                if (newPage)
                    total++;
                int d = 0;
                int message = 0;
                for (int index = start; index < start + num && index < n; index++) {
                    message++;
                    if (messages.charAt(index) == 'D')
                        d++;
                }
                if (d == message)
                    total += 2;
                else if (d > 0)
                    total += Math.min(d + 1, message - d + 2);
                newPage = true;
            }
            min = Math.min(min, total);
        }
        return min;
    }
}