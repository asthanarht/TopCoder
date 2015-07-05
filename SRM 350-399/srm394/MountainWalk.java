public class MountainWalk {
    public int cellsVisited(String[] areaMap, int heightDifference) {
        boolean[][] visited = new boolean[areaMap.length][areaMap[0].length()];
        visited[0][0] = true;
        int count = 1;
        int x = 0, y = 0;
        while (true) {
            if (x + 1 < areaMap.length
                    && !visited[x + 1][y]
                    && Math.abs(areaMap[x + 1].charAt(y) - areaMap[x].charAt(y)) <= heightDifference) {
                x++;
                count++;
                visited[x][y] = true;
                continue;
            }
            else if (y - 1 >= 0
                    && !visited[x][y - 1]
                    && Math.abs(areaMap[x].charAt(y - 1) - areaMap[x].charAt(y)) <= heightDifference) {
                y--;
                count++;
                visited[x][y] = true;
                continue;
            }
            else if (x - 1 >= 0
                    && !visited[x - 1][y]
                    && Math.abs(areaMap[x - 1].charAt(y) - areaMap[x].charAt(y)) <= heightDifference) {
                x--;
                count++;
                visited[x][y] = true;
                continue;
            }
            else if (y + 1 < areaMap[x].length()
                    && !visited[x][y + 1]
                    && Math.abs(areaMap[x].charAt(y + 1) - areaMap[x].charAt(y)) <= heightDifference) {
                y++;
                count++;
                visited[x][y] = true;
                continue;
            }
            break;
        }
        return count;
    }
}
