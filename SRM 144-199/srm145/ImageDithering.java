import java.util.HashSet;

public class ImageDithering {

    public int count(String dithered, String[] screen) {

        HashSet<Character> d = new HashSet<Character>();
        for (int i = 0; i < dithered.length(); i++)
            d.add(dithered.charAt(i));

        int count = 0;

        int r = screen.length;
        int c = screen[0].length();
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                if (d.contains(screen[i].charAt(j)))
                    count++;

        return count;

    }

}
