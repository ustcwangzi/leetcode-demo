package com.wz.dynamicprogramming;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example:
 * Input:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * Output: 4
 */
public class MaximalSquare {
    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maximalSquare(matrix));
    }

    /**
     * 动态规划
     * dp[i][j] 表示到达 (i,j) 所能组成的最大正方形的边长
     * 第一行和第一列的最大边长是 1，对于其他位置，只有 matrix[i,j] == 1，dp[i][j] 才有可能大于0，否则 dp[i][j] 一定为0
     * 当 matrix[i,j] == 1，此时要看 dp[i-1][j-1], dp[i][j-1] 和 dp[i-1][j] 这三个位置的最小值，并加上1，就是 dp[i][j] 的值
     * 因为不能有 0 存在，所以只能取交集，最后再用 dp[i][j] 的值来更新最大边长即可
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length, n = matrix[0].length;
        int result = 0;
        // 到 (i,j) 所能组成的正方形最大边长
        int[][] dp = new int[m][n];
        // 第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = Character.getNumericValue(matrix[i][0]);
            if (dp[i][0] == 1) {
                result = 1;
            }
        }
        // 第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = Character.getNumericValue(matrix[0][j]);
            if (dp[0][j] == 1) {
                result = 1;
            }
        }
        // 其他位置
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                result = Math.max(result, dp[i][j]);
            }
        }
        return result * result;
    }
}
