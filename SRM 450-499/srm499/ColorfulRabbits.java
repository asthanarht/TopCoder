package srm499;

import java.util.Arrays;

public class ColorfulRabbits {
    public int getMinimum(int[] replies) {
        Arrays.sort(replies);
        int r = replies[0];
        int count = 0;
        int total = 0;
        for (int i = 0; i < replies.length; i++) {
            if (replies[i] == r)
                count++;
            else {
                if (r >= count - 1)
                    total += r + 1;
                else
                    total += (count + r) / (r + 1) * (r + 1);
                r = replies[i];
                count = 1;
            }
        }
        if (count > 0)
            if (r >= count - 1)
                total += r + 1;
            else
                total += (count + r) / (r + 1) * (r + 1);
        return total;
    }
}
