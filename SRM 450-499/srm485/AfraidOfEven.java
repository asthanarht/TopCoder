package srm485;

public class AfraidOfEven {
    public int[] restoreProgression(int[] seq) {
        for (long seq0 = seq[0]; seq0 < Integer.MAX_VALUE; seq0 *= 2)
            for (long seq1 = seq[1]; seq1 < Integer.MAX_VALUE; seq1 *= 2) {
                long progress = seq1 - seq0;
                long expect = seq1 + progress;
                boolean found = true;
                for (int i = 2; i < seq.length; i++, expect += progress) {
                    if (expect > Integer.MAX_VALUE) {
                        found = false;
                        break;
                    }
                    long checki = seq[i];
                    while (checki < expect)
                        checki *= 2;
                    if (checki != expect) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    seq[0] = (int) seq0;
                    for (int i = 1; i < seq.length; i++)
                        seq[i] = (int) (seq[i - 1] + progress);
                    return seq;
                }
            }
        return new int[seq.length];
    }
}
