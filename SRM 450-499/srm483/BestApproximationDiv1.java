public class BestApproximationDiv1 {
    private static char[] num;

    public static String findFraction(int maxDen, String number) {
        num = number.toCharArray();
        int quality = 0;
        int bestD = 1, bestN = 0;
        for (int denominator = 1; denominator <= maxDen; denominator++)
            for (int numerator = 0; numerator < denominator; numerator++) {
                int q = quality(numerator, denominator);
                if (q > quality) {
                    quality = q;
                    bestD = denominator;
                    bestN = numerator;
                }
            }
        return bestN + "/" + bestD + " has " + quality + " exact digits";
    }

    private static int quality(double numerator, double denominator) {
        int quality = 1;
        String fraction = Double.toString(numerator / denominator);
        for (int i = fraction.length(); i < 8; i++)
            fraction += '0';
        for (int i = 2; i < 8; i++)
            if (fraction.charAt(i) == num[i])
                quality++;
            else
                break;
        return quality;
    }
}
