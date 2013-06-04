package srm480;

import java.util.HashSet;

public class InternetSecurity {
    public String[] determineWebsite(String[] address, String[] keyword,
            String[] dangerous, int threshold) {
        int n = address.length;
        HashSet<String> words = new HashSet<String>();
        for (String word : dangerous)
            words.add(word);
        boolean[] danger = new boolean[n];
        boolean found = false;
        do {
            found = false;
            for (int i = 0; i < n; i++)
                if (!danger[i]) {
                    String[] keys = keyword[i].split(" ");
                    int count = 0;
                    for (String word : keys)
                        if (words.contains(word))
                            count++;
                    if (count >= threshold) {
                        danger[i] = true;
                        found = true;
                        for (String word : keys)
                            words.add(word);
                    }
                }
        } while (found);
        int count = 0;
        for (int i = 0; i < n; i++)
            if (danger[i])
                count++;
        String[] result = new String[count];
        for (int i = 0, index = 0; i < n; i++)
            if (danger[i])
                result[index++] = address[i];
        return result;
    }
}
