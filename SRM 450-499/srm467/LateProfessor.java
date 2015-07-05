public class LateProfessor {
    public double getProbability(int waitTime, int walkTime, int lateTime,
            int bestArrival, int worstArrival) {
        int total = 0;
        int len = walkTime - lateTime;
        if (len <= 0)
            return 0;
        int start = waitTime;
        int period = waitTime + walkTime;
        if (bestArrival == worstArrival) {
            bestArrival %= period;
            if (bestArrival > waitTime && bestArrival + lateTime <= period)
                return 1;
            return 0;
        }
        while (start + len <= bestArrival)
            start += period;
        if (start < bestArrival) {
            total += Math.min(worstArrival, start + len) - bestArrival;
            start += period;
        }
        while (start + len <= worstArrival) {
            total += len;
            start += period;
        }
        if (worstArrival > start)
            total += worstArrival - start;
        System.out.println(total);
        return (double) total / (double) (worstArrival - bestArrival);
    }
}
