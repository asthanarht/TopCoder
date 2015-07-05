public class MultiRead {
    public int minCycles(String trace, int procs) {
        int count = 0;
        for (int i = 0; i < trace.length(); i++)
            if (trace.charAt(i) == 'W')
                count++;
            else {
                boolean done = false;
                for (int j = i + 1; j < trace.length(); j++)
                    if (trace.charAt(j) == 'W') {
                        count += (j - i + procs - 1) / procs;
                        i = j - 1;
                        done = true;
                        break;
                    }
                if (!done) {
                    count += (trace.length() - i + procs - 1) / procs;
                    break;
                }
            }
        return count;
    }
}
