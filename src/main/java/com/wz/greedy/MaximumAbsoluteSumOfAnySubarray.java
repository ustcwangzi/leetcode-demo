package com.wz.greedy;

/**
 * You are given an integer array nums. The absolute sum of a subarray [numsl, numsl+1, ..., numsr-1, numsr] is abs(numsl + numsl+1 + ... + numsr-1 + numsr).
 * Return the maximum absolute sum of any (possibly empty) subarray of nums.
 * Note that abs(x) is defined as follows:
 * 1. If x is a negative integer, then abs(x) = -x.
 * 2. If x is a non-negative integer, then abs(x) = x.
 *
 * Example 1:
 * Input: nums = [1,-3,2,3,-4]
 * Output: 5
 * Explanation: The subarray [2,3] has absolute sum = abs(2+3) = abs(5) = 5.
 *
 * Example 2:
 * Input: nums = [2,-5,1,-4,3,-2]
 * Output: 8
 * Explanation: The subarray [-5,1,-4] has absolute sum = abs(-5+1-4) = abs(-8) = 8.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^4 <= nums[i] <= 10^4
 */
public class MaximumAbsoluteSumOfAnySubarray {
    public static void main(String[] args) {
        System.out.println(maxAbsoluteSum(new int[]{1, -3, 2, 3, -4}));
        System.out.println(maxAbsoluteSum(new int[]{2, -5, 1, -4, 3, -2}));
    }

    /**
     * 分别求子数组的最大正数和及最小负数，结果是二者的最大绝对值
     */
    public static int maxAbsoluteSum(int[] nums) {
        int result = 0, min = 0, max = 0;
        for (int num : nums) {
            // max+num小于0时，将结果清空
            max = Math.max(max + num, 0);
            min = Math.min(min + num, 0);
            result = Math.max(result, Math.max(max, Math.abs(min)));
        }
        return result;
    }
}
