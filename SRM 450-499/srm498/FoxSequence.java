public class FoxSequence {
    public String isValid(int[] seq) {
        if (seq.length < 5)
            return "NO";
        int[] difference = new int[5];
        int cur = seq[1] - seq[0];
        difference[0] = cur;
        int count = 1;
        for (int i = 0; i < seq.length - 1; i++) {
            int diff = seq[i + 1] - seq[i];
            if (diff != cur) {
                if (count == 5)
                    return "NO";
                difference[count++] = diff;
                cur = diff;
            }
        }
        if (count < 3)
            return "NO";
        if (difference[0] <= 0 || difference[1] >= 0)
            return "NO";
        if (count == 5) {
            if (difference[2] != 0 || difference[3] <= 0 || difference[4] >= 0)
                return "NO";
        }
        else if (difference[2] <= 0 || difference[3] >= 0)
            return "NO";
        return "YES";
    }
}
