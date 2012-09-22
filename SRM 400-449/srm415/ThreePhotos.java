package srm415;

public class ThreePhotos {
    public int removeCubes(String[] A, String[] B, String[] C) {
        int n = A.length;
        boolean cube[][][] = new boolean[n][n][n];
        // initialize a full cube
        for (int x = 0; x < n; x++)
            for (int y = 0; y < n; y++)
                for (int z = 0; z < n; z++)
                    cube[x][y][z] = true;
        // remove which must be removed
        for (int x = 0; x < n; x++)
            for (int y = 0; y < n; y++)
                for (int z = 0; z < n; z++) {
                    if (A[x].charAt(y) == 'N')
                        for (int i = 0; i < n; i++)
                            cube[x][y][i] = false;
                    if (B[x].charAt(z) == 'N')
                        for (int i = 0; i < n; i++)
                            cube[x][i][z] = false;
                    if (C[y].charAt(z) == 'N')
                        for (int i = 0; i < n; i++)
                            cube[i][y][z] = false;
                }
        // check for invalid shade
        for (int x = 0; x < n; x++)
            for (int y = 0; y < n; y++)
                for (int z = 0; z < n; z++) {
                    if (A[x].charAt(y) == 'Y') {
                        boolean found = false;
                        for (int i = 0; i < n; i++)
                            if (cube[x][y][i]) {
                                found = true;
                                break;
                            }
                        if (!found)
                            return -1;
                    }
                    if (B[x].charAt(z) == 'Y') {
                        boolean found = false;
                        for (int i = 0; i < n; i++)
                            if (cube[x][i][z]) {
                                found = true;
                                break;
                            }
                        if (!found)
                            return -1;
                    }
                    if (C[y].charAt(z) == 'Y') {
                        boolean found = false;
                        for (int i = 0; i < n; i++)
                            if (cube[i][y][z]) {
                                found = true;
                                break;
                            }
                        if (!found)
                            return -1;
                    }
                }
        // count removed cells
        int count = 0;
        for (int x = 0; x < n; x++)
            for (int y = 0; y < n; y++)
                for (int z = 0; z < n; z++)
                    if (!cube[x][y][z])
                        count++;
        return count;
    }
}
