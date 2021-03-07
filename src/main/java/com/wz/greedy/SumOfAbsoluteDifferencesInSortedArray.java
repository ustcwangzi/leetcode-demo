package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given an integer array nums sorted in non-decreasing order.
 * Build and return an integer array result with the same length as nums such that result[i] is equal to
 * the summation of absolute differences between nums[i] and all the other elements in the array.
 * In other words, result[i] is equal to sum(|nums[i]-nums[j]|) where 0 <= j < nums.length and j != i (0-indexed).
 *
 * Example 1:
 * Input: nums = [2,3,5]
 * Output: [4,3,5]
 * Explanation: Assuming the arrays are 0-indexed, then
 * result[0] = |2-2| + |2-3| + |2-5| = 0 + 1 + 3 = 4,
 * result[1] = |3-2| + |3-3| + |3-5| = 1 + 0 + 2 = 3,
 * result[2] = |5-2| + |5-3| + |5-5| = 3 + 2 + 0 = 5.
 *
 * Constraints:
 * 1. 2 <= nums.length <= 10^5
 * 2. 1 <= nums[i] <= nums[i + 1] <= 10^4
 */
public class SumOfAbsoluteDifferencesInSortedArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getSumAbsoluteDifferences(new int[]{2, 3, 5})));
    }

    /**
     * result[i] = (nums[i] - nums[0]) + (nums[i] - nums[1]) + ... + (nums[i] - nums[i-1])
     *             + (nums[i+1] - nums[i]) + (nums[i+2] - nums[i]) + ... + (nums[n-1] - nums[i])
     *           = nums[i] * i - (nums[0] + nums[1] + ... + nums[i-1])
     *             + (nums[i+1] + nums[i+2] + ... + nums[n-1]) - nums[i] * (n-i-1)
     *           = nums[i] * i - sum[0...i-1] + sum[i+1...n-1] - nums[i] * (n-1-i)
     * 可以使用前缀和快速计算
     * result[i] = nums[i] * i - preSums[i-1] + (preSums[n-1] - preSums[i]) - nums[i] * (n-i-1)
     */
    public static int[] getSumAbsoluteDifferences(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n], result = new int[n];
        preSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = nums[i] + preSum[i - 1];
        }

        for (int i = 0; i < n; i++) {
            result[i] = i * nums[i] - (i > 0 ? preSum[i - 1] : 0) + preSum[n - 1] - preSum[i] - (n - i - 1) * nums[i];
        }
        return result;
    }
}
