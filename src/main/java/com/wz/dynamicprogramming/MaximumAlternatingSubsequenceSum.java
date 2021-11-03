package com.wz.dynamicprogramming;

/**
 * The alternating sum of a 0-indexed array is defined as the sum of the elements at even indices minus the sum of the elements at odd indices.
 * For example, the alternating sum of [4,2,5,3] is (4 + 5) - (2 + 3) = 4.
 * Given an array nums, return the maximum alternating sum of any subsequence of nums (after reindexing the elements of the subsequence).
 * A subsequence of an array is a new array generated from the original array by deleting some elements (possibly none)
 * without changing the remaining elements' relative order. For example, [2,7,4] is a subsequence of [4,2,3,7,2,1,4] (the underlined elements), while [2,4,2] is not.
 *
 * Example 1:
 * Input: nums = [4,2,5,3]
 * Output: 7
 * Explanation: It is optimal to choose the subsequence [4,2,5] with alternating sum (4 + 5) - 2 = 7.
 *
 * Example 2:
 * Input: nums = [5,6,7,8]
 * Output: 8
 * Explanation: It is optimal to choose the subsequence [8] with alternating sum 8.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^5
 */
public class MaximumAlternatingSubsequenceSum {
    public static void main(String[] args) {
        System.out.println(maxAlternatingSum(new int[]{4, 2, 5, 3}));
        System.out.println(maxAlternatingSum(new int[]{5, 6, 7, 8}));
    }

    /**
     * dp[i] 表示 索引 i 处能得到的最大和交替和。
     * dp[i][0] 表示以 i 结尾且长度为奇数的最大交替和
     * dp[i][1] 表示以 i 结尾且长度为偶数的最大交替和
     * 分两种情况讨论：
     * 1.以 i 结尾且长度为奇数的最大交替和 dp[i][0]：
     *   不用当前元素，为 dp[i-1][0]
     *   用当前元素直接作为新的子序列 nums[i]
     *   接在上一个偶数序列后，追加当前元素 dp[i-1][1] + nums[i])
     * 2.以 i 结尾且长度为偶数的最大交替和 dp[i][1]：
     *   不用当前元素，为 dp[i-1][1]
     *   接在上一个奇数序列后，减去当前元素 dp[i-1][0] - nums[i])
     * 因此
     * dp[i][0] = Math.max(dp[i-1][0], Math.max(nums[i], dp[i-1][1] + nums[i]));
     * dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - nums[i]);
     * 由于 nums[i]>0 所以取奇数列的值一定大于偶数列，最后返回 dp[n-1][0] 即可
     * 初始值为第一个元素作为奇数数列，而一个数无法为偶数长度数列，所以奇数情况为最小值初始化：
     * dp[0][0] = nums[0]; dp[0][1] = Integer.MIN_VALUE;
     */
    public static long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][2];
        dp[0][0] = nums[0];
        dp[0][1] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(nums[i], dp[i - 1][1] + nums[i]));
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - nums[i]);
        }
        return dp[n - 1][0];
    }
}
