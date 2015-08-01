package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class ProSol {
    private static String PATH = "./"; // path to codes
    private static String content;
    private static HashSet<String> solved = new HashSet<String>();
    private static HashSet<String> problems = new HashSet<String>();
    private static HashMap<Integer, SRM> srms = new HashMap<Integer, SRM>();
    @SuppressWarnings("unchecked")
    private static HashSet<String>[][] sets = new HashSet[3][4];
    @SuppressWarnings("unchecked")
    private static HashSet<String>[][] total = new HashSet[3][4];

    public static void main(String[] args) {
        long time = 0;
        readlocal();
        for (int d = 1; d <= 2; d++)
            for (int l = 1; l <= 3; l++) {
                sets[d][l] = new HashSet<String>();
                total[d][l] = new HashSet<String>();
                time -= System.currentTimeMillis();
                fetch(d, l);
                time += System.currentTimeMillis();
                read(d, l);
            }
        output();
        System.out.println(time);
    }

    private static void readlocal() {
        File folder = new File(PATH);
        ArrayList<File> files = new ArrayList<File>();
        files.add(folder);

        while (files.size() > 0) {
            File file = files.remove(0);
            if (file.isFile()) {
                String name = file.getName();
                int index = name.indexOf(".java");
                if (index > 0)
                    solved.add(name.substring(0, index));
            }
            else if (file.isDirectory()) {
                for (File f : file.listFiles())
                    files.add(f);
            }
        }
    }

    public static void fetch(int div, int level) {
        content = null;
        URLConnection connection = null;
        try {
            String url = "http://community.topcoder.com/tc?module=ProblemArchive&sr=1&er=10000&div"
                    + div + "l=" + level;
            System.out.println("fetching ... " + url);
            connection = new URL(url).openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String PRO_NAME_PRE = "<A HREF=\"/stat?c=problem_statement";

    public static void read(int div, int level) {
        try (BufferedReader br = new BufferedReader(new StringReader(content))) {
            for (String line; (line = br.readLine()) != null;) {
                for (int index; (index = line.indexOf(PRO_NAME_PRE)) >= 0;) {
                    String proName = br.readLine().trim();
                    problems.add(proName);
                    br.readLine();
                    br.readLine();
                    br.readLine();
                    line = br.readLine();
                    index = line.indexOf("rd");
                    line = line.substring(index + 3);
                    index = line.indexOf("\"");
                    line = line.substring(0, index);
                    int rd = Integer.parseInt(line);
                    String srmName = br.readLine().trim()
                            .replaceAll("&#039;", "");
                    SRM srm = srms.get(rd);
                    if (srm == null)
                        srm = new SRM(rd, srmName);
                    srm.set[div][level] = true;
                    total[div][level].add(proName);
                    if (solved.contains(proName)) {
                        srm.done[div][level] = true;
                        sets[div][level].add(proName);
                    }
                    srms.put(rd, srm);
                }
            }
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void output() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new FileWriter("solved.txt")));
            out.println("  ||       d 2       ||       d 1       ||");
            out.println("  ||  1  |  2  |  3  ||  1  |  2  |  3  ||");
            out.println(String.format(
                    "  ||%5d|%5d|%5d||%5d|%5d|%5d||  solved %d",
                    sets[2][1].size(), sets[2][2].size(), sets[2][3].size(),
                    sets[1][1].size(), sets[1][2].size(), sets[1][3].size(),
                    solved.size()));
            out.println(String.format(
                    "  ||%5d|%5d|%5d||%5d|%5d|%5d||  total %d",
                    total[2][1].size(), total[2][2].size(), total[2][3].size(),
                    total[1][1].size(), total[1][2].size(), total[1][3].size(),
                    problems.size()));
            out.println();

            int n = srms.size();
            SRM[] ss = new SRM[n];
            int i = 0;
            for (SRM s : srms.values())
                ss[i++] = s;
            Arrays.sort(ss);
            for (SRM s : ss)
                out.println(s.toString());
            out.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

class SRM implements Comparable<SRM> {
    int rd;
    String srm;
    boolean[][] set = new boolean[3][4]; // if this problem exists
    boolean[][] done = new boolean[3][4]; // if this problem is solved

    public SRM(int rd, String srm) {
        super();
        this.rd = rd;
        this.srm = srm;
    }

    @Override
    public int compareTo(SRM s) {
        return Integer.compare(s.rd, rd);
    }

    @Override
    public String toString() {
        StringBuffer line = new StringBuffer("  |");
        for (int d = 2; d >= 1; d--) {
            line.append('|');
            for (int l = 1; l <= 3; l++) {
                if (done[d][l])
                    line.append("  1  |");
                else
                    line.append(set[d][l] ? "     |" : "  -  |");
            }
        }
        line.append("|  " + srm);
        return line.toString();
    }
}
