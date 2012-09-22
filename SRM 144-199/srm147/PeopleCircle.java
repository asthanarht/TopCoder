package srm147;

public class PeopleCircle {

    public static String order(int numMales, int numFemales, int K) {
        int n = numMales + numFemales + 1;
        boolean[] people = new boolean[n];
        int cur = 0;

        for (int i = 0; i < numFemales; i++) {
            for (int j = 0; j < K; j++) {
                cur++;
                if (cur >= n) {
                    cur -= n;
                    cur++;
                }
                while (people[cur]) {
                    cur++;
                    if (cur >= n) {
                        cur -= n;
                        cur++;
                    }
                }
            }
            people[cur] = true;
        }

        String result = "";
        n--;
        for (int i = 1; i <= n; i++)
            if (people[i])
                result += "F";
            else
                result += "M";
        return result;
    }
}
