import java.math.BigInteger;

public class ObtainingDigitK {
    public int minNumberToAdd(String originalNumber, int k) {
        BigInteger num = new BigInteger(originalNumber);
        String snum = num.toString();
        for (int add = 0; add <= 10; add++) {
            for (int i = 0; i < snum.length(); i++)
                if (snum.charAt(i) - '0' == k)
                    return add;
            num = num.add(BigInteger.ONE);
            snum = num.toString();
        }
        return 0;
    }
}
