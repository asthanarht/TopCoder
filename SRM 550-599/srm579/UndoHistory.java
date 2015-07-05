public class UndoHistory {
    public int minPresses(String[] lines) {
        int total = lines[0].length() + 1;
        String buffer = lines[0];
        for (int i = 1; i < lines.length; i++) {
            int restore = 2 + lines[i].length();
            int goon = Integer.MAX_VALUE;
            if (lines[i].startsWith(buffer))
                goon = lines[i].length() - buffer.length();
            for (int j = 0; j < i; j++) {
                for (int common = 0; common < Math.min(lines[i].length(),
                        lines[j].length()); common++)
                    if (lines[i].charAt(common) == lines[j].charAt(common))
                        restore = Math.min(restore, 1 + lines[i].length()
                                - common);
                    else
                        break;
            }
            buffer = lines[i];
            total += Math.min(restore, goon) + 1;
        }
        return total;
    }
}
