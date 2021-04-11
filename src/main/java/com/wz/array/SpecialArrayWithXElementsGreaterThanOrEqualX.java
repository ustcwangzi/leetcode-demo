package com.wz.array;

import java.util.Arrays;

/**
 * You are given an array nums of non-negative integers. nums is considered special if there exists a number x
 * such that there are exactly x numbers in nums that are greater than or equal to x.
 * Notice that x does not have to be an element in nums.
 * Return x if the array is special, otherwise, return -1. It can be proven that if nums is special, the value for x is unique.
 *
 * Example 1:
 * Input: nums = [3,5]
 * Output: 2
 * Explanation: There are 2 values (3 and 5) that are greater than or equal to 2.
 *
 * Example 2:
 * Input: nums = [0,0]
 * Output: -1
 * Explanation: No numbers fit the criteria for x.
 * If x = 0, there should be 0 numbers >= x, but there are 2.
 * If x = 1, there should be 1 number >= x, but there are 0.
 * If x = 2, there should be 2 numbers >= x, but there are 0.
 * x cannot be greater since there are only 2 numbers in nums.
 *
 * Example 3:
 * Input: nums = [0,4,3,0,4]
 * Output: 3
 * Explanation: There are 3 values that are greater than or equal to 3.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. 0 <= nums[i] <= 1000
 */
public class SpecialArrayWithXElementsGreaterThanOrEqualX {
    public static void main(String[] args) {
        System.out.println(specialArray(new int[]{3, 5}));
        System.out.println(specialArray(new int[]{0, 4, 3, 0, 4}));
    }

    /**
     * 找出 X，满足数组中存在 X 个元素大于等于 X
     * 先找到数组的最大值 max，X 的可能值为 0～max，遍历每一种可能，然后使用 count 统计数组中大于等于 X 的元素个数
     */
    public static int specialArray(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();

        for (int i = 0; i <= max; i++) {
            int count = 0;
            for (int num : nums) {
                // 统计满足条件的元素个数
                if (num >= i) {
                    count++;
                }
            }

            if (count == i) {
                return i;
            }
        }
        return -1;
    }
}
