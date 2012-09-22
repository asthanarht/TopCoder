package srm354;

public class DateFormat {
    public String fromEuropeanToUs(String[] dateList) {
        String dl = "";
        for (int i = 0; i < dateList.length; i++)
            dl += dateList[i];
        String[] dates = dl.split(" ");
        int m, d, mpre = 0, dpre = 0;
        String res = "";
        int[] ds = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        for (int i = 0; i < dates.length; i++) {
            if (i != 0)
                res += " ";
            String[] info = dates[i].split("/");
            m = Integer.parseInt(info[0]);
            d = Integer.parseInt(info[1]);
            boolean md = false, dm = false;
            if (m <= 12 && d <= ds[m] && (m > mpre || (m == mpre && d > dpre)))
                md = true;
            if (d <= 12 && m <= ds[d] && (d > mpre || (d == mpre && m > dpre)))
                dm = true;
            if (md && (!dm || m <= d)) {
                String date = String.format("%2d/%2d", m, d).replaceAll(" ",
                        "0");
                res += date;
                mpre = m;
                dpre = d;
            }
            else if (dm && (!md || d <= m)) {
                String date = String.format("%2d/%2d", d, m).replaceAll(" ",
                        "0");
                res += date;
                mpre = d;
                dpre = m;
            }
            else
                return new String();
        }
        return res;
    }
}
