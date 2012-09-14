package srm505;

public class SentenceCapitalizerInator {

    public String fixCaps(String paragraph) {

        String s = "";

        String ss = paragraph.substring(0, paragraph.indexOf(".") + 1);
        paragraph = paragraph.substring(paragraph.indexOf(".") + 1);
        String sss = "" + ss.charAt(0);
        s += sss.toUpperCase() + ss.substring(1);

        while (paragraph.contains(".")) {
            ss = paragraph.substring(0, paragraph.indexOf(".") + 1);
            paragraph = paragraph.substring(paragraph.indexOf(".") + 1);
            sss = "" + ss.charAt(1);
            s += " " + sss.toUpperCase() + ss.substring(2);
        }

        return s;

    }

}
