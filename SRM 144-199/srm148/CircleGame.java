package srm148;

public class CircleGame {
    private int total = 0;
    private int[] cards;

    public int cardsLeft(String deck) {
        int n = deck.length();
        cards = new int[n];

        for (int i = 0; i < n; i++) {
            switch (deck.charAt(i)) {
                case 'A':
                    cards[total++] = 1;
                    break;
                case '2':
                    cards[total++] = 2;
                    break;
                case '3':
                    cards[total++] = 3;
                    break;
                case '4':
                    cards[total++] = 4;
                    break;
                case '5':
                    cards[total++] = 5;
                    break;
                case '6':
                    cards[total++] = 6;
                    break;
                case '7':
                    cards[total++] = 7;
                    break;
                case '8':
                    cards[total++] = 8;
                    break;
                case '9':
                    cards[total++] = 9;
                    break;
                case 'T':
                    cards[total++] = 10;
                    break;
                case 'J':
                    cards[total++] = 11;
                    break;
                case 'Q':
                    cards[total++] = 12;
                    break;
            }
        }

        boolean found = true;
        while (found) {
            found = false;
            for (int i = 0; i < total - 1; i++) {
                if (cards[i] + cards[i + 1] == 13) {
                    remove(i);
                    found = true;
                    break;
                }
            }
            if (!found && total > 0 && cards[0] + cards[total - 1] == 13) {
                remove2();
                found = true;
            }
        }

        return total;
    }

    private void remove(int n) {
        for (int i = n; i < total - 2; i++)
            cards[i] = cards[i + 2];
        total -= 2;
    }

    private void remove2() {
        for (int i = 0; i < total - 2; i++)
            cards[i] = cards[i + 1];
        total -= 2;
    }
}
