public class RowAndCoins {
    public String getWinner(String cells) {
        char first = cells.charAt(0), last = cells.charAt(cells.length() - 1);
        if (first == 'B' && last == 'B')
            return "Bob";
        return "Alice";
    }
}
