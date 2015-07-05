public class MatchNumbersEasy {
    public String maxNumber(int[] matches, int n) {
        String[] dp = new String[n + 1];
        dp[0] = "";
        for (int i = 1; i <= n; i++) {
            String max = "";
            for (int j = 0; j < matches.length; j++)
                if (i - matches[j] >= 0) {
                    String next = dp[i - matches[j]] + j;
                    if (next.startsWith("0"))
                        next = "";
                    max = getBigger(max, next);
                }
            dp[i] = max;
        }
        if (dp[n].length() == 0)
            return "0";
        else
            return dp[n];
    }

    private String getBigger(String num1, String num2) {
        if (num1.length() > num2.length())
            return num1;
        else if (num1.length() < num2.length())
            return num2;
        else
            return num1.compareTo(num2) > 0 ? num1 : num2;
    }
}
