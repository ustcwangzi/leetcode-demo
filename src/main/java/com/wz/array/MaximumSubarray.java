package com.wz.array;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 * <p>
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray2(nums));
    }

    /**
     * dp[i]代表以nums[i]结尾时的最大累加和
     * 则 dp[i] = Max{dp[i-1] + nums[i], nums[i]}
     */
    public static int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(0, dp[i - 1]) + nums[i];
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 方案一中 dp[i] 仅依赖 dp[i-1]，因此可以去除数组，使用变量 curSum 来记录当前累加和
     */
    public static int maxSubArray2(int[] nums) {
        int curSum = 0, result = Integer.MIN_VALUE;
        for (int num : nums) {
            curSum = Math.max(0, curSum) + num;
            result = Math.max(result, curSum);
        }
        return result;
    }
}
