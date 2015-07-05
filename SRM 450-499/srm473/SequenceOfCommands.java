public class SequenceOfCommands {
    private int[] xchange = { 0, 1, 0, -1 };
    private int[] ychange = { 1, 0, -1, 0 };

    public String whatHappens(String[] commands) {
        int n = commands.length;
        int x = 0, y = 0, dir = 0;
        for (int times = 0; times < 4; times++) {
            for (int i = 0; i < n; i++)
                for (int j = 0; j < commands[i].length(); j++) {
                    char c = commands[i].charAt(j);
                    if (c == 'S') {
                        x += xchange[dir];
                        y += ychange[dir];
                    }
                    else if (c == 'R')
                        dir = (dir + 1) % 4;
                    else if (c == 'L')
                        dir = (dir + 3) % 4;
                }
            if (x == 0 && y == 0)
                return "bounded";
        }
        return "unbounded";
    }
}
