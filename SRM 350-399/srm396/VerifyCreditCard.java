public class VerifyCreditCard {
    public String checkDigits(String cardNumber) {
        int num[] = new int[cardNumber.length()];
        for (int i = 0; i < num.length; i++)
            num[i] = Integer.parseInt("" + cardNumber.charAt(i));
        if (num.length % 2 == 0) {
            for (int i = 0; i < num.length; i++)
                if (i % 2 == 0)
                    num[i] *= 2;
        }
        else {
            for (int i = 0; i < num.length; i++)
                if (i % 2 != 0)
                    num[i] *= 2;
        }
        int sum = 0;
        for (int i = 0; i < num.length; i++)
            while (num[i] > 0) {
                sum += num[i] % 10;
                num[i] /= 10;
            }
        if (sum % 10 == 0)
            return "VALID";
        return "INVALID";
    }
}
