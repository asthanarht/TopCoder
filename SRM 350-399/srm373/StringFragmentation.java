package srm373;

public class StringFragmentation {
    public int largestFontSize(String text, int width, int height) {
        int max = -1;
        String[] words = text.split(" ");
        int n = words.length;
        for (int size = 8;; size++) {
            int h = 2 * size, w = size + 2, imax = height / h, jmax = width / w;
            int index = 0;
            for (int i = 0; i < imax && index < n; i++) {
                int j = -1;
                while (index < n && jmax - j >= words[index].length() + 1) {
                    j += words[index].length() + 1;
                    index++;
                }
            }
            if (index == n)
                max = size;
            else
                break;
        }
        return max;
    }
}
