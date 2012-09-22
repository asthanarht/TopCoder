package srm507;

public class CubeAnts {
    public int getMinimumSteps(int[] pos) {
        if (has(6, pos))
            return 3;
        else if (has(2, pos) || has(5, pos) || has(7, pos))
            return 2;
        else if (has(1, pos) || has(3, pos) || has(4, pos))
            return 1;
        else
            return 0;
    }

    public boolean has(int num, int[] pos) {
        for (int i = 0; i < pos.length; i++) {
            if (pos[i] == num)
                return true;
        }
        return false;
    }
}
