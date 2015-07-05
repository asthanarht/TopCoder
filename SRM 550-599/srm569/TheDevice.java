public class TheDevice {
    public int minimumAdditional(String[] plates) {
        int result = 0;
        for (int i = 0; i < plates[0].length(); i++) {
            int count0 = 0, count1 = 0;
            for (int j = 0; j < plates.length; j++)
                if (plates[j].charAt(i) == '0')
                    count0++;
                else
                    count1++;
            int need = 0;
            if (count0 < 1)
                need += 1;
            if (count1 < 2)
                need += 2 - count1;
            result = Math.max(result, need);
        }
        return result;
    }
}
