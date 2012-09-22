package srm429;

public class LinearPolyominoCovering {
    public String findCovering(String region) {
        for (int i = 0; i < region.length(); i++)
            if (region.charAt(i) == 'X')
                if (i + 3 < region.length())
                    if (region.substring(i, i + 4).equals("XXXX"))
                        region = region.replaceFirst("XXXX", "AAAA");
                    else if (region.charAt(i + 1) == 'X')
                        region = region.replaceFirst("XX", "BB");
                    else
                        return "impossible";
                else if (i + 1 < region.length())
                    if (region.charAt(i + 1) == 'X')
                        region = region.replaceFirst("XX", "BB");
                    else
                        return "impossible";
                else
                    return "impossible";
        return region;
    }
}
