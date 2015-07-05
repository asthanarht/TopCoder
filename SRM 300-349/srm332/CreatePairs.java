import java.util.Arrays;

public class CreatePairs {
    public int maximalSum(int[] data) {
        Arrays.sort(data);
        int res = 0, count = 0;
        int index = 0;
        for (; index + 1 < data.length && data[index] < 0
                && data[index + 1] < 0; index++) {
            res += data[index] * data[index + 1];
            index++;
        }
        for (int i = data.length - 1; i >= index; i--) {
            if (data[i] > 1 && i > 0 && data[i - 1] > 1) {
                res += data[i] * data[i - 1];
                i--;
            }
            else if (data[i] == 0)
                count++;
            else if (data[i] < 0 && count > 0)
                count--;
            else
                res += data[i];
        }
        return res;
    }
}
