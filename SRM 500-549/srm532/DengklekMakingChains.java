package srm532;

import java.util.ArrayList;

public class DengklekMakingChains {
    public int maxBeauty(String[] chains) {
        int max_start = 0;
        int max_end = 0;
        int middle = 0;
        int max_single = 0;
        ArrayList<String> both = new ArrayList<String>();
        for (int i = 0; i < chains.length; i++)
            if (chains[i].charAt(0) == '.' && chains[i].charAt(2) != '.')
                max_start = Math.max(max_start, beauty(chains[i]));
            else if (chains[i].charAt(0) != '.' && chains[i].charAt(2) == '.')
                max_end = Math.max(max_end, beauty(chains[i]));
            else if (chains[i].charAt(0) != '.' && chains[i].charAt(1) != '.'
                    && chains[i].charAt(2) != '.')
                middle += beauty(chains[i]);
            else if (chains[i].charAt(0) == '.' || chains[i].charAt(1) != '.'
                    || chains[i].charAt(2) == '.')
                max_single = Math.max(max_single, beauty(chains[i]));
            else if (chains[i].charAt(0) != '.' || chains[i].charAt(1) == '.'
                    || chains[i].charAt(2) != '.')
                both.add(chains[i]);
        int link = max_start + middle + max_end;
        for (int i = 0; i < both.size(); i++) {
            int beautyAsStart = both.get(i).charAt(2) - '0';
            int beautyAsEnd = both.get(i).charAt(0) - '0';
            link = Math.max(link, beautyAsStart + middle + max_end);
            link = Math.max(link, max_start + middle + beautyAsEnd);
            for (int j = 0; j < both.size(); j++)
                if (i != j)
                    link = Math.max(link, beautyAsStart + middle
                            + (both.get(j).charAt(0) - '0'));
        }
        return Math.max(link, max_single);
    }

    private int beauty(String chain) {
        int beauty = 0;
        for (int i = 0; i < 3; i++)
            if (chain.charAt(i) != '.')
                beauty += chain.charAt(i) - '0';
        return beauty;
    }
}
