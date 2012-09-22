package srm372;

public class RoadConstruction {
    public int getExitTime(String[] currentLanes) {
        int n = currentLanes.length, highest = 0;
        int count = 0;
        boolean exist = false;
        boolean[] yield = new boolean[n];
        while (true) {
            exist = false;
            for (int i = n - 1; i >= 0; i--)
                if (currentLanes[i].length() > 0) {
                    highest = i;
                    break;
                }
            for (int i = 0; i < highest; i++)
                if (!yield[i])
                    yield[i] = true;
                else if (currentLanes[i].length() > 0) {
                    if (currentLanes[i].charAt(0) == 'D')
                        return count;
                    currentLanes[i] = currentLanes[i].substring(1);
                    count++;
                    yield[i] = false;
                    exist = true;
                    break;
                }
            if (!exist) {
                if (currentLanes[highest].charAt(0) == 'D')
                    return count;
                currentLanes[highest] = currentLanes[highest].substring(1);
                count++;
            }
        }
    }
}
