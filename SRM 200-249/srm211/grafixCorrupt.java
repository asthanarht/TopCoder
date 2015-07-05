public class grafixCorrupt {
    public int selectWord(String[] dictionary, String candidate) {
        int n = dictionary.length, m = candidate.length();
        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < m; j++)
                if (dictionary[i].charAt(j) == candidate.charAt(j))
                    count++;
            if (count > max) {
                max = count;
                index = i;
            }
        }
        return index;
    }
}
