package srm513;

public class TrainingCamp {
   public String[] determineSolvers(String[] attendance, String[] problemTopics) {
      int N = attendance.length;
      int M = attendance[0].length();
      int K = problemTopics.length;
      String[] result = new String[N];

      for (int i = 0; i < N; i++)
         result[i] = "";

      for (int i = 0; i < K; i++) {
         String require = problemTopics[i];
         for (int j = 0; j < N; j++) {
            boolean could = true;
            for (int k = 0; k < M; k++)
               if (require.charAt(k) == 'X' && attendance[j].charAt(k) == '-') {
                  could = false;
                  break;
               }
            if (could)
               result[j] += 'X';
            else
               result[j] += '-';
         }
      }
      return result;
   }
}
