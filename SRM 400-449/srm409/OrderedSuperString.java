package srm409;

public class OrderedSuperString {
    public int getLength(String[] words) {
        int n = words.length;
        String supers = words[0];
        int index = 0;

        for (int i = 1; i < n; i++) {
            int sn = supers.length();
            int nn = words[i].length();
            boolean ok = false;
            for (int j = index; j < sn; j++) {
                if (nn < sn - j) {
                    if (words[i].compareTo(supers.substring(j, j + nn)) == 0) {
                        index = j;
                        ok = true;
                        break;
                    }
                }
                else if (nn == sn - j) {
                    if (words[i].compareTo(supers.substring(j)) == 0) {
                        index = j;
                        ok = true;
                        break;
                    }
                }
                else {
                    if (words[i].substring(0, sn - j).compareTo(
                            supers.substring(j)) == 0) {
                        index = j;
                        ok = true;
                        supers += words[i].substring(sn - j);
                        break;
                    }
                }
            }
            if (!ok) {
                index = sn;
                supers += words[i];
            }
        }

        return supers.length();
    }
}
