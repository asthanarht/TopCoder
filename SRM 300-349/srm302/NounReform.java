public class NounReform {
    public String[] makePlural(String[] nouns) {
        String[] es = { "s", "z", "x", "ch", "sh" };
        String[] s = { "ay", "ey", "iy", "oy", "uy" };
        iter: for (int i = 0; i < nouns.length; i++) {
            for (int j = 0; j < es.length; j++)
                if (nouns[i].endsWith(es[j])) {
                    nouns[i] += "es";
                    continue iter;
                }
            for (int j = 0; j < s.length; j++)
                if (nouns[i].endsWith(s[j])) {
                    nouns[i] += "s";
                    continue iter;
                }
            if (nouns[i].endsWith("y")) {
                nouns[i] = nouns[i].substring(0, nouns[i].length() - 1) + "ies";
                continue iter;
            }
            nouns[i] += "s";
        }
        return nouns;
    }
}
