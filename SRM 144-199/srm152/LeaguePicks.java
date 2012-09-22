package srm152;

import java.util.ArrayList;

public class LeaguePicks {
    public int[] returnPicks(int position, int friends, int picks) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        int count = 0;
        int left = position - 1;
        int right = friends - position;
        count -= left;
        left *= 2;
        right *= 2;
        while (true) {
            if (list.size() % 2 == 0)
                count += left + 1;
            else
                count += right + 1;
            if (count <= picks)
                list.add(count);
            else
                break;
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            array[i] = list.get(i).intValue();
        return array;
    }
}
