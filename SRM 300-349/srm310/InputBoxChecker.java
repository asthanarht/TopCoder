public class InputBoxChecker {
    public String[] checkPrefix(int smallest, int largest, int[] numbers) {
        String[] result = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            if (isValid(smallest, largest, numbers[i]))
                result[i] = "VALID";
            else
                result[i] = "INVALID";
        return result;
    }

    private boolean isValid(int smallest, int largest, int num) {
        long factor = 1;
        while (num * factor <= largest) {
            if (num * factor <= largest && (num + 1) * factor - 1 >= smallest)
                return true;
            factor *= 10;
        }
        return false;
    }
}
