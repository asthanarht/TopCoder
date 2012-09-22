package srm307;

public class PartSorting {
    public int[] process(int[] data, int nSwaps) {
        int n = data.length;
        for (int i = 0; i < n && nSwaps > 0; i++) {
            int max = data[i];
            int index = 0;
            for (int j = i + 1; j < n && j <= i + nSwaps; j++)
                if (data[j] > max) {
                    max = data[j];
                    index = j;
                }
            if (max > data[i]) {
                for (int j = index; j > i; j--)
                    data[j] = data[j - 1];
                data[i] = max;
                nSwaps -= index - i;
            }
        }
        return data;
    }
}
