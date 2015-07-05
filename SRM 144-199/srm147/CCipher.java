public class CCipher {
    public String decode(String cipherText, int shift) {
        String s = "";
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (c - shift < 'A')
                c = (char) ('Z' - 'A' + c - shift + 1);
            else
                c = (char) (c - shift);
            s += "" + (char) c;
        }
        return s;
    }
}
