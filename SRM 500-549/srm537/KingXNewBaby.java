package srm537;

public class KingXNewBaby {
    public String isValid(String name) {
        if (name.length() != 8)
            return "NO";
        char[] vowel = { 'a', 'e', 'i', 'o', 'u' };
        int[] count = new int[26];
        for (int i = 0; i < name.length(); i++)
            if (!Character.isLowerCase(name.charAt(i)))
                return "NO";
            else
                count[name.charAt(i) - 'a']++;
        int check = 0;
        for (int i = 0; i < vowel.length; i++) {
            check += count[vowel[i] - 'a'];
            if (check != 0 && check != 2)
                return "NO";
        }
        return check == 2 ? "YES" : "NO";
    }
}
