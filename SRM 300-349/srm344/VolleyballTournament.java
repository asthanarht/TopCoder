package srm344;

import java.util.Arrays;

public class VolleyballTournament {
    public String reconstructResults(int wonMatches, int lostMatches,
            int wonSets, int lostSets) {
        int[] w1 = new int[wonMatches], w2 = new int[wonMatches];
        int[] l1 = new int[lostMatches], l2 = new int[lostMatches];
        Arrays.fill(w1, 3);
        Arrays.fill(l2, 3);
        int w = wonSets - wonMatches * 3;
        int l = lostSets - lostMatches * 3;
        int index;
        if (wonMatches > 0) {
            index = l - (l / wonMatches) * wonMatches;
            for (int i = 0; i < index; i++)
                w2[i] = l / wonMatches + 1;
            for (int i = index; i < wonMatches; i++)
                w2[i] = l / wonMatches;
        }
        if (lostMatches > 0) {
            index = w - (w / lostMatches) * lostMatches;
            for (int i = 0; i < index; i++)
                l1[i] = w / lostMatches + 1;
            for (int i = index; i < lostMatches; i++)
                l1[i] = w / lostMatches;
        }
        Arrays.sort(w2);
        Arrays.sort(l1);
        int count = 0;
        for (int i = 0; i < wonMatches; i++)
            if (w2[i] == 1)
                count++;
        if (count > 1)
            return "AMBIGUITY";
        count = 0;
        for (int i = 0; i < lostMatches; i++)
            if (l1[i] == 1)
                count++;
        if (count > 1)
            return "AMBIGUITY";
        String res = "";
        for (int i = 0; i < lostMatches; i++)
            res += l1[i] + "-" + l2[i] + ",";
        for (int i = 0; i < wonMatches; i++)
            res += w1[i] + "-" + w2[i] + ",";
        res = res.substring(0, res.length() - 1);
        return res;
    }
}
