package com.wz.lists;

import java.util.Arrays;

/**
 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: 6
 *
 * Example 2:
 * Input: [1,2,3,4]
 * Output: 24
 */
public class MaximumProductOfThreeNumbers {
    public static void main(String[] args) {
        int[] nums = new int[]{-3, -1, 1, 2};
        System.out.println(maximumProduct(nums));
    }

    /**
     * 先排序，因为可能存在负数，因此最大的乘积可能是最后的三个数相乘或最小的两个数乘以最后一个数
     */
    public static int maximumProduct(int[] nums) {
        if (nums.length < 3) {
            return 0;
        }

        Arrays.parallelSort(nums);
        int n = nums.length;
        return Math.max(nums[n - 1] * nums[n - 2] * nums[n - 3], nums[n - 1] * nums[1] * nums[0]);
    }
}
