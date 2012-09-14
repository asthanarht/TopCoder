package srm392;

public class TwoStringMasks {
   public String shortestCommon(String s1, String s2) {
      String combine = "";
      int index1 = s1.indexOf("*");
      int index2 = s2.indexOf("*");
      String[] seg1 = new String[2];
      seg1[0] = s1.substring(0, index1);
      seg1[1] = s1.substring(index1 + 1);
      String[] seg2 = new String[2];
      seg2[0] = s2.substring(0, index2);
      seg2[1] = s2.substring(index2 + 1);

      boolean b1 = seg1[0].length() >= seg2[0].length();
      String longer = b1 ? seg1[0] : seg2[0];
      String shorter = b1 ? seg2[0] : seg1[0];
      if (!longer.startsWith(shorter))
         return "impossible";
      combine += shorter;
      String stack = longer.substring(shorter.length());

      boolean b2 = seg1[1].length() >= seg2[1].length();
      longer = b2 ? seg1[1] : seg2[1];
      shorter = b2 ? seg2[1] : seg1[1];
      if (!longer.endsWith(shorter))
         return "impossible";
      longer = longer.substring(0, longer.length() - shorter.length());
      boolean found = false;
      if (b1 != b2)
         for (int i = 0; i < stack.length(); i++)
            if (stack.length() - i <= longer.length()
                  && longer.startsWith(stack.substring(i))) {
               combine += stack.substring(0, i);
               combine += longer + shorter;
               found = true;
               break;
            }
      if (!found)
         combine += stack + longer + shorter;
      return combine;
   }
}
