import java.util.Arrays;

public class GUMIAndSongsDiv1 {
    private final int INF = 100000000;

    public int maxSongs(int[] duration, int[] tone, int T) {
        int n = duration.length;
        Song[] songs = new Song[n];
        for (int i = 0; i < n; i++)
            songs[i] = new Song(duration[i], tone[i]);
        Arrays.sort(songs);

        int[][] dp = new int[n][n + 1];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                dp[i][j] = INF;
        int max = 0;
        for (int last = 0; last < n; last++) {
            dp[last][1] = songs[last].dur;
            if (dp[last][1] <= T)
                max = Math.max(max, 1);
            for (int done = 1; done <= last + 1; done++)
                for (int next = last + 1; next < n; next++) {
                    int t = dp[last][done] + songs[next].dur + songs[next].tone
                            - songs[last].tone;
                    if (dp[next][done + 1] >= t)
                        dp[next][done + 1] = t;
                    if (t <= T)
                        max = Math.max(max, done + 1);
                }
        }
        return max;
    }
}

class Song implements Comparable<Song> {
    int dur;
    int tone;

    public Song(int dur, int tone) {
        super();
        this.dur = dur;
        this.tone = tone;
    }

    @Override
    public int compareTo(Song s) {
        if (this.tone == s.tone)
            return 0;
        else
            return this.tone < s.tone ? -1 : 1;
    }
}
