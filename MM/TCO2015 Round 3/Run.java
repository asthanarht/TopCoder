import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Run {
	private static final int SEED = 1;
	private static final String LOC = "E:/workspace/TopCoder/bin/";
	private static final String BATCH = "test.bat";

	public static void main(String[] args) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					LOC + BATCH)));
			pw.println("cd E:/workspace/TopCoder/bin");
			pw.println("java PegJumpVis -exec \"java PegJumping\" -pause -seed "
					+ SEED);
			// pw.println("exit");
			pw.close();
			Process p = Runtime.getRuntime().exec(
					"cmd /c start /wait " + LOC + BATCH);

			int exitVal = p.waitFor();
			System.out.println("Exited with error code " + exitVal);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
