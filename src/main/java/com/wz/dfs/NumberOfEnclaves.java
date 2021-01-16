package com.wz.dfs;

/**
 * Given a 2D array A, each cell is 0 (representing sea) or 1 (representing land)
 * A move consists of walking from one land square 4-directionally to another land square, or off the boundary of the grid.
 * Return the number of land squares in the grid for which we cannot walk off the boundary of the grid in any number of moves.
 *
 * Example 1:
 * Input: [ [0,0,0,0],
 *          [1,0,1,0],
 *          [0,1,1,0],
 *          [0,0,0,0]]
 * Output: 3
 * Explanation:
 * There are three 1s that are enclosed by 0s, and one 1 that isn't enclosed because its on the boundary.
 *
 * Example 2:
 * Input: [ [0,1,1,0],
 *          [0,0,1,0],
 *          [0,0,1,0],
 *          [0,0,0,0]]
 * Output: 0
 * Explanation:
 * All 1s are either on the boundary or can reach the boundary.
 *
 * Note:
 * 1. 1 <= A.length <= 500
 * 2. 1 <= A[i].length <= 500
 * 3. 0 <= A[i][j] <= 1
 * 4. All rows have the same size.
 */
public class NumberOfEnclaves {
    public static void main(String[] args) {
        int[][] A = new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0},
        };
        System.out.println(numEnclaves(A));
    }

    /**
     * 与 {@link NumberOfIslands} 类似
     * 遍历四个边，将与边相连的 1 设置为 0，然后再遍历数组统计其中 1 的个数
     */
    public static int numEnclaves(int[][] A) {
        int m = A.length, n = A[0].length;
        // 第一列和最后一列
        for (int i = 0; i < m; i++) {
            dfs(A, i, 0);
            dfs(A, i, n - 1);
        }
        // 第一行和最后一行
        for (int j = 0; j < n; j++) {
            dfs(A, 0, j);
            dfs(A, m - 1, j);
        }

        int result = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (A[i][j] == 1) {
                    result++;
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] A, int i, int j) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length || A[i][j] != 1) {
            return;
        }
        A[i][j] = 0;
        dfs(A, i - 1, j);
        dfs(A, i + 1, j);
        dfs(A, i, j - 1);
        dfs(A, i, j + 1);
    }
}
