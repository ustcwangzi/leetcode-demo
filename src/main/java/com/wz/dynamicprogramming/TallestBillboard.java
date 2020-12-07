package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * You are installing a billboard and want it to have the largest height. The billboard will have two steel supports,
 * one on each side.  Each steel support must be an equal height.
 * You have a collection of rods which can be welded together.  For example, if you have rods of lengths 1, 2, and 3,
 * you can weld them together to make a support of length 6.
 * Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.
 *
 * Example 1:
 * Input: [1,2,3,6]
 * Output: 6
 * Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
 *
 * Example 2:
 * Input: [1,2,3,4,5,6]
 * Output: 10
 * Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
 *
 * Note:
 * 1. 0 <= rods.length <= 20
 * 2. 1 <= rods[i] <= 1000
 * 3. The sum of rods is at most 5000.
 */
public class TallestBillboard {
    public static void main(String[] args) {
        System.out.println(tallestBillboard(new int[]{1, 2, 3, 4, 5, 6}));
    }

    /**
     * 状态 dp[i][j] 表示考虑了前 i 个钢筋，搭建的两个钢制支架差距为 j 时，较低的支架的最大高度是多少
     * 初始化 dp[i][j]=Integer.MIN_VALUE，dp[0][0]=0
     * 1. 如果不用第 i 个钢筋，则 dp[i][j] = max{dp[i][j], dp[i-1][j]}
     * 2. 如果使用了第 i 个钢筋，高度为 x，并将它放到了较高的支架上，则差距会扩大 x，即 dp[i][j+x] = max{dp[i][j+x], dp[i-1][j]}
     * 3. 若放到了较低的支架上，并且 x 小于等于差距 j，则 dp[i][j-x] = max{dp[i][j-x], dp[i-1][j]+x}
     * 4. 否则 dp[i][x-j] = max{dp[i][x-j], dp[i-1][j]+j}
     * 最终答案为 dp[n][0]
     */
    public static int tallestBillboard(int[] rods) {
        int n = rods.length, sum = 0;
        for (int num : rods) {
            sum += num;
        }
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum - rods[i - 1]; j++) {
                // not use i
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                // put on higher part
                dp[i][j + rods[i - 1]] = Math.max(dp[i][j + rods[i - 1]], dp[i - 1][j]);
                // put on lower part
                dp[i][Math.abs(j - rods[i - 1])] = Math.max(dp[i][Math.abs(j - rods[i - 1])], dp[i - 1][j] + Math.min(j, rods[i - 1]));
            }
        }
        return dp[n][0];
    }
}
