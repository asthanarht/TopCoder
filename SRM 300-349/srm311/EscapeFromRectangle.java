package srm311;

public class EscapeFromRectangle {
    public int shortest(int x, int y, int w, int h) {
        int min = Math.min(x, y);
        min = Math.min(min, w - x);
        min = Math.min(min, h - y);
        return min;
    }
}
