package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.
 * To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.
 * However, you will lose points if you pick a cell too far from the cell that you picked in the previous row.
 * For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
 * Return the maximum number of points you can achieve.
 * abs(x) is defined as:
 * 1. x for x >= 0.
 * 2. -x for x < 0.
 *
 * Example 1:
 * @see ../../../../resource/MaximumNumberOfPointsWithCost1.jpg
 * Input: points = [[1,2,3],[1,5,1],[3,1,1]]
 * Output: 9
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 2), (1, 1), and (2, 0).
 * You add 3 + 5 + 3 = 11 to your score.
 * However, you must subtract abs(2 - 1) + abs(1 - 0) = 2 from your score.
 * Your final score is 11 - 2 = 9.
 *
 * Example 2:
 * @see ../../../../resource/MaximumNumberOfPointsWithCost2.jpg
 * Input: points = [[1,5],[2,3],[4,2]]
 * Output: 11
 * Explanation:
 * The blue cells denote the optimal cells to pick, which have coordinates (0, 1), (1, 1), and (2, 0).
 * You add 5 + 3 + 4 = 12 to your score.
 * However, you must subtract abs(1 - 1) + abs(1 - 0) = 1 from your score.
 * Your final score is 12 - 1 = 11.
 *
 * Constraints:
 * 1. m == points.length
 * 2. n == points[r].length
 * 3. 1 <= m, n <= 10^5
 * 4. 1 <= m * n <= 10^5
 * 5. 0 <= points[r][c] <= 10^5
 */
public class MaximumNumberOfPointsWithCost {
    public static void main(String[] args) {
        System.out.println(maxPoints(new int[][]{{1, 2, 3}, {1, 5, 1}, {3, 1, 1}}));
    }

    /**
     * dp[i] 表示当前行第 i 个格子被选中时的最大得分，若上一行对应位置的总分为 last[j]，则 dp[i] = max{last[j] - abs{i-j}} + points[line][i]
     * 为简化每个格子最高分数的求解，可以预处理计算各个位置选取上一行的左、右侧格子所能得到的最高分
     */
    public static long maxPoints(int[][] points) {
        int m = points.length, n = points[0].length;
        long[] dp = new long[n];
        for (int j = 0; j < n; j++) {
            dp[j] = points[0][j];
        }

        for (int i = 1; i < m; i++) {
            // 复制上一行的最大得分
            long[] last = dp.clone();
            // 预处理计算各个位置选取上一行的左、右侧格子所能得到的最高分
            long[] left = new long[n], right = new long[n];
            left[0] = last[0];
            // 正上方的格子统计在左侧中
            for (int j = 1; j < n; j++) {
                left[j] = Math.max(left[j - 1] - 1, last[j]);
            }
            for (int j = n - 2; j >= 0; j--) {
                right[j] = Math.max(right[j + 1] - 1, last[j + 1] - 1);
            }
            for (int j = 0; j < n; j++) {
                dp[j] = Math.max(left[j], right[j]) + points[i][j];
            }
        }
        return Arrays.stream(dp).max().getAsLong();
    }
}
