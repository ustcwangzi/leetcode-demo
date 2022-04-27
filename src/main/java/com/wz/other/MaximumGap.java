package com.wz.other;

import java.util.Arrays;

/**
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *
 * Example 1:
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 *
 * Example 2:
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. 0 <= nums[i] <= 10^9
 */
public class MaximumGap {
    public static void main(String[] args) {
        System.out.println(maximumGap(new int[]{3, 6, 9, 1}));
        System.out.println(maximumGap(new int[]{10}));
    }

    /**
     * 排序后遍历，遍历过程中计算相邻元素的差值，并更新结果
     */
    public static int maximumGap(int[] nums) {
        Arrays.parallelSort(nums);
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, nums[i] - nums[i - 1]);
        }
        return result;
    }
}
