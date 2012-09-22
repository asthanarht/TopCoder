package srm371;

public class SpiralRoute {
    public int[] thronePosition(int width, int length) {
        int[] loc = new int[2]; // {x, y}
        if (width > length) {
            loc[1] = length / 2;
            loc[0] = length % 2 == 0 ? loc[1] - 1 : (width - 1) - loc[1];
        }
        else if (width < length) {
            loc[0] = (width - 1) / 2;
            loc[1] = width % 2 == 0 ? loc[0] + 1 : (length - 1) - loc[0];
        }
        else {
            loc[0] = width % 2 == 0 ? (width - 1) / 2 : width / 2;
            loc[1] = length / 2;
        }
        return loc;
    }
}

// public class SpiralRoute {
// public int[] thronePosition(int width, int length) {
// int total = width * length;
// int[] xChange = { 1, 0, -1, 0 };
// int[] yChange = { 0, 1, 0, -1 };
// boolean[][] grid = new boolean[width][length];
// int[] cur = { 0, 0 }; // current {x,y}
// int direction = 0;
// while (total > 1) {
// // mark current location
// grid[cur[0]][cur[1]] = true;
// total--;
// // check if redirection is needed
// if (cur[0] + xChange[direction] == -1
// || cur[0] + xChange[direction] == width
// || cur[1] + yChange[direction] == -1
// || cur[1] + yChange[direction] == length
// || grid[cur[0] + xChange[direction]][cur[1] + yChange[direction]])
// direction = (direction + 1) % 4;
// // move to next cell
// cur[0] += xChange[direction];
// cur[1] += yChange[direction];
// }
// return cur;
// }
// }
