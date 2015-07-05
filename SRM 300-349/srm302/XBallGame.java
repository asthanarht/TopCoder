import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class XBallGame {
    public String[] newStatistics(String[] placeboard) {
        HashSet<String> names = new HashSet<String>();
        String[] result = new String[placeboard.length];
        for (int i = 0; i < placeboard.length; i++) {
            String name = placeboard[i]
                    .substring(0, placeboard[i].indexOf("-"));
            if (names.contains(name))
                continue;
            names.add(name);
            ArrayList<String> position = new ArrayList<String>();
            for (int j = 0; j < placeboard.length; j++)
                if (placeboard[j].startsWith(name)
                        && placeboard[j].indexOf("-") == name.length())
                    position.add(placeboard[j].substring(placeboard[j]
                            .indexOf("-") + 1));
            String[] pos = new String[position.size()];
            for (int j = 0; j < position.size(); j++)
                pos[j] = position.get(j);
            Arrays.sort(pos);
            for (int j = 0; j < placeboard.length; j++)
                if (placeboard[j].startsWith(name)
                        && placeboard[j].indexOf("-") == name.length()) {
                    String thePos = placeboard[j].substring(name.length() + 1);
                    String newEntry = name + "-" + thePos;
                    for (int k = 0; k < pos.length; k++)
                        if (!pos[k].equals(thePos))
                            newEntry += "," + pos[k];
                    result[j] = newEntry;
                }
        }
        return result;
    }
}
