public class MultiNumber {
    public String check(int number) {
        String num = String.valueOf(number);
        for (int i = 1; i < num.length(); i++) {
            int mul1 = 1;
            int mul2 = 1;
            for (int j = 0; j < i; j++)
                mul1 *= num.charAt(j) - '0';
            for (int j = i; j < num.length(); j++)
                mul2 *= num.charAt(j) - '0';
            if (mul1 == mul2)
                return "YES";
        }
        return "NO";
    }
}
