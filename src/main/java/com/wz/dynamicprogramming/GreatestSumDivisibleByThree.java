package com.wz.dynamicprogramming;

/**
 * Given an array nums of integers, we need to find the maximum possible sum of elements of the array such that it is divisible by three.
 *
 * Example 1:
 * Input: nums = [3,6,5,1,8]
 * Output: 18
 * Explanation: Pick numbers 3, 6, 1 and 8 their sum is 18 (maximum sum divisible by 3).
 *
 * Example 2:
 * Input: nums = [4]
 * Output: 0
 * Explanation: Since 4 is not divisible by 3, do not pick any number.
 *
 * Example 3:
 * Input: nums = [1,2,3,4,4]
 * Output: 12
 * Explanation: Pick numbers 1, 3, 4 and 4 their sum is 12 (maximum sum divisible by 3).
 *
 * Constraints:
 * 1. 1 <= nums.length <= 4 * 10^4
 * 2. 1 <= nums[i] <= 10^4
 */
public class GreatestSumDivisibleByThree {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree(nums));
    }

    /**
     * dp[i][k] 表示的是截止到 nums[i] 被 3 整除余数为k的最大和，需要考虑 k=0, k=1, k=2 这三种情况：
     * dp[i][*] = max{dp[i-1][*], dp[i-1][*] + nums[i]}  (* 取值为 0,1,2)
     */
    public static int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][3];
        dp[0][nums[0] % 3] = nums[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = dp[i - 1][j];
            }
            for (int j = 0; j < 3; j++) {
                int curSum = dp[i - 1][j] + nums[i];
                dp[i][curSum % 3] = Math.max(dp[i][curSum % 3], curSum);
            }
        }
        return dp[n - 1][0];
    }
}
