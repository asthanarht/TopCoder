import java.util.Arrays;

public class NextNumber {
    public int getNextNumber(int N) {
        boolean bits[] = new boolean[32];
        for (int i = 31; i >= 0; i--, N = N >> 1)
            if (N % 2 == 1)
                bits[i] = true;
        int count = 0;
        boolean found = false;
        for (int i = 31; i >= 0; i--) {
            found = bits[i] ? true : found;
            count += bits[i] ? 1 : 0;
            if (found && !bits[i]) {
                bits[i] = true;
                Arrays.fill(bits, i + 1, 32, false);
                Arrays.fill(bits, 33 - count, 32, true);
                break;
            }
        }
        int num = 0;
        for (int i = 0; i < 32; i++) {
            num = num << 1;
            num += bits[i] ? 1 : 0;
        }
        return num;
    }
}
