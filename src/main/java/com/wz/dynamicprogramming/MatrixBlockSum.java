package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a m * n matrix mat and an integer K, return a matrix answer where each answer[i][j] is the sum of all elements
 * mat[r][c] for i - K <= r <= i + K, j - K <= c <= j + K, and (r, c) is a valid position in the matrix.
 *
 * Example 1:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 1
 * Output: [[12,21,16],[27,45,33],[24,39,28]]
 *
 * Example 2:
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]], K = 2
 * Output: [[45,45,45],[45,45,45],[45,45,45]]
 *
 * Constraints:
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n, K <= 100
 * 4. 1 <= mat[i][j] <= 100
 */
public class MatrixBlockSum {
    public static void main(String[] args) {
        int[][] result = matrixBlockSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }, 1);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 先计算矩阵前缀和，多加个一行一列方便处理边界，然后根据每个点计算它的左上边界和右下边界再计算结果
     */
    public static int[][] matrixBlockSum(int[][] mat, int K) {
        int m = mat.length, n = mat[0].length;
        // 矩阵前缀和
        int[][] preSum = new int[m + 1][n + 1];
        int[][] dp = new int[m][n];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + mat[i - 1][j - 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                int left = Math.max(j - K - 1, 0);
                int right = Math.min(j + K, n);
                int up = Math.max(i - K - 1, 0);
                int down = Math.min(m, i + K);
                //通过前缀和的组合，得出区域和
                dp[i - 1][j - 1] = preSum[down][right] - preSum[up][right] - preSum[down][left] + preSum[up][left];
            }
        return dp;
    }
}
