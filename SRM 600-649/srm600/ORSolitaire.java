package srm600;

public class ORSolitaire {
    public int getMinimum(int[] numbers, int goal) {
        int[] cnt = new int[32];
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            boolean ok = true;
            for (int j = 0; j < 32; j++)
                if ((goal & (1 << j)) == 0 && (num & (1 << j)) != 0)
                    ok = false;
            if (ok) {
                for (int j = 0; j < 32; j++)
                    if ((num & (1 << j)) != 0)
                        cnt[j]++;
            }
        }
        int min = numbers.length;
        for (int i = 0; i < 32; i++)
            if ((goal & (1 << i)) != 0)
                min = Math.min(min, cnt[i]);
        return min;
    }
}
