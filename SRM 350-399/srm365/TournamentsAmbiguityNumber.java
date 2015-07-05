public class TournamentsAmbiguityNumber {
    public int scrutinizeTable(String[] table) {
        int n = table.length;
        int res = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (table[i].charAt(j) == '1')
                    for (int k = 0; k < n; k++)
                        if (table[j].charAt(k) == '1'
                                && table[k].charAt(i) == '1')
                            res++;
        return res;
    }
}
