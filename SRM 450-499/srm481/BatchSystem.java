import java.util.HashMap;

public class BatchSystem {
    public int[] schedule(int[] duration, String[] user) {
        HashMap<String, Integer> userID = new HashMap<String, Integer>();
        for (int i = 0; i < user.length; i++)
            if (!userID.containsKey(user[i]))
                userID.put(user[i], userID.size());
        long[] total = new long[userID.size()];
        for (int i = 0; i < duration.length; i++)
            total[userID.get(user[i])] += duration[i];
        int[] result = new int[duration.length];
        int index = 0;
        for (int i = 0; i < userID.size(); i++) {
            long min = Long.MAX_VALUE;
            int minj = 0;
            for (int j = 0; j < userID.size(); j++)
                if (total[j] < min) {
                    min = total[j];
                    minj = j;
                }
            for (int j = 0; j < duration.length; j++)
                if (userID.get(user[j]) == minj)
                    result[index++] = j;
            total[minj] = Long.MAX_VALUE;
        }
        return result;
    }
}
