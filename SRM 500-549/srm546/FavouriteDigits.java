import java.util.Arrays;

public class FavouriteDigits {
    public long findNext(long N, int digit1, int count1, int digit2, int count2) {
        if (count1 == 0 && count2 == 0)
            return N;
        String require = require(count1, digit1, count2, digit2);

        String ns = String.valueOf(N);
        if (require.length() > ns.length()) {
            if (require.charAt(0) != '0')
                return Long.parseLong(require);
            else if (require.charAt(require.length() - 1) != '0')
                return Long.parseLong(require.charAt(require.length() - 1)
                        + require.substring(0, require.length() - 1));
            else
                return Long.parseLong('1' + require);
        }
        else {
            String result = "";
            int check1 = count1, check2 = count2;
            for (int i = 0; i < ns.length(); i++) {
                String max = getMax(result, ns.length(), check1, digit1,
                        check2, digit2);
                if (ns.compareTo(max) > 0)
                    break;
                for (int digit = 0; digit < 10; digit++) {
                    String candidate = result + digit;
                    if (check1 > 0 && digit == digit1) {
                        max = getMax(candidate, ns.length(), check1 - 1,
                                digit1, check2, digit2);
                        if (ns.compareTo(max) <= 0) {
                            check1--;
                            result += digit;
                            break;
                        }
                    }
                    if (check2 > 0 && digit == digit2) {
                        max = getMax(candidate, ns.length(), check1, digit1,
                                check2 - 1, digit2);
                        if (ns.compareTo(max) <= 0) {
                            check2--;
                            result += digit;
                            break;
                        }
                    }
                    max = getMax(candidate, ns.length(), check1, digit1,
                            check2, digit2);
                    if (ns.compareTo(max) <= 0) {
                        result += digit;
                        break;
                    }
                }
            }
            if (check1 == 0 && check2 == 0)
                return Long.parseLong(result);
            int len = ns.length() + 1;
            result = "1";
            if (count1 > 0 && digit1 == 1)
                count1--;
            if (count2 > 0 && digit2 == 1)
                count2--;
            require = require(count1, digit1, count2, digit2);
            for (int i = 0; i < len - 1 - require.length(); i++)
                result += '0';
            result += require;
            return Long.parseLong(result);
        }
    }

    private String require(int count1, int digit1, int count2, int digit2) {
        String require = "";
        for (int i = 0; i < count1; i++)
            require += digit1;
        for (int i = 0; i < count2; i++)
            require += digit2;
        char[] chars = require.toCharArray();
        Arrays.sort(chars);
        require = String.valueOf(chars);
        return require;
    }

    private String getMax(String result, int len, int check1, int digit1,
            int check2, int digit2) {
        String max = result;
        int tofill = len - result.length();
        if (tofill < check1 + check2)
            return "0";
        for (int j = 0; j < tofill - check1 - check2; j++)
            max += '9';
        if (digit1 > digit2) {
            for (int j = 0; j < check1; j++)
                max += digit1;
            for (int j = 0; j < check2; j++)
                max += digit2;
        }
        else {
            for (int j = 0; j < check2; j++)
                max += digit2;
            for (int j = 0; j < check1; j++)
                max += digit1;
        }
        return max;
    }
}
