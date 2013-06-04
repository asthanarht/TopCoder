package srm480;

import java.util.Arrays;

public class Cryptography {
    public long encrypt(int[] numbers) {
        Arrays.sort(numbers);
        long result = numbers[0] + 1;
        for (int i = 1; i < numbers.length; i++)
            result *= numbers[i];
        return result;
    }
}
