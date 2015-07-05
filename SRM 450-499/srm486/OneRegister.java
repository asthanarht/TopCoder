import java.util.HashSet;
import java.util.LinkedList;

public class OneRegister {
    private HashSet<Long> visited = new HashSet<Long>();
    private LinkedList<Long> key = new LinkedList<Long>();
    private LinkedList<String> operation = new LinkedList<String>();

    public String getProgram(int s, int t) {
        add(s, "");
        while (!key.isEmpty()) {
            long num = key.poll();
            String path = operation.poll();
            if (num == t)
                return path;
            if (num * num <= t)
                add(num * num, path + '*');
            if (num + num <= t)
                add(num + num, path + "+");
            if (path.length() == 0 && num != 1)
                add(1, "/");
        }
        return ":-(";
    }

    private void add(long num, String path) {
        if (visited.contains(num))
            return;
        visited.add(num);
        key.add(num);
        operation.add(path);
    }
}
