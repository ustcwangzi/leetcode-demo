package com.wz.dfs;

/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 * From each cell, you can either move in four directions: left, right, up, or down.
 * You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 * Example 1:
 * @link ../../../../resource/LongestIncreasingPathInMatrix1.jpg
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 * @link ../../../../resource/LongestIncreasingPathInMatrix2.jpg
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 * Example 3:
 * Input: matrix = [[1]]
 * Output: 1
 *
 * Constraints:
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= m, n <= 200
 * 4. 0 <= matrix[i][j] <= 2^31 - 1
 */
public class LongestIncreasingPathInMatrix {
    public static void main(String[] args) {
        System.out.println(longestIncreasingPath(new int[][]{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}));
        System.out.println(longestIncreasingPath(new int[][]{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}));
    }

    /**
     * 遍历每个点，对每个点进行 DFS，计算上下左右能够达到的最远距离
     * 注意，要求的是递增序列，因此 DFS 过程中需要记录上一个点的元素值 pre
     * 同时，为了加快计算，使用 visited 记录已经计算过的距离值
     */
    public static int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length, result = 0;
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(matrix, i, j, -1, visited));
            }
        }
        return result;
    }

    private static int dfs(int[][] matrix, int i, int j, int pre, int[][] visited) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= pre) {
            return 0;
        }
        if (visited[i][j] > 0) {
            return visited[i][j];
        }
        int result = 0;
        result = Math.max(result, dfs(matrix, i - 1, j, matrix[i][j], visited));
        result = Math.max(result, dfs(matrix, i + 1, j, matrix[i][j], visited));
        result = Math.max(result, dfs(matrix, i, j - 1, matrix[i][j], visited));
        result = Math.max(result, dfs(matrix, i, j + 1, matrix[i][j], visited));
        return visited[i][j] = result + 1;
    }
}
