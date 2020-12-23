package com.wz.dynamicprogramming;

/**
 * Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such
 * that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
 * A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array,
 * leaving the remaining elements in their original order.
 *
 * Example 1:
 * Input: nums = [10,2,-10,5,20], k = 2
 * Output: 37
 * Explanation: The subsequence is [10, 2, 5, 20].
 *
 * Example 2:
 * Input: nums = [-1,-2,-3], k = 1
 * Output: -1
 * Explanation: The subsequence must be non-empty, so we choose the largest number.
 *
 * Example 3:
 * Input: nums = [10,-2,-10,-5,20], k = 2
 * Output: 23
 * Explanation: The subsequence is [10, -2, -5, 20].
 *
 *
 * Constraints:
 * 1. 1 <= k <= nums.length <= 10^5
 * 2. -10^4 <= nums[i] <= 10^4
 */
public class ConstrainedSubsequenceSum {
    public static void main(String[] args) {
        System.out.println(constrainedSubsetSum(new int[]{10, 2, -10, 5, 20}, 2));
    }

    public static int constrainedSubsetSum(int[] nums, int k) {
        int[] sum = new int[nums.length];
        int maxIndex = 0, result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (i - k - 1 >= maxIndex) {
                maxIndex = i - 1;
                for (int j = 2; j <= k; j++) {
                    if (sum[maxIndex] < sum[i - j]) maxIndex = i - j;
                }
            }
            sum[i] = Math.max(nums[i] + sum[maxIndex], nums[i]);
            if (sum[i] >= sum[maxIndex]) {
                maxIndex = i;
            }
            result = Math.max(result, sum[i]);
        }
        return result;
    }
}
