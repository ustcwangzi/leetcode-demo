package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Example 1:
 * Input: nums = [7,2,5,10,8], m = 2
 * Output: 18
 * Explanation:
 * There are four ways to split nums into two subarrays.
 * The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 * Example 2:
 * Input: nums = [1,2,3,4,5], m = 2
 * Output: 9
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. 0 <= nums[i] <= 106
 * 3. 1 <= m <= min(50, nums.length)
 */
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{7, 2, 5, 10, 8}, 2));
    }

    /**
     * dp[i][j] 表示将 nums[0...j-1] 分成 i 组所能得到的子数组之和最大值的最小值，初始化为整型最大值
     * 为了快速的算出子数组之和，建立累计和数组 preSum
     * 将前 j 个数字分成 i 组，如果每个数字都是单独一组，那么最多有 j 组，如果将整个数组看为一个整体，那么有 1 组，所以 i 的范围是 [1, j]
     * 需要遍历这中间所有的情况，假如中间任意一个位置 k，dp[i-1][k] 表示前 k 个数字分成 i-1 组所能得到的子数组之和最大值的最小值，
     * 而 preSum[j] - preSum[k] 就是后面的数字之和，取二者之间的较大值，然后和 dp[i][j] 进行对比，更新 dp[i][j] 为二者之中的较小值，
     * 这样 k 在 [1,j] 的范围内扫过一遍，dp[i][j] 就能更新到最小值，最终返回 dp[m][n] 即可
     */
    public static int splitArray(int[] nums, int m) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 遍历所有的分组情况
                for (int k = i - 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i - 1][k], preSum[j] - preSum[k]));
                }
            }
        }
        return dp[m][n];
    }
}
