import java.util.HashMap;

public class HuffmanDecoding {
    public String decode(String archive, String[] dictionary) {
        String result = "";
        String temp = "";
        HashMap<String, String> dic = new HashMap<String, String>();
        for (int i = 0; i < dictionary.length; i++)
            dic.put(dictionary[i], "" + (char) ('A' + i));
        for (int i = 0; i < archive.length(); i++) {
            temp += archive.charAt(i);
            if (dic.get(temp) != null) {
                result += dic.get(temp);
                temp = "";
            }
        }
        return result;
    }
}
