import java.util.Arrays;
import java.util.HashSet;

public class TheMoviesLevelOneDivOne {
    public long find(int n, int m, int[] row, int[] seat) {
        long result = 0;
        long preRow = -1, preSeat = -1;
        int N = row.length;
        Seat[] seats = new Seat[N];
        HashSet<Integer> rows = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
            seats[i] = new Seat(row[i], seat[i]);
            rows.add(row[i]);
        }
        Arrays.sort(seats);
        for (int i = 0; i < N; i++) {
            if (seats[i].row == preRow) {
                long num = seats[i].seat - preSeat - 2;
                result += (num > 0 ? num : 0);
            }
            else {
                long num = seats[i].seat - 2;
                result += (num > 0 ? num : 0);
            }
            if ((i + 1 < N && seats[i + 1].row != seats[i].row) || (i == N - 1)) {
                long num = m - seats[i].seat - 1;
                result += (num > 0 ? num : 0);
            }
            preRow = seats[i].row;
            preSeat = seats[i].seat;
        }
        result += (n - rows.size()) * (long) (m - 1);
        return result;
    }
}

class Seat implements Comparable<Seat> {
    long row;
    long seat;

    public Seat(long row, long seat) {
        super();
        this.row = row;
        this.seat = seat;
    }

    @Override
    public int compareTo(Seat seat) {
        if (this.row == seat.row)
            return this.seat < seat.seat ? -1 : 1;
        return this.row < seat.row ? -1 : 1;
    }
}
