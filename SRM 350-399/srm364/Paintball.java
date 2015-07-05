import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Paintball {
    public String[] getLeaderboard(String[] players, String[] messages) {
        HashMap<String, Team> teamMap = new HashMap<String, Team>();
        ArrayList<Team> teams = new ArrayList<Team>();
        HashMap<String, Player> playerMap = new HashMap<String, Player>();
        for (int i = 0; i < players.length; i++) {
            String[] info = players[i].split(" ");
            Player p = new Player(info[0]);
            Team team = teamMap.get(info[1]);
            if (team == null) {
                team = new Team(info[1]);
                teamMap.put(info[1], team);
                teams.add(team);
            }
            p.setTeam(team);
            team.addPlayer(p);
            playerMap.put(info[0], p);
        }
        for (int i = 0; i < messages.length; i++) {
            String[] info = messages[i].split(" ");
            Player p1 = playerMap.get(info[0]);
            Player p2 = playerMap.get(info[2]);
            if (p1.team.name.equals(p2.team.name))
                p1.minus();
            else {
                p1.add();
                p2.minus();
            }
        }
        Team[] ts = new Team[teams.size()];
        for (int i = 0; i < teams.size(); i++)
            ts[i] = teams.get(i);
        Arrays.sort(ts);
        String[] res = new String[teams.size() + players.length];
        for (int i = 0, cur = 0; i < ts.length; i++) {
            String[] tres = ts[i].toStrings();
            for (int j = 0; j < tres.length; j++)
                res[cur++] = tres[j];
        }
        return res;
    }
}

class Team implements Comparable<Object> {
    String name;
    int score = 0;
    ArrayList<Player> players = new ArrayList<Player>();

    public Team(String name) {
        super();
        this.name = name;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void add() {
        score++;
    }

    public void minus() {
        score--;
    }

    public int compareTo(Object o) {
        Team other = (Team) o;
        if (score != other.score)
            return other.score - score;
        return name.compareTo(other.name);
    }

    public String[] toStrings() {
        String[] res = new String[players.size() + 1];
        res[0] = name + " " + score;
        Player[] ps = new Player[players.size()];
        for (int i = 0; i < players.size(); i++)
            ps[i] = players.get(i);
        Arrays.sort(ps);
        for (int i = 0; i < ps.length; i++)
            res[i + 1] = ps[i].toString();
        return res;
    }
}

class Player implements Comparable<Object> {
    String name;
    int score = 0;
    Team team;

    public Player(String name) {
        super();
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void add() {
        score++;
        team.add();
    }

    public void minus() {
        score--;
        team.minus();
    }

    public int compareTo(Object arg0) {
        Player other = (Player) arg0;
        if (score != other.score)
            return other.score - score;
        return name.compareTo(other.name);
    }

    public String toString() {
        return "  " + name + " " + score;
    }
}