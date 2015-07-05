public class TurtleSpy {
    public double maxDistance(String[] commands) {
        int n = commands.length;
        double forward = 0, backward = 0;
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            String[] coms = commands[i].split(" ");
            if (coms[0].equals("forward"))
                forward += Integer.parseInt(coms[1]);
            else if (coms[0].equals("backward"))
                backward += Integer.parseInt(coms[1]);
            else if (coms[0].equals("right"))
                degree[i] = Integer.parseInt(coms[1]);
            else if (coms[0].equals("left"))
                degree[i] = 360 - Integer.parseInt(coms[1]);
        }
        boolean[] cycle = new boolean[360];
        cycle[0] = true;
        for (int i = 0; i < n; i++)
            if (degree[i] != 0) {
                boolean[] ncycle = new boolean[360];
                for (int j = 0; j < 360; j++)
                    if (cycle[j]) {
                        ncycle[j] = true;
                        ncycle[(j + degree[i]) % 360] = true;
                    }
                cycle = ncycle;
            }
        for (int i = 0, d = 180; i < 180; i++)
            if (cycle[d + i] || cycle[d - i])
                return Math.sqrt(forward * forward + backward * backward - 2
                        * forward * backward
                        * Math.cos((d + i) / 180.0 * Math.PI));
        return Math.abs(forward - backward);
    }
}