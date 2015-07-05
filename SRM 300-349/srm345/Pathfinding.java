public class Pathfinding {
    public int getDirections(int x, int y) {
        int absx = Math.abs(x), absy = Math.abs(y);
        int dis = absx + absy;
        if ((x == 0 && y < 0 && absy % 2 == 0)
                || (y == 0 && x < 0 && absx % 2 == 0)
                || (x < 0 && y < 0 && absx % 2 == 0 && absy % 2 == 0))
            dis += 4;
        else if ((x == 0 && y < 0) || (y == 0 && x < 0) || (x < 0 && y < 0)
                || (x > 0 && y < 0 && absx % 2 == 0 && absy % 2 == 1)
                || (x < 0 && y > 0 && absx % 2 == 1 && absy % 2 == 0)
                || (x > 0 && y > 0 && absx % 2 == 1 && absy % 2 == 1))
            dis += 2;
        return dis;
    }
}
