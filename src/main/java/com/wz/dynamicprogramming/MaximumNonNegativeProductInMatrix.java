package com.wz.dynamicprogramming;

/**
 * You are given a rows x cols matrix grid. Initially, you are located at the top-left corner (0, 0), and in each step,
 * you can only move right or down in the matrix.
 * Among all possible paths starting from the top-left corner (0, 0) and ending in the bottom-right corner (rows - 1, cols - 1),
 * find the path with the maximum non-negative product. The product of a path is the product of all integers in the grid cells visited along the path.
 * Return the maximum non-negative product modulo 109 + 7. If the maximum product is negative return -1.
 * Notice that the modulo is performed after getting the maximum product.
 *
 * Example 1:
 * Input: grid = [[-1,-2,-3],
 *                [-2,-3,-3],
 *                [-3,-3,-2]]
 * Output: -1
 * Explanation: It's not possible to get non-negative product in the path from (0, 0) to (2, 2), so return -1.
 *
 * Example 2:
 * Input: grid = [[1,-2,1],
 *                [1,-2,1],
 *                [3,-4,1]]
 * Output: 8
 * Explanation: Maximum non-negative product is in bold (1 * 1 * -2 * -4 * 1 = 8).
 *
 * Example 3:
 * Input: grid = [[1, 3],
 *                [0,-4]]
 * Output: 0
 * Explanation: Maximum non-negative product is in bold (1 * 0 * -4 = 0).
 *
 * Constraints:
 * 1. 1 <= rows, cols <= 15
 * 2. -4 <= grid[i][j] <= 4
 */
public class MaximumNonNegativeProductInMatrix {
    public static void main(String[] args) {
        System.out.println(maxProductPath(new int[][]{
                {1, -2, 1},
                {1, -2, 1},
                {3, -4, 1},
        }));
    }

    /**
     * dp[i][j] 表示到达 grid[i][j] 时得到的最大乘积和最小乘积
     * 因为存在负数，所以每步的结果可能用到上一步的最大乘积或最小乘积
     * 可以从 (i,j-1) 或 (i-1,j) 达到 (i,j)，分别将这两种情况产生的乘积进行计算即可
     */
    public static int maxProductPath(int[][] grid) {
        int m = grid.length, n = grid[0].length, mod = 1000000007;
        Pair[][] dp = new Pair[m][n];
        dp[0][0] = new Pair(grid[0][0], grid[0][0]);
        // 第一列
        for (int i = 1; i < m; i++) {
            long value = grid[i][0] * dp[i - 1][0].min;
            dp[i][0] = new Pair(value, value);
        }
        // 第一行
        for (int j = 1; j < n; j++) {
            long value = grid[0][j] * dp[0][j - 1].min;
            dp[0][j] = new Pair(value, value);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                long left1 = grid[i][j] * dp[i][j - 1].min, left2 = grid[i][j] * dp[i][j - 1].max;
                long top1 = grid[i][j] * dp[i - 1][j].min, top2 = grid[i][j] * dp[i - 1][j].max;
                long min = Math.min(left1, Math.min(left2, Math.min(top1, top2)));
                long max = Math.max(left1, Math.max(left2, Math.max(top1, top2)));
                dp[i][j] = new Pair(min, max);
            }
        }
        return dp[m - 1][n - 1].max >= 0 ? (int) (dp[m - 1][n - 1].max % mod) : -1;
    }

    static class Pair {
        long min;
        long max;

        public Pair(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }
}
