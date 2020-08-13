package com.wz.array;

/**
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 * How many possible unique paths are there?
 * <p>
 * Example 1:
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * <p>
 * Example 2:
 * Input: m = 7, n = 3
 * Output: 28
 */
public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
        System.out.println(uniquePaths(7, 3));
    }

    /**
     * 动态规划
     * dp[i][j]记录在到达每个位置的不同路径数，则 dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 第一行
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
