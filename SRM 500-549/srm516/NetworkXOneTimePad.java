import java.util.HashSet;

public class NetworkXOneTimePad {
    public int crack(String[] plaintexts, String[] ciphertexts) {
        HashSet<String> keys = new HashSet<String>();
        for (String pt : plaintexts)
            for (String ct : ciphertexts)
                keys.add(getKey(pt, ct));

        HashSet<String> cipherset;
        int count = 0;
        for (String key : keys) {
            cipherset = new HashSet<String>();
            for (String pt : plaintexts)
                cipherset.add(encript(pt, key));
            count++;
            for (String ct : ciphertexts)
                if (!cipherset.contains(ct)) {
                    count--;
                    break;
                }
        }
        return count;
    }

    private String encript(String mes, String key) {
        char cipher[] = new char[mes.length()];
        for (int i = 0; i < mes.length(); i++)
            if (mes.charAt(i) == key.charAt(i))
                cipher[i] = '0';
            else
                cipher[i] = '1';
        return new String(cipher);
    }

    private String getKey(String plain, String cipher) {
        char p[] = plain.toCharArray();
        char c[] = cipher.toCharArray();
        char k[] = new char[p.length];
        for (int i = 0; i < p.length; i++)
            if (c[i] == '0')
                k[i] = p[i];
            else if (p[i] == '0')
                k[i] = '1';
            else
                k[i] = '0';
        return new String(k);
    }
}
