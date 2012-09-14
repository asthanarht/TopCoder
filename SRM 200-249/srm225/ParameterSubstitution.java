package srm225;

public class ParameterSubstitution {
   public String processParams(String code, String[] params) {
      String result = "";
      for (int i = 0; i < code.length(); i++)
         if (code.charAt(i) == '$') {
            int j = i + 1;
            if (j == code.length() || code.charAt(j) == '0') {
               result += code.charAt(i);
               continue;
            }
            String num = "";
            int index = 0;
            while (j < code.length() && Character.isDigit(code.charAt(j))) {
               j++;
               num = code.substring(i + 1, j);
               index = Integer.parseInt(num);
               if (index > params.length) {
                  j--;
                  break;
               }
            }
            if (j == i + 1) {
               result += code.charAt(i);
               continue;
            }
            num = code.substring(i + 1, j);
            index = Integer.parseInt(num);
            result += params[index - 1];
            i = j - 1;
         }
         else
            result += code.charAt(i);
      return result;
   }
}
