public class PointsOnACircle {
    public int color(String[] points) {
        boolean[] space = new boolean[360];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < points.length; i++)
            sb.append(points[i]);

        String[] input = sb.toString().split(" ");
        for (int i = 0; i < input.length; i++)
            if (input[i].length() > 0)
                space[Integer.parseInt(input[i])] = true;

        int max = 0;
        for (int angle = 1; angle < 360; angle++) {
            int count = 0;
            boolean[] copy = space.clone();
            boolean found = true;
            while (found) {
                found = false;
                for (int i = 0; i < 360; i++)
                    if (copy[i]
                            && copy[(i + angle) % 360]
                            && (!copy[(360 + i - angle) % 360] || !copy[(i + angle * 2) % 360])) {
                        count += 2;
                        copy[i] = false;
                        copy[(i + angle) % 360] = false;
                        found = true;
                    }
                for (int i = 0; i < 360; i++)
                    if (copy[i] && copy[(i + angle) % 360]) {
                        count += 2;
                        copy[i] = false;
                        copy[(i + angle) % 360] = false;
                        found = true;
                        break;
                    }
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
