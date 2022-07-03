package com.wz.bfs;

import java.util.*;

/**
 * Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of it if they exist
 * (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.
 * Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
 * A binary matrix is a matrix with all cells equal to 0 or 1 only.
 * A zero matrix is a matrix with all cells equal to 0.
 *
 * Example 1:
 * @link ../../../../resource/MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix.jpg
 * Input: mat = [[0,0],[0,1]]
 * Output: 3
 * Explanation: One possible solution is to flip (1, 0) then (0, 1) and finally (1, 1) as shown.
 *
 * Example 2:
 * Input: mat = [[0]]
 * Output: 0
 * Explanation: Given matrix is a zero matrix. We do not need to change it.
 *
 * Example 3:
 * Input: mat = [[1,0,0],[1,0,0]]
 * Output: -1
 * Explanation: Given matrix cannot be a zero matrix.
 *
 * Constraints:
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n <= 3
 * 4. mat[i][j] is either 0 or 1.
 */
public class MinimumNumberOfFlipsToConvertBinaryMatrixToZeroMatrix {
    public static void main(String[] args) {
        System.out.println(minFlips(new int[][]{{0, 0}, {0, 1}}));
        System.out.println(minFlips(new int[][]{{1, 0, 0}, {1, 0, 0}}));
    }

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 将 matrix 当前状态存入队列中，然后针对每种状态进行 BFS
     * BFS 过程中，尝试将每个位置及其四个邻居进行反转，然后再次加入队列中进行下一轮
     */
    public static int minFlips(int[][] mat) {
        int m = mat.length, n = mat[0].length, step = 0;
        Queue<int[][]> queue = new LinkedList<>();
        queue.offer(mat);
        Set<String> visited = new HashSet<>();
        visited.add(toString(mat));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[][] cur = queue.poll();
                if (isZeroMatrix(cur)) {
                    return step;
                }

                // 将每个位置及其四个邻居进行反转
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        int[][] copy = deepCopy(cur);
                        copy[i][j] = 1 - copy[i][j];
                        for (int[] dir : DIRS) {
                            int x = i + dir[0], y = j + dir[1];
                            if (x < 0 || x >= m || y < 0 || y >= n) {
                                continue;
                            }
                            copy[x][y] = 1 - copy[x][y];
                        }

                        if (visited.add(toString(copy))) {
                            queue.offer(copy);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private static int[][] deepCopy(int[][] matrix) {
        int[][] copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }

        return copy;
    }

    private static boolean isZeroMatrix(int[][] matrix) {
        for (int[] array : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (array[j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String toString(int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for (int[] array : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                builder.append(array[j]);
            }
        }
        return builder.toString();
    }
}
