import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class EllysJuice {
    int n;
    HashSet<String> win = new HashSet<String>();
    ArrayList<Player> drink = new ArrayList<Player>();
    HashMap<String, Player> all = new HashMap<String, Player>();
    boolean[] tick;
    String[] ps;

    public String[] getWinners(String[] players) {
        n = players.length;
        ps = players;
        for (int i = 0; i < n; i++)
            if (all.get(players[i]) == null) {
                Player play = new Player(players[i]);
                drink.add(play);
                all.put(players[i], play);
            }
        tick = new boolean[n];
        dfs(n);
        String[] winner = new String[win.size()];
        int i = 0;
        for (String name : win)
            winner[i++] = name;
        Arrays.sort(winner);
        return winner;
    }

    private void dfs(int total) {
        if (total == 0) {
            double max = 0;
            for (Player player : drink)
                if (player.drink > max)
                    max = player.drink;
            int count = 0;
            for (Player player : drink)
                if (player.drink == max)
                    count++;
            if (count > 1)
                return;
            for (Player player : drink)
                if (player.drink == max) {
                    win.add(player.name);
                    return;
                }
        }
        for (int i = 0; i < n; i++)
            if (!tick[i]) {
                tick[i] = true;
                Player player = all.get(ps[i]);
                double drink = player.drink;
                double newdrink = drink + 1.0
                        / Math.pow(2, 1 + (n - total) / 2);
                player.setDrink(newdrink);
                dfs(total - 1);
                player.setDrink(drink);
                tick[i] = false;
            }
    }
}

class Player {
    public String name;
    public double drink = 0;

    public Player(String name) {
        super();
        this.name = name;
    }

    public double getDrink() {
        return drink;
    }

    public void setDrink(double drink) {
        this.drink = drink;
    }
}
