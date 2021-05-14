package com.wz.greedy;

import java.util.Stack;

/**
 * The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.
 * For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
 * Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums.
 * Since the answer may be large, return it modulo 109 + 7.
 * Note that the min-product should be maximized before performing the modulo operation.
 * Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.
 * A subarray is a contiguous part of an array.
 *
 * Example 1:
 * Input: nums = [1,2,3,2]
 * Output: 14
 * Explanation: The maximum min-product is achieved with the subarray [2,3,2] (minimum value is 2).
 * 2 * (2+3+2) = 2 * 7 = 14.
 *
 * Example 2:
 * Input: nums = [2,3,3,1,2]
 * Output: 18
 * Explanation: The maximum min-product is achieved with the subarray [3,3] (minimum value is 3).
 * 3 * (3+3) = 3 * 6 = 18.
 *
 * Example 3:
 * Input: nums = [3,1,5,6,4,2]
 * Output: 60
 * Explanation: The maximum min-product is achieved with the subarray [5,6,4] (minimum value is 4).
 * 4 * (5+6+4) = 4 * 15 = 60.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= 10^7
 */
public class MaximumSubarrayMinProduct {
    public static void main(String[] args) {
        System.out.println(maxSumMinProduct(new int[]{1, 2, 3, 2}));
        System.out.println(maxSumMinProduct(new int[]{2, 3, 3, 1, 2}));
    }

    /**
     * 单调栈
     * 与 {@link com.wz.array.LargestRectangleInHistogram} 类似，只是本题需要计算区间和，可以使用前缀和进行计算
     */
    public static int maxSumMinProduct(int[] nums) {
        int n = nums.length, mod = 1000000007;
        long[] preSum = new long[n];
        Stack<Integer> stack = new Stack<>();
        long result = 0;
        for (int i = 0; i < n; i++) {
            preSum[i] = i == 0 ? nums[i] : nums[i] + preSum[i - 1];
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                result = Math.max(result, nums[stack.pop()] * (preSum[i - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            result = Math.max(result, nums[stack.pop()] * (preSum[n - 1] - (stack.isEmpty() ? 0 : preSum[stack.peek()])));
        }
        return (int) (result % mod);
    }
}
