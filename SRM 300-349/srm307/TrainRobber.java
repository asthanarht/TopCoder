package srm307;

import java.util.PriorityQueue;

public class TrainRobber {
    public static void main(String[] args) throws InterruptedException {
        String[] offset = { "3 4" };
        String[] period = { "4", "2" };
        double result = finalPosition(1, 4, offset, period, 1, 1, 100);
        System.out.println(result);
    }

    public static double finalPosition(int length, int nCarriages,
            String[] offset, String[] period, int trainSpeed, int robberSpeed,
            int nBridges) throws InterruptedException {
        String of = offset[0];
        for (int i = 1; i < offset.length; i++)
            of += " " + offset[i];
        String[] ofs = of.split(" ");
        String pe = period[0];
        for (int i = 1; i < period.length; i++)
            pe += " " + period[i];
        String[] pes = pe.split(" ");
        PriorityQueue<Sequence> briges = new PriorityQueue<Sequence>();
        for (int i = 0; i < ofs.length; i++)
            briges.add(new Sequence(Integer.parseInt(ofs[i]), Integer
                    .parseInt(pes[i])));
        long start = 0, end = 0, pos = 0;
        double speed = trainSpeed + robberSpeed;
        while (true) {
            Thread.sleep(200);
            end = briges.peek().cur;
            // System.out.println();
            // System.out.println("start:" + start);
            // System.out.println("end:" + end);
            int max = (int) ((end - start) * robberSpeed / (speed * length));
            // System.out.println("max:" + max);
            if (pos + max >= nCarriages) {
                double time = (nCarriages - pos) * 1.0 * length / robberSpeed;
                return start + time * speed;
            }
            else {
                pos += max;
                Sequence s = briges.poll();
                while (s.cur - end <= 1) {
                    nBridges--;
                    if (nBridges == 0)
                        return s.cur;
                    end = s.cur;
                    s.next();
                    briges.add(s);
                    s = briges.poll();
                }
                start = end;
                briges.add(s);
            }
        }
    }
}

class Sequence implements Comparable<Sequence> {
    public long cur;
    public long period;

    public Sequence(long offset, long period) {
        super();
        this.cur = offset;
        this.period = period;
    }

    public void next() {
        cur += period;
    }

    public int compareTo(Sequence s) {
        if (this.cur == s.cur)
            return 0;
        return this.cur > s.cur ? 1 : -1;
    }
}
