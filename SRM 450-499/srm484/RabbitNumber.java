package srm484;

import java.util.LinkedList;

public class RabbitNumber {
    public static int theCount(int low, int high) {
        int count = 0;
        LinkedList<Long> queue = new LinkedList<Long>();
        queue.add(1L);
        queue.add(2L);
        queue.add(3L);
        while (queue.size() > 0) {
            long num = queue.poll();
            if (num >= low && num <= high && check(num))
                count++;
            num *= 10;
            if (num <= high) {
                queue.add(num);
                queue.add(num + 1);
                queue.add(num + 2);
                queue.add(num + 3);
            }
        }
        return count;
    }

    private static boolean check(long num) {
        long mul = num * num;
        return s(mul) == s(num) * s(num);
    }

    private static long s(long num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
