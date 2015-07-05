import java.util.Arrays;

public class RestaurantManager {
    public int allocateTables(int[] tables, int[] groupSizes, int[] arrivals,
            int[] departures) {
        int count = 0;
        int N = tables.length;
        int M = groupSizes.length;
        Arrays.sort(tables);
        int usedUtil[] = new int[N];
        Arrays.fill(usedUtil, 0);

        for (int i = 0; i < M; i++) {
            int size = groupSizes[i];
            int arr = arrivals[i];
            int dep = departures[i];
            int index = -1;
            for (int j = 0; j < N; j++)
                if (tables[j] >= size && usedUtil[j] <= arr) {
                    index = j;
                    usedUtil[j] = dep;
                    break;
                }
            if (index == -1)
                count += size;
        }

        return count;
    }
}
