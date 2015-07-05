public class LotteryCheating {
    // the number of divisors of a number is odd iff it is a perfect square
    public int minimalChange(String ID) {
        int result = 10;
        for (int i = 0; i <= 100000; i++) {
            String square = "" + (long) i * i;
            if (square.length() > ID.length())
                break;
            int count = 0, diff = ID.length() - square.length();
            for (int index = 0; index < diff; index++)
                if (ID.charAt(index) != '0')
                    count++;
            for (int index = 0; index < square.length(); index++)
                if (ID.charAt(index + diff) != square.charAt(index))
                    count++;
            result = Math.min(result, count);
        }
        return result;
    }
}
