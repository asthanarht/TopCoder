package srm390;

public class ConcatenateNumber {
    public int getSmallest(int number, int k) {
        int length = ("" + number).length();
        long time = (long) Math.pow(10, length);
        long remainder = number % k;
        for (int i = 1; i < 1000000; i++) {
            if (remainder == 0)
                return i;
            // let a = number.toString()
            // then aa % k = (a * pow(10, length) + a) % k
            // = ((a % k) * pow(10, length) + a) % k
            remainder = (remainder * time + number) % k;
        }
        return -1;
    }
}
