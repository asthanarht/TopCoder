//import java.util.ArrayList;
//import java.util.HashSet;
//
//public class CompositeSmash {
//   public String thePossible(int N, int target) {
//      if (N == target)
//         return "Yes";
//      if (target > N)
//         return "No";
//      if (N % target != 0)
//         return "No";
//      ArrayList<Integer> allT = new ArrayList<Integer>();
//      HashSet<Integer> divN = new HashSet<Integer>();
//      HashSet<Integer> divT = new HashSet<Integer>();
//      boolean found = true;
//      while (N >= 2 && found) {
//         found = false;
//         for (int i = 2; i <= N; i++)
//            if (N % i == 0) {
//               divN.add(i);
//               N /= i;
//               found = true;
//               break;
//            }
//      }
//      while (target >= 2 && found) {
//         found = false;
//         for (int i = 2; i <= target; i++)
//            if (target % i == 0) {
//               allT.add(i);
//               divT.add(i);
//               target /= i;
//               found = true;
//               break;
//            }
//      }
//      if (allT.size() == 1)
//         return "Yes";
//      if (divN.size() == 1 && divT.size() == 1 && allT.size() == 2)
//         return "Yes";
//      return "No";
//   }
//}

public class CompositeSmash {
    public String thePossible(int N, int target) {
        if (possible(N, target))
            return "Yes";
        else
            return "No";
    }

    private boolean possible(int N, int target) {
        if (N == target)
            return true;
        if (target > N)
            return false;
        if (N % target != 0)
            return false;
        for (int i = 2; i * i <= N; i++)
            if (N % i == 0) {
                int num = N / i;
                if (possible(i, target) || possible(num, target))
                    ;
                else
                    return false;
            }
        return true;
    }
}