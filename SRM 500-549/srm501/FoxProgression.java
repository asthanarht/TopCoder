package srm501;

public class FoxProgression {

    public int theCount(int[] seq) {

        int plus;
        int multi = 0;

        boolean arith = true;
        boolean geo = true;

        if (seq.length == 1)
            return -1;

        plus = seq[1] - seq[0];
        if (seq[0] == 0) {
            for (int i = 1; i < seq.length; i++)
                if (seq[i] != 0) {
                    geo = false;
                    break;
                }
            if (geo)
                return -1;
        }
        else {
            if (seq[1] % seq[0] != 0)
                geo = false;
            else
                multi = seq[1] / seq[0];
        }

        for (int i = 2; i < seq.length; i++)
            if (seq[i] - seq[i - 1] != plus) {
                arith = false;
                break;
            }

        if (geo && seq[0] != 0)
            for (int i = 2; i < seq.length; i++) {
                if (seq[i - 1] != 0) {
                    if (seq[i] % seq[i - 1] != 0
                            || seq[i] / seq[i - 1] != multi) {
                        geo = false;
                        break;
                    }
                }
                else {
                    geo = false;
                    break;
                }
            }

        int count = 0;
        if (arith)
            count++;
        if (geo)
            count++;
        if (arith && geo) {
            int num1 = seq[seq.length - 1] + plus;
            int num2 = seq[seq.length - 1] * multi;
            if (num1 == num2)
                count--;
        }
        return count;
    }

}
