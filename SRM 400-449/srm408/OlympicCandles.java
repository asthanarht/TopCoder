import java.util.Arrays;

public class OlympicCandles {

    public int numberOfNights(int[] candles) {

        int count1 = 0;// day
        int count2 = 0;// candle
        int count3 = 0;
        boolean done = false;
        int n = candles.length;

        while (true) {
            if (count1 > n)
                break;

            Arrays.sort(candles);

            count1++;
            count2 = 0;
            done = false;
            for (int i = n - 1; i >= 0; i--) {
                if (candles[i] > 0) {
                    candles[i]--;
                    count2++;
                    if (count2 == count1) {
                        count3++;
                        done = true;
                        break;
                    }
                }
            }

            if (!done)
                break;
        }

        return count3;
    }

}
