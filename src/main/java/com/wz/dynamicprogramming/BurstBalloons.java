package com.wz.dynamicprogramming;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 * 1. You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 2. 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 *
 * Example:
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */
public class BurstBalloons {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));
    }

    /**
     * dp[i, j] 表示打爆从 i 到 j 所有气球能得到的最大积分
     * 每一次在 [i, j] 这个区间中选择一个 k，代表该区间中最后一个被打爆的气球
     * 注意，需要先遍历完所有长度为 1 的区间，再是长度为 2 的区间，再依次累加长度，直到最后才遍历整个区间
     */
    public static int maxCoins(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                dp[i][j] = Integer.MIN_VALUE;
                // k 是最后一个扎破的气球
                for (int k = i; k <= j; k++) {
                    int left = i == 0 ? 1 : nums[i - 1];
                    int right = j == n - 1 ? 1 : nums[j + 1];
                    int leftSum = k == i ? 0 : dp[i][k - 1];
                    int rightSum = k == j ? 0 : dp[k + 1][j];
                    dp[i][j] = Math.max(dp[i][j], left * nums[k] * right + leftSum + rightSum);
                }
            }
        }
        return dp[0][n - 1];
    }
}
