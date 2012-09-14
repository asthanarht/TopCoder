package srm397;

public class BreakingTheCode {
   public String decodingEncoding(String code, String message) {
      if (message.charAt(0) >= '0' && message.charAt(0) <= '9') {
         String text = "";
         for (int i = 0; i < message.length(); i += 2) {
            int index = Integer.parseInt(message.substring(i, i + 2));
            System.out.println(index);
            text += code.charAt(index - 1);
         }
         return text;
      }
      else {
         String cryp = "";
         for (int i = 0; i < message.length(); i++)
            cryp += String.format("%2d", code.indexOf(message.charAt(i)) + 1);
         cryp = cryp.replaceAll(" ", "0");
         return cryp;
      }
   }
}
