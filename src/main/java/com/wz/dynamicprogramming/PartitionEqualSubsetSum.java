package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a non-empty array nums containing only positive integers,
 * find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 *
 * Example 1:
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 * Example 2:
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 200
 * 2. 1 <= nums[i] <= 100
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

    /**
     * 动态规划
     * dp[i] 表示数组中能否取出若干个数字之和为 i，最后只需要返回 dp[target]，target 是数组元素之和的一半，
     * 需要遍历数组中的数字，想办法用数组中的数字凑出 target，因为都是正数，所以只会越加越大，
     * 加上 nums[i] 就有可能会组成区间 [nums[i], target] 中的某个值，那么对于这个区间中的任意一个数字 j，
     * 如果 dp[j-nums[i]] 为 true，说明现在已经可以组成 j-nums[i] 这个数字了，再加上 nums[i]，就可以组成数字 j 了，即 dp[j] = true
     * 因此 dp[j] = dp[j] || dp[j-nums[i]]  (nums[i] <= j <= target)
     * 注意，这里需要从 target 遍历到 nums[i]，而不能反过来，因为 dp[0] 是 true，会导致所有 dp 值都是 true
     */
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return false;
        }

        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum >> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[target];
    }
}
