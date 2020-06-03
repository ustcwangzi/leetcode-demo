package com.wz.lists;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 *
 * Example 2:
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, -2, 4};
        System.out.println(maxProduct(nums));

        nums = new int[]{-2, 0, -1};
        System.out.println(maxProduct(nums));

        nums = new int[]{-2, 3, -4};
        System.out.println(maxProduct(nums));
    }

    /**
     * 负数 * 负数得到正数，因此需要保留preMin和preMax
     * 然后curMax在nums[i]、preMin * nums[i]、preMax * nums[i]三者中取最大的
     * 然后curMin在nums[i]、preMin * nums[i]、preMax * nums[i]三者中取最小的
     * 依次遍历，更新最终结果
     */
    public static int maxProduct(int[] nums) {
        if (nums.length <= 1) {
            return nums.length == 0 ? 0 : nums[0];
        }

        int result = nums[0], preMin = nums[0], preMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curMax = Math.max(preMax * nums[i], Math.max(preMin * nums[i], nums[i]));
            int curMin = Math.min(preMin * nums[i], Math.min(preMax * nums[i], nums[i]));
            result = Math.max(result, curMax);
            preMin = curMin;
            preMax = curMax;
        }
        return result;
    }
}
