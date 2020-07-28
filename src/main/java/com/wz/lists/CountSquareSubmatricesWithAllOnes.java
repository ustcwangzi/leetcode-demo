package com.wz.lists;

import java.util.Arrays;

/**
 * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
 *
 * Example 1:
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 *
 * Example 2:
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 */
public class CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 1, 1, 1}
        };
        System.out.println(countSquares(matrix));

        matrix = new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        };
        System.out.println(countSquares(matrix));
    }

    /**
     * 动态规划
     * dp[i][j]表示以 A[i][j] 作为右下角元素最大的 square submatrices 的个数
     * 1. 如果 A[i][j] == 0, 则 dp[i][j] = 0
     * 2. 如果 A[i][j] == 1, 则 dp[i][j] 的值由 dp[i - 1][j], dp[i - 1][j - 1] 以及 dp[i][j - 1] 中的最小值决定:
     *    dp[i][j] = min{dp[i - 1][j], dp[i - 1][j - 1], dp[i][j - 1]} + 1
     */
    public static int countSquares(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];
        // 第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
        }
        // 第一行
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        int result = 0;
        for (int[] array : dp) {
            result += Arrays.stream(array).sum();
        }
        return result;
    }
}
