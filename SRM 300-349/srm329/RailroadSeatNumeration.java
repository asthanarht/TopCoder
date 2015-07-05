public class RailroadSeatNumeration {
    private int[] to = { 6, 1, 3, 4 };

    public String getInternational(int[] tickets) {
        boolean domestic = true, international = true;
        for (int i = 0; i < tickets.length; i++) {
            if (!validDomestic(tickets[i]))
                domestic = false;
            if (!validInternational(tickets[i]))
                international = false;
        }
        String res = "";
        if (domestic && international)
            res = "AMBIGUOUS";
        else if (!domestic && !international)
            res = "BAD DATA";
        else if (domestic && !international) {
            for (int i = 0; i < tickets.length; i++)
                res += toInternatinal(tickets[i])
                        + (i == tickets.length - 1 ? "" : " ");
        }
        else {
            for (int i = 0; i < tickets.length; i++)
                res += tickets[i] + (i == tickets.length - 1 ? "" : " ");
        }
        return res;
    }

    private boolean validDomestic(int ticket) {
        if (ticket >= 1 && ticket <= 36)
            return true;
        return false;
    }

    private boolean validInternational(int ticket) {
        if (ticket <= 10)
            return false;
        int n1 = ticket / 10;
        int n2 = ticket % 10;
        if (n1 >= 1 && n1 <= 9 && (n2 == 1 || n2 == 3 || n2 == 4 || n2 == 6))
            return true;
        return false;
    }

    private int toInternatinal(int ticket) {
        int n1 = (ticket + 3) / 4;
        int n2 = to[ticket % 4];
        return n1 * 10 + n2;
    }
}
