import java.util.Arrays;

public class TomekPhone {
    public int minKeystrokes(int[] frequencies, int[] keySizes) {
        int f = frequencies.length, k = keySizes.length;
        int sum = 0;
        for (int i = 0; i < k; i++)
            sum += keySizes[i];
        if (sum < f)
            return -1;
        Arrays.sort(frequencies);
        Arrays.sort(keySizes);
        int result = 0;
        int level = 1;
        for (int i = f - 1, j = k - 1; i >= 0; i--, j--) {
            if (j < 0 || keySizes[j] < level) {
                j = k - 1;
                level++;
            }
            result += level * frequencies[i];
        }
        return result;
    }
}
