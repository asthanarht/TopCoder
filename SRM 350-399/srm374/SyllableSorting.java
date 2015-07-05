import java.util.ArrayList;
import java.util.Arrays;

public class SyllableSorting {
    public String[] sortWords(String[] words) {
        int n = words.length;
        Word[] ws = new Word[n];
        for (int i = 0; i < n; i++)
            ws[i] = new Word(words[i]);
        Arrays.sort(ws);
        for (int i = 0; i < n; i++)
            words[i] = ws[i].word;
        return words;
    }
}

class Word implements Comparable<Object> {
    String word;

    public Word(String word) {
        super();
        this.word = word;
    }

    public int compareTo(Object arg0) {
        String[] seg1 = split(this.word);
        String[] seg2 = split(((Word) arg0).word);
        String[] seg1s = seg1.clone();
        String[] seg2s = seg2.clone();
        Arrays.sort(seg1s);
        Arrays.sort(seg2s);
        int c = compare(seg1s, seg2s);
        if (c != 0)
            return c;
        return compare(seg1, seg2);
    }

    public static String[] split(String word) {
        ArrayList<String> segs = new ArrayList<String>();
        int index1 = 0, index2 = 0;
        boolean end = false;
        for (; index2 < word.length(); index2++)
            if (isVowel(word.charAt(index2)))
                end = true;
            else if (end) {
                segs.add(word.substring(index1, index2));
                index1 = index2;
                end = false;
            }
        segs.add(word.substring(index1, index2));
        String[] seg = new String[segs.size()];
        for (int i = 0; i < segs.size(); i++)
            seg[i] = segs.get(i);
        return seg;
    }

    public static boolean isVowel(char c) {
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
            return true;
        return false;
    }

    public static int compare(String[] seg1, String[] seg2) {
        for (int i = 0; i < Math.min(seg1.length, seg2.length); i++) {
            int c = seg1[i].compareTo(seg2[i]);
            if (c != 0)
                return c;
        }
        return seg1.length - seg2.length;
    }
}