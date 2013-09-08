import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

interface Const {
    // turn this on to output given and predicted coordinates
    public static final boolean DEBUG = false;
    // turn this on to output starting time of each part
    public static final boolean CHECK_TIME = false;

    public static final int N_DIMENSION = 10;
    public static final int BATCH_SIZE = 450;
    public static final float INIT_LEARNING_RATE = 2e-7f;
    // public static final long TIME_LIMIT = 4100; // on server
    public static final long TIME_LIMIT = 9700;
    public static final int SMALL = 450;
}

/**
 * 1. Gradient descent based on order.
 * 2. Mini-Batch gradient descent based on order for small cases.
 * 
 * 1. Works better for big number of points than small number of points.
 * 
 * raw score 0.0 | 0.0 | 0.0 | 0.0 | 0.0 | 0.0 | 0.0 | 0.0 | 0.0 | 0.0
 * 
 * @author Betty
 * 
 */
public class TheUniverseUnravels implements Const {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int nCoords = Integer.parseInt(in.nextLine());
        String[] coords = new String[nCoords];
        for (int i = 0; i < nCoords; i++)
            coords[i] = in.nextLine();

        int nRanks = Integer.parseInt(in.nextLine());
        String[] ranks = new String[nRanks];
        for (int i = 0; i < nRanks; i++)
            ranks[i] = in.nextLine();

        int nMinDist = Integer.parseInt(in.nextLine());
        int[] minDist = new int[nMinDist];
        for (int i = 0; i < nMinDist; i++)
            minDist[i] = Integer.parseInt(in.nextLine());

        int nMaxDist = Integer.parseInt(in.nextLine());
        int[] maxDist = new int[nMaxDist];
        for (int i = 0; i < nMaxDist; i++)
            maxDist[i] = Integer.parseInt(in.nextLine());

        in.close();

        String[] res = predictCoordinates(coords, ranks, minDist, maxDist);
        System.out.println(res.length);
        for (int i = 0; i < res.length; i++)
            System.out.println(res[i]);
        System.out.flush();
    }

    public static String[] predictCoordinates(String[] coords, String[] ranks,
            int[] minDist, int[] maxDist) {
        long heapSize = Runtime.getRuntime().maxMemory();
        System.err.println("heapSize : " + heapSize);

        Data.startTime = System.currentTimeMillis();

        Data.nPoints = coords.length;
        System.err.println("nPoints : " + Data.nPoints);

        // parse coords
        // given coordinates of points
        Data.points = new int[Data.nPoints][N_DIMENSION];
        for (int i = 0; i < Data.nPoints; i++) {
            String[] ints = coords[i].split(" ");
            for (int j = 0; j < N_DIMENSION; j++)
                Data.points[i][j] = Integer.parseInt(ints[j]);
        }

        // parse ranks
        // number of points inside [center point id][some other point id]
        Data.nInCircle = new int[Data.nPoints][Data.nPoints];
        for (int i = 0; i < Data.nPoints; i++) {
            String[] info = ranks[i].split(" ");
            for (int j = 0; j < Data.nPoints; j++)
                Data.nInCircle[i][j] = Integer.parseInt(info[j]);
        }

        initPredict();
        initOrder();

        initGDOrder();
        if (Data.nPoints < SMALL) {
            gdOrderSmall();
            Data.cast();
            initMiniBGDOrder();
            miniBGDOrder();
        }
        else {
            gdOrder();
            Data.cast();
        }

        if (DEBUG)
            Check.printPoints();

        // prepare result
        Data.startTime = System.currentTimeMillis();
        String[] result = new String[Data.nPoints];

        for (int i = 0; i < Data.nPoints; i++)
            result[i] = Data.toString(i);
        if (CHECK_TIME)
            System.err.println("result prepare time : "
                    + (System.currentTimeMillis() - Data.startTime));
        return result;
    }

    /**
     * Initializes predicted coordinates of points
     */
    private static void initPredict() {
        if (CHECK_TIME)
            System.err.println("initPredict() time : "
                    + (System.currentTimeMillis() - Data.startTime));
        Data.pointsPred = new float[Data.nPoints][N_DIMENSION];
        Data.pointsPredClone = new float[Data.nPoints][N_DIMENSION];
        Data.unknown = new int[Data.nPoints][N_DIMENSION];
        Data.nUnknown = new int[Data.nPoints];
        for (int i = 0; i < Data.nPoints; i++)
            for (int j = 0; j < N_DIMENSION; j++)
                if (Data.points[i][j] == -1) {
                    Data.pointsPred[i][j] = 500;
                    Data.unknown[i][Data.nUnknown[i]++] = j;
                }
                else
                    Data.pointsPred[i][j] = Data.points[i][j];
    }

    /**
     * Orders points for each center point according to rank[center][pid]
     */
    private static void initOrder() {
        if (CHECK_TIME)
            System.err.println("initOrder() time : "
                    + (System.currentTimeMillis() - Data.startTime));
        Data.order = new int[Data.nPoints][Data.nPoints];
        HashMap<Integer, ArrayList<Integer>> helper;
        for (int i = 0; i < Data.nPoints; i++) {
            helper = new HashMap<Integer, ArrayList<Integer>>();
            for (int j = 0; j < Data.nPoints; j++) {
                int key = Data.nInCircle[i][j];
                ArrayList<Integer> list = helper.get(key);
                if (list == null) {
                    list = new ArrayList<Integer>();
                    helper.put(key, list);
                }
                list.add(j);
            }
            for (int key = 0, order = 0; key < Data.nPoints; key++) {
                ArrayList<Integer> list = helper.get(key);
                if (list == null)
                    continue;
                for (Integer pid : list)
                    Data.order[i][order++] = pid;
            }
        }
    }

    private static void initGDOrder() {
        if (CHECK_TIME)
            System.err.println("initGDOrder() time : "
                    + (System.currentTimeMillis() - Data.startTime));
        Data.examples = new ArrayList<TrainingExample>();
        for (int i = 0; i < Data.nPoints; i++)
            for (int j = 3; j < Data.nPoints - 1; j++) {
                int closer = Data.order[i][j - 1];
                int farther = Data.order[i][j];
                Data.examples.add(new TrainingExampleOrder(closer, farther, i));
            }
    }

    private static void gdOrder() {
        if (CHECK_TIME)
            System.err.println("gdOrder() time : "
                    + (System.currentTimeMillis() - Data.startTime));
        float minError = Float.MAX_VALUE;
        long maxTime = 0;
        long time = System.currentTimeMillis();
        for (float learningRate = INIT_LEARNING_RATE; time + maxTime < Data.startTime
                + TIME_LIMIT;) {
            Data.copy();
            float error = 0;
            for (TrainingExample example : Data.examples) {
                example.calculateGradient();
                example.descent(learningRate);
                error += example.getError();
            }
            if (error < minError)
                minError = error;
            else
                Data.restore();
            if (CHECK_TIME)
                System.err.println("time : " + (time - Data.startTime)
                        + " error : " + error + " learningRate : "
                        + learningRate);

            long time2 = System.currentTimeMillis();
            maxTime = time2 - time;
            time = time2;
        }
    }

    private static void gdOrderSmall() {
        if (CHECK_TIME)
            System.err.println("gdOrder() time : "
                    + (System.currentTimeMillis() - Data.startTime));
        float minError = Float.MAX_VALUE;
        long maxTime = 0;
        long time = System.currentTimeMillis();
        for (float learningRate = INIT_LEARNING_RATE; time + maxTime < Data.startTime
                + TIME_LIMIT / 2;) {
            Data.copy();
            float error = 0;
            Collections.shuffle(Data.examples);
            for (TrainingExample example : Data.examples) {
                example.calculateGradient();
                example.descent(learningRate);
                error += example.getError();
            }
            if (error < minError)
                minError = error;
            else {
                learningRate = INIT_LEARNING_RATE * 1.1f;
                Data.restore();
            }
            if (CHECK_TIME)
                System.err.println("time : " + (time - Data.startTime)
                        + " error : " + error + " learningRate : "
                        + learningRate);

            long time2 = System.currentTimeMillis();
            maxTime = time2 - time;
            time = time2;
        }
        if (CHECK_TIME) {
            float error = 0;
            for (TrainingExample example : Data.examples) {
                example.calculateGradient();
                error += example.getError();
            }
            System.err.println("final error : " + error);
        }
    }

    private static void initMiniBGDOrder() {
        if (CHECK_TIME)
            System.err.println("initMiniBGDOrder() time : "
                    + (System.currentTimeMillis() - Data.startTime));
        Data.examples = new ArrayList<TrainingExample>();
        for (int i = 0; i < Data.nPoints; i++)
            Data.examples.add(new TrainingExampleCenter(i));
    }

    private static void miniBGDOrder() {
        if (CHECK_TIME)
            System.err.println("miniBGDOrder() time : "
                    + (System.currentTimeMillis() - Data.startTime));
        float minError = Float.MAX_VALUE;
        long maxTime = 0;
        long time = System.currentTimeMillis();
        for (float learningRate = INIT_LEARNING_RATE; time + maxTime < Data.startTime
                + TIME_LIMIT;) {
            float error = 0;
            Collections.shuffle(Data.examples);
            for (TrainingExample example : Data.examples) {
                example.calculateGradient();
                example.descent(learningRate);
                error += example.getError();
            }
            if (error < minError)
                minError = error;
            if (CHECK_TIME)
                System.err.println("time : " + (time - Data.startTime)
                        + " error : " + error + " learningRate : "
                        + learningRate);

            long time2 = System.currentTimeMillis();
            maxTime = time2 - time;
            time = time2;
        }
    }
}

class TrainingExampleOrder extends TrainingExample {
    protected int pid1, pid2, center;
    protected boolean inRange = false;
    protected float error;
    protected float[] gradient1 = new float[N_DIMENSION];
    protected float[] gradient2 = new float[N_DIMENSION];

    public TrainingExampleOrder(int pid1, int pid2, int center) {
        this.pid1 = pid1;
        this.pid2 = pid2;
        this.center = center;
    }

    @Override
    public void calculateGradient() {
        float disP1 = Data.dis2(center, pid1);
        float disP2 = Data.dis2(center, pid2);
        error = disP1 - disP2;
        if (disP1 < disP2) {
            inRange = true;
            return;
        }
        else
            inRange = false;
        for (int i = 0; i < Data.nUnknown[pid1]; i++) {
            int j = Data.unknown[pid1][i];
            gradient1[j] = (Data.pointsPred[pid1][j] - Data.pointsPred[center][j])
                    * error;
        }
        for (int i = 0; i < Data.nUnknown[pid2]; i++) {
            int j = Data.unknown[pid2][i];
            gradient2[j] = (Data.pointsPred[pid2][j] - Data.pointsPred[center][j])
                    * error;
        }
    }

    @Override
    public float getError() {
        return error > 0 ? error : 0;
    }

    @Override
    public void descent(float learningRate) {
        if (inRange)
            return;
        for (int i = 0; i < Data.nUnknown[pid1]; i++) {
            int j = Data.unknown[pid1][i];
            gradient1[j] *= learningRate;
            Data.pointsPred[pid1][j] -= gradient1[j];
            if (Data.pointsPred[pid1][j] < 0)
                Data.pointsPred[pid1][j] = 0;
            if (Data.pointsPred[pid1][j] > 1000)
                Data.pointsPred[pid1][j] = 1000;
        }
        for (int i = 0; i < Data.nUnknown[pid2]; i++) {
            int j = Data.unknown[pid2][i];
            gradient2[j] *= learningRate;
            Data.pointsPred[pid2][j] += gradient2[j];
            if (Data.pointsPred[pid2][j] < 0)
                Data.pointsPred[pid2][j] = 0;
            if (Data.pointsPred[pid2][j] > 1000)
                Data.pointsPred[pid2][j] = 1000;
        }
    }
}

class TrainingExampleCenter extends TrainingExample {
    protected int center;
    protected boolean inRange = false;
    protected int error;
    protected int[] gradient = new int[N_DIMENSION];

    public TrainingExampleCenter(int center) {
        this.center = center;
    }

    @Override
    public void calculateGradient() {
        int size = Math.min(Data.nPoints, BATCH_SIZE);
        int[] dis = new int[size];
        for (int i = 1; i < size; i++)
            dis[i] = Data.dis2int(center, Data.order[center][i]);
        error = 0;
        for (int i = 2; i < size; i++) {
            if (Data.nInCircle[center][Data.order[center][i - 1]] == Data.nInCircle[center][Data.order[center][i]])
                error += (dis[i - 1] == dis[i] ? 0 : 1);
            else
                error += (dis[i - 1] < dis[i] ? 0 : 1);
        }
        if (error == 0) {
            inRange = true;
            return;
        }
        else
            inRange = false;

        for (int i = 0; i < Data.nUnknown[center]; i++) {
            int j = Data.unknown[center][i];
            int x = Data.pointsInt[center][j];
            int xL = Math.max(0, x - 1);
            int xU = Math.min(1000, x + 1);
            int[] disL = new int[size];
            int[] disU = new int[size];
            System.arraycopy(dis, 0, disL, 0, size);
            System.arraycopy(dis, 0, disU, 0, size);
            for (int k = 1; k < size; k++) {
                int pidk = Data.order[center][k];
                int xk = Data.pointsInt[pidk][j];
                int diff = x - xk;
                int diffL = xL - xk;
                int diffU = xU - xk;
                disL[k] -= diff * diff;
                disL[k] += diffL * diffL;
                disU[k] -= diff * diff;
                disU[k] += diffU * diffU;
            }

            int errorL = 0;
            int errorU = 0;
            for (int k = 2; k < size; k++) {
                if (Data.nInCircle[center][Data.order[center][k - 1]] == Data.nInCircle[center][Data.order[center][k]]) {
                    errorL += (disL[k - 1] == disL[k] ? 0 : 1);
                    errorU += (disU[k - 1] == disU[k] ? 0 : 1);
                }
                else {
                    errorL += (disL[k - 1] < disL[k] ? 0 : 1);
                    errorU += (disU[k - 1] < disU[k] ? 0 : 1);
                }
            }

            if (errorL <= error / 2 && error <= errorU)
                gradient[j] = xL - x;
            else if (errorU <= error / 2 && error <= errorL)
                gradient[j] = xU - x;
            else
                gradient[j] = 0;
        }
    }

    @Override
    public float getError() {
        return error;
    }

    @Override
    public void descent(float learningRate) {
        if (inRange)
            return;
        for (int i = 0; i < Data.nUnknown[center]; i++) {
            int j = Data.unknown[center][i];
            Data.pointsInt[center][j] += gradient[j];
        }
    }
}

abstract class TrainingExample implements Const {
    abstract public void calculateGradient();

    abstract public float getError();

    abstract public void descent(float learningRate);
}

class Data implements Const {
    protected static int nPoints;
    // number of points strictly inside the circle(i, dis(i, j))
    protected static int[][] nInCircle;
    // point id [center point id][kth nn]
    protected static int[][] order;
    protected static int[][] points;
    protected static float[][] pointsPred;
    protected static float[][] pointsPredClone;
    protected static int[][] pointsInt;
    protected static int[][] unknown;
    protected static int[] nUnknown;
    protected static ArrayList<TrainingExample> examples;
    protected static long startTime;

    protected static float dis2(int p1, int p2) {
        float dis = 0;
        for (int i = 0; i < N_DIMENSION; i++) {
            float diff = pointsPred[p1][i] - pointsPred[p2][i];
            dis += diff * diff;
        }
        return dis;
    }

    protected static int dis2int(int p1, int p2) {
        int dis = 0;
        for (int i = 0; i < N_DIMENSION; i++) {
            int diff = pointsInt[p1][i] - pointsInt[p2][i];
            dis += diff * diff;
        }
        return dis;
    }

    protected static String toString_given(int pid) {
        String coords = "";
        for (int i = 0; i < N_DIMENSION; i++) {
            if (i > 0)
                coords += ' ';
            coords += points[pid][i];
        }
        return coords;
    }

    protected static String toString(int pid) {
        String coords = "";
        for (int i = 0; i < N_DIMENSION; i++) {
            if (i > 0)
                coords += ' ';
            coords += pointsInt[pid][i];
        }
        return coords;
    }

    protected static void copy() {
        for (int i = 0; i < nPoints; i++)
            System.arraycopy(pointsPred, 0, pointsPredClone, 0, N_DIMENSION);
    }

    protected static void restore() {
        for (int i = 0; i < nPoints; i++)
            System.arraycopy(pointsPredClone, 0, pointsPred, 0, N_DIMENSION);
    }

    protected static void cast() {
        pointsInt = new int[nPoints][N_DIMENSION];
        for (int i = 0; i < nPoints; i++)
            for (int j = 0; j < N_DIMENSION; j++)
                pointsInt[i][j] = Math.round(pointsPred[i][j]);
    }
}

class Check implements Const {
    protected static void printPoints() {
        PrintWriter points_given = null;
        try {
            points_given = new PrintWriter(new BufferedWriter(new FileWriter(
                    "points_given.txt")));
            for (int i = 0; i < Data.nPoints; i++)
                points_given.println(Data.toString_given(i));
            points_given.close();
        }
        catch (IOException e) {
        }

        PrintWriter points_pred = null;
        try {
            points_pred = new PrintWriter(new BufferedWriter(new FileWriter(
                    "points_pred.txt")));
            for (int i = 0; i < Data.nPoints; i++)
                points_pred.println(Data.toString(i));
            points_pred.close();
        }
        catch (IOException e) {
        }
    }
}