package com.wz.array;

/**
 * Given an array consisting of n integers, find the contiguous subarray of given length k
 * that has the maximum average value. And you need to output the maximum average value.
 *
 * Example 1:
 * Input: [1,12,-5,-6,50,3], k = 4
 * Output: 12.75
 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
 */
public class MaximumAverageSubarrayI {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 12, -5, -6, 50, 3};
        System.out.println(findMaxAverage(nums, 4));
    }

    /**
     * 用sum[i]存储数组下标0～i的元素之和
     * 则sum[i] - sum[i-k]表示以i为最后一个元素的k个元素之和
     */
    public static double findMaxAverage(int[] nums, int k) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        // 初值为前k个元素之和，不然会漏掉这种情况
        double result = sum[k - 1] * 1.0 / k;
        for (int i = k; i < nums.length; i++) {
            result = Math.max(result, (sum[i] - sum[i - k]) * 1.0 / k);
        }
        return result;
    }
}
