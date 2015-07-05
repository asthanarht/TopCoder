import java.util.HashSet;

public class CubeStickers {

    public String isPossible(String[] sticker) {

        HashSet<String> colors = new HashSet<String>();

        for (int i = 0; i < sticker.length; i++)
            colors.add(sticker[i]);

        if (colors.size() < 3)
            return "NO";

        int count1 = 0;
        int count2 = 0;
        for (String color : colors) {
            count2 = 0;
            for (int i = 0; i < sticker.length; i++) {
                if (sticker[i].compareTo(color) == 0)
                    count2++;
            }
            if (count2 > 1)
                count1++;
        }

        if (count1 >= 3)
            return "YES";
        else if (count1 == 2 && colors.size() >= 4)
            return "YES";
        else if (count1 == 1 && colors.size() >= 5)
            return "YES";
        else if (count1 == 0 && colors.size() >= 6)
            return "YES";

        return "NO";
    }
}
