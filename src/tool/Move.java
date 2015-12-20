package tool;

import java.io.File;
import java.util.Scanner;

public class Move {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        move();
    }

    private static void move() {
        System.out.print(" enter srm number : ");
        String line = in.nextLine();
        if (line.length() == 0) {
            System.out.print(" enter path \"***/***\" : ");
            line = "./" + in.nextLine();
            if (line.length() == 0)
                return;
        }
        else {
            int n = Integer.parseInt(line);
            int a = n / 100, b = n % 100;
            line = "./SRM " + a;
            if (b < 50)
                line += "00-" + a + "49/srm" + n;
            else
                line += "50-" + a + "99/srm" + n;
        }
        in.close();

        File dir = new File(line);
        if (!dir.exists())
            dir.mkdirs();

        dir = new File("src/");
        for (File file : dir.listFiles())
            if (file.getName().endsWith(".java")) {
                String name = line + "/" + file.getName();
                file.renameTo(new File(name));
            }
    }

}