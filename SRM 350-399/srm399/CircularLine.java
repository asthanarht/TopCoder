package srm399;

public class CircularLine {
    public int longestTravel(int[] t) {
        int total = 0;
        for (int i = 0; i < t.length; i++)
            total += t[i];
        total = total / 2;
        int max = 0;
        for (int i = 0; i < t.length; i++) {
            int sum = 0;
            for (int j = 0; j < t.length; j++) {
                int index = i + j;
                index %= t.length;
                sum += t[index];
                if (sum > total)
                    break;
                else if (sum > max)
                    max = sum;
            }
        }
        return max;
    }
}
