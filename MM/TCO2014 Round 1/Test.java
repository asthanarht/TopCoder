import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	private static final int CASE = 100;
	private static final String LOC = "F:/workspace/TopCoder/bin/";
	private static final String BATCH = "test.bat";
	private static final String SCORE = "score.txt";
	private static final String REPORT = "report.txt";

	public static void main(String[] args) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					LOC + BATCH)));
			pw.println("cd F:/workspace/TopCoder/bin");
			for (int seed = 1; seed <= CASE; seed++)
				pw.println("java SquareRemoverVis -exec \"java SquareRemover\" -novis -seed "
						+ seed + " >> " + SCORE);
			pw.println("exit");
			pw.close();
			pw = new PrintWriter(
					new BufferedWriter(new FileWriter(LOC + SCORE)));
			pw.close();
			Process p = Runtime.getRuntime().exec(
					"cmd /c start /wait " + LOC + BATCH);

			int exitVal = p.waitFor();
			System.out.println("Exited with error code " + exitVal);

			Scanner scan = new Scanner(new BufferedReader(new FileReader(LOC
					+ SCORE)));
			int total = 0, best = 0, bestSeed = 0, worst = Integer.MAX_VALUE, worstSeed = 0;
			HashMap<Integer, ArrayList<Integer>> distribution = new HashMap<Integer, ArrayList<Integer>>();
			String line;
			for (int seed = 1; seed <= CASE; seed++) {
				line = scan.nextLine();
				while (!line.startsWith("Score"))
					line = scan.nextLine();
				line = line.substring(line.indexOf('=') + 1).trim();
				int score = Integer.parseInt(line);
				total += score;
				if (score > best) {
					best = score;
					bestSeed = seed;
				}
				if (score < worst) {
					worst = score;
					worstSeed = seed;
				}
				if (!distribution.containsKey(score / 100))
					distribution.put(score / 100, new ArrayList<Integer>());
				distribution.get(score / 100).add(seed);
			}
			scan.close();

			scan = new Scanner(new BufferedReader(new FileReader(LOC + SCORE)));
			int time = 0, tseed = 0;
			for (int seed = 1; seed <= CASE; seed++) {
				line = scan.nextLine();
				while (!line.startsWith("time"))
					line = scan.nextLine();
				line = line.substring(line.indexOf('=') + 1).trim();
				int t = Integer.parseInt(line);
				if (t > time) {
					time = t;
					tseed = seed;
				}
			}
			scan.close();

			PrintWriter report = new PrintWriter(new BufferedWriter(
					new FileWriter(LOC + REPORT)));
			int seg = distribution.keySet().size();
			int partition = 0;
			for (int key = 0, cnt = 0; cnt < seg; key++)
				if (distribution.containsKey(key)) {
					cnt++;
					log((key * 100) + " - " + (key * 100 + 100) + " ("
							+ distribution.get(key).size() + ") :", report);
					for (int s : distribution.get(key))
						log(s + " ", report);
					log("\n", report);
					partition += distribution.get(key).size();
					if (partition << 1 >= CASE) {
						log(" == 50% " + partition + "/" + CASE + " == \n",
								report);
						partition -= CASE;
					}
				}

			log("\nTotal raw score " + total, report);
			log("\nAverage " + (total * 100 / CASE / 100.0), report);
			log("\nBest " + best + " -seed " + bestSeed, report);
			log("\nWorst " + worst + " -seed " + worstSeed, report);

			log("\nTime " + time + " -seed " + tseed, report);

			report.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void log(String message, PrintWriter pw) {
		System.out.print(message);
		pw.print(message);
	}
}
