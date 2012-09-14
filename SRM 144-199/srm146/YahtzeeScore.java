package srm146;

public class YahtzeeScore {

    public int maxPoints(int[] toss) {

        int n = toss.length;
        int count = 0;
        int max = 0;

        for (int i = 1; i <= 6; i++) {
            count = 0;
            for (int j = 0; j < n; j++) {
                if (toss[j] == i)
                    count += i;
            }
            if (count > max)
                max = count;
        }

        return max;

    }

}
