package com.wz.lists;

import java.util.Arrays;

/**
 * Given an array consists of non-negative integers, your task is to count the number of triplets
 * chosen from the array that can make triangles if we take them as side lengths of a triangle.
 *
 * Example 1:
 * Input: [2,2,3,4]
 * Output: 3
 * Explanation:
 * Valid combinations are:
 * 2,3,4 (using the first 2)
 * 2,3,4 (using the second 2)
 * 2,2,3
 */
public class ValidTriangleNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 3, 4};
        System.out.println(triangleNumber(nums));
    }

    /**
     * 思路与{@link ThreeSum}类似
     * 组成三角形要求两边之和大于第三边，即三个数字中较小的两个数字之和大于第三个数字
     * 先对数组进行排序，然后依次固定最后一个数，找前两个数
     */
    public static int triangleNumber(int[] nums) {
        Arrays.parallelSort(nums);
        int result = 0;
        // 从后向前遍历，因为要固定三个数中较大的
        for (int i = nums.length - 1; i > 1; i--) {
            if (nums[i] == 0) {
                continue;
            }
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    // 说明left到right之间的那些数均满足条件
                    result += right - left;
                    right--;
                } else {
                    // 不满足条件时，left右移
                    left++;
                }
            }
        }

        return result;
    }
}
