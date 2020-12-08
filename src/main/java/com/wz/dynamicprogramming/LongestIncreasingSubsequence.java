package com.wz.dynamicprogramming;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 * 1. There may be more than one LIS combination, it is only necessary for you to return the length.
 * 2. Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    /**
     * 动态规划
     * dp[i] 表示以 nums[i] 为结尾的最长递增子串的长度
     * 对于每一个 nums[i]，从 0 搜索到 i，如果发现某个数小于 nums[i]，更新 dp[i] = Math.max(dp[i], dp[j]+1)，加 1 因为需要加上自身
     */
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length, result = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
