package com.wz.dynamicprogramming;

/**
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.
 *
 * Example 1:
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * Explanation:
 * @link ../../../../resource/OutOfBoundaryPaths.jpg
 *
 * Note:
 * 1. Once you move the ball out of boundary, you cannot move it back.
 * 2. The length and height of the grid is in range [1,50].
 * 3. N is in range [0,50].
 */
public class OutOfBoundaryPaths {
    public static void main(String[] args) {
        System.out.println(findPaths(2, 2, 2, 0, 0));
    }

    /**
     * dp[k][i][j] 表示总共走 k 步，从 (i,j) 位置走出边界的总路径数
     * 对于 dp[k][i][j]，走 k 步出边界的总路径数等于其周围四个位置的走 k-1 步出边界的总路径数之和
     * 如果周围某个位置已经出边界了，那么就直接加上 1，否则就在 dp 数组中找出该值
     */
    public static int findPaths(int m, int n, int N, int i, int j) {
        int mod = 1000000007;
        int[][][] dp = new int[N + 1][m][n];
        for (int k = 1; k < N + 1; k++) {
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    long left = (x == 0) ? 1 : dp[k - 1][x - 1][y];
                    long right = (x == m - 1) ? 1 : dp[k - 1][x + 1][y];
                    long up = (y == 0) ? 1 : dp[k - 1][x][y - 1];
                    long down = (y == n - 1) ? 1 : dp[k - 1][x][y + 1];
                    dp[k][x][y] = (int) ((left + right + up + down) % mod);
                }
            }
        }
        return dp[N][i][j];
    }
}
