import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Test {
	private static final int CASEMIN = 1;
	private static final int CASEMAX = 100;
	private static final int DISTRIBUTION_INTERVAL = 1000;
	private static final String LOC = "E:/workspace/TopCoder/bin/";
	private static final String BATCH = "test.bat";
	private static final String SCORE = "score.txt";
	private static final String REPORT = "report.txt";

	public static void main(String[] args) throws Exception {
		PrintWriter report = new PrintWriter(new BufferedWriter(new FileWriter(
				LOC + REPORT)));

		runTests(report);
		readScore(report);
		readTime(report);
		logScore(report);
		logDistribution(report);

		report.close();
	}

	private static void runTests(PrintWriter report) throws Exception {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(LOC
				+ BATCH)));
		pw.println("cd E:/workspace/TopCoder/bin");
		for (int seed = CASEMIN; seed <= CASEMAX; seed++)
			pw.println("java PegJumpVis -exec \"java PegJumping\" -novis -seed "
					+ seed + " >> " + SCORE);
		pw.println("exit");
		pw.close();
		pw = new PrintWriter(new BufferedWriter(new FileWriter(LOC + SCORE)));
		pw.close();
		Process p = Runtime.getRuntime().exec(
				"cmd /c start /wait " + LOC + BATCH);

		int exitVal = p.waitFor();
		log("Exited with error code " + exitVal, report);
	}

	private static void readScore(PrintWriter pw) throws Exception {
		Scanner scan = new Scanner(new BufferedReader(new FileReader(LOC
				+ SCORE)));
		int total = 0, best = 0, bestSeed = 0, worst = Integer.MAX_VALUE, worstSeed = 0;
		HashMap<Integer, ArrayList<Integer>> distribution = new HashMap<Integer, ArrayList<Integer>>();
		String line;
		for (int seed = CASEMIN; seed <= CASEMAX; seed++) {
			line = scan.nextLine();
			while (!line.startsWith("Score"))
				line = scan.nextLine();
			line = line.substring(line.indexOf('=') + 1).trim();
			int score = Integer.parseInt(line);
			v[seed - 1] = score;
			total += score;
			if (score > best) {
				best = score;
				bestSeed = seed;
			}
			if (score < worst) {
				worst = score;
				worstSeed = seed;
			}
			if (!distribution.containsKey(score / DISTRIBUTION_INTERVAL))
				distribution.put(score / DISTRIBUTION_INTERVAL,
						new ArrayList<Integer>());
			distribution.get(score / DISTRIBUTION_INTERVAL).add(seed);
		}
		scan.close();

		log("\n\nTotal raw score " + total, pw);
		log("\nAverage " + (total * 1.0 / (CASEMAX - CASEMIN + 1)), pw);
		log("\nBest " + best + " -seed " + bestSeed, pw);
		log("\nWorst " + worst + " -seed " + worstSeed, pw);
	}

	private static void readTime(PrintWriter pw) throws Exception {
		Scanner scan = new Scanner(new BufferedReader(new FileReader(LOC
				+ SCORE)));
		int time = 0, tseed = 0;
		String line;
		for (int seed = CASEMIN; seed <= CASEMAX; seed++) {
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
		System.out.printf("\nTime %.3fs -seed %d\n", time * 0.001, tseed);
		pw.printf("\nTime %.3fs -seed %d\n", time * 0.001, tseed);
	}

	private static void logScore(PrintWriter pw) {
		log("\n", pw);
		for (int i = CASEMIN; i <= CASEMAX; i++)
			best[i - CASEMIN] = Math.max(best[i - CASEMIN], v[i - CASEMIN]);

		System.out.printf("baseline {%5d", v01[0]);
		pw.printf("baseline {%5d", v01[0]);
		for (int i = 1; i <= CASEMAX - CASEMIN; i++) {
			System.out.printf(", %5d", v01[i]);
			pw.printf(", %5d", v01[i]);
		}
		System.out.println("}");
		pw.println("}");

		System.out.printf("thistime {%5.0f", compare(v[0], v01[0]));
		pw.printf("thistime {%5.0f", compare(v[0], v01[0]));
		for (int i = 1; i <= CASEMAX - CASEMIN; i++) {
			System.out.printf(", %5.0f", compare(v[i], v01[i]));
			pw.printf(", %5.0f", compare(v[i], v01[i]));
		}
		System.out.println("}");
		pw.println("}");

		System.out.printf("thistime {%5d", v[0]);
		pw.printf("thistime {%5d", v[0]);
		for (int i = 1; i <= CASEMAX - CASEMIN; i++) {
			System.out.printf(", %5d", v[i]);
			pw.printf(", %5d", v[i]);
		}
		System.out.println("}");
		pw.println("}");

		System.out.printf("thistime {%5.0f", compare(v[0], best[0]));
		pw.printf("thistime {%5.0f", compare(v[0], best[0]));
		for (int i = 1; i <= CASEMAX - CASEMIN; i++) {
			System.out.printf(", %5.0f", compare(v[i], best[i]));
			pw.printf(", %5.0f", compare(v[i], best[i]));
		}
		System.out.println("}");
		pw.println("}");

		System.out.printf("bestever {%5d", best[0]);
		pw.printf("bestever {%5d", best[0]);
		for (int i = 1; i <= CASEMAX - CASEMIN; i++) {
			System.out.printf(", %5d", best[i]);
			pw.printf(", %5d", best[i]);
		}
		System.out.println("}");
		pw.println("}");

		log("\n", pw);
		double sum = 0;
		for (int i = CASEMIN; i <= CASEMAX; i++)
			sum += compare(v[i - CASEMIN], v01[i - CASEMIN]);
		System.out.printf(
				"average total improvement over base version : %.2f%%\n", sum
						/ (CASEMAX - CASEMIN + 1));
		pw.printf("average total improvement over base version : %.2f%%\n", sum
				/ (CASEMAX - CASEMIN + 1));

		sum = 0;
		for (int i = CASEMIN; i <= CASEMAX; i++)
			sum += compare(v[i - CASEMIN], best[i - CASEMIN]);
		System.out.printf(
				"average total improvement over best version : %.2f%%\n", sum
						/ (CASEMAX - CASEMIN + 1));
		pw.printf("average total improvement over best version : %.2f%%\n", sum
				/ (CASEMAX - CASEMIN + 1));
		log("\n", pw);
	}

	private static void logDistribution(PrintWriter pw) {
		HashMap<Integer, ArrayList<Integer>> distribution = new HashMap<Integer, ArrayList<Integer>>();
		for (int seed = CASEMIN; seed <= CASEMAX; seed++) {
			if (!distribution.containsKey(v[seed - 1] / DISTRIBUTION_INTERVAL))
				distribution.put(v[seed - 1] / DISTRIBUTION_INTERVAL,
						new ArrayList<Integer>());
			distribution.get(v[seed - 1] / DISTRIBUTION_INTERVAL).add(seed);
		}

		int seg = distribution.keySet().size();
		int partition = 0;
		for (int key = 0, cnt = 0; cnt < seg; key++)
			if (distribution.containsKey(key)) {
				cnt++;
				log((key * DISTRIBUTION_INTERVAL) + " - "
						+ ((key + 1) * DISTRIBUTION_INTERVAL) + " ("
						+ distribution.get(key).size() + ") :", pw);
				for (int s : distribution.get(key))
					log(s + " ", pw);
				log("\n", pw);
				partition += distribution.get(key).size();
				if (partition << 1 >= CASEMAX) {
					log(" == 50% " + partition + "/" + CASEMAX + " == \n", pw);
					partition -= CASEMAX;
				}
			}
	}

	private static void log(String message, PrintWriter pw) {
		System.out.print(message);
		pw.print(message);
	}

	private static double compare(double score, double com) {
		if (score > com) {
			double p = (score - com) / com * 100;
			return p;
		} else {
			double p = (com - score) / com * 100;
			return -p;
		}

	}

	private static int[] v01 = { 2904, 4876, 1991, 13636, 20192, 28173, 4114,
			17370, 18473, 1103, 11247, 1289, 2268, 4818, 6447, 21258, 30023,
			5272, 7909, 20432, 17978, 8478, 4861, 9404, 9310, 31493, 7320,
			16960, 21580, 14573, 50504, 8627, 16280, 5837, 3640, 24108, 38212,
			32662, 14436, 4493, 7258, 1663, 5610, 4534, 20455, 42413, 21180,
			7935, 2496, 3649, 4078, 4944, 1595, 7161, 22596, 37962, 29893,
			16274, 9592, 16288, 15455, 5393, 44770, 4650, 7910, 3360, 23502,
			37547, 2139, 7614, 6605, 14756, 6515, 28271, 4945, 1187, 814, 1103,
			27983, 5601, 13436, 23883, 23792, 16950, 6080, 3760, 12803, 15433,
			55055, 12067, 4186, 10531, 6811, 13190, 31220, 25236, 5560, 6309,
			13870, 39295 };
	private static int[] v = new int[CASEMAX - CASEMIN + 1];
	private static int[] best = { 2977, 4876, 1991, 14779, 20772, 28398, 4215,
			17894, 18473, 1103, 11247, 1386, 2268, 4855, 6863, 21258, 30023,
			5272, 7909, 20432, 18093, 8478, 5057, 9404, 9310, 31671, 7320,
			16960, 21580, 15121, 50504, 8627, 16280, 6075, 3745, 24674, 38719,
			32662, 14644, 4573, 7258, 1765, 5610, 4567, 20455, 42875, 21267,
			7935, 2724, 3649, 4190, 4944, 1693, 7169, 22596, 37962, 29893,
			16742, 9748, 16950, 15455, 5393, 45212, 4765, 7910, 3598, 24028,
			37547, 2177, 7614, 6605, 14949, 6564, 28271, 5117, 1271, 836, 1103,
			27983, 5678, 13436, 24010, 24338, 16950, 6166, 3760, 12803, 15433,
			55055, 12067, 4186, 10531, 6811, 13578, 31777, 25236, 5560, 6684,
			14083, 39403 };
}
