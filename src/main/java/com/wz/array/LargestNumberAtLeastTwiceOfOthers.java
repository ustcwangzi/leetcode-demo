package com.wz.array;

/**
 * In a given integer array nums, there is always exactly one largest element.
 * Find whether the largest element in the array is at least twice as much as every other number in the array.
 * If it is, return the index of the largest element, otherwise return -1.
 *
 * Example 1:
 * Input: nums = [3, 6, 1, 0]
 * Output: 1
 * Explanation: 6 is the largest integer, and for every other number in the array x,
 *              6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.
 *
 * Example 2:
 * Input: nums = [1, 2, 3, 4]
 * Output: -1
 * Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 */
public class LargestNumberAtLeastTwiceOfOthers {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 1, 0};
        System.out.println(dominantIndex(nums));

        nums = new int[]{1, 2, 3, 4};
        System.out.println(dominantIndex(nums));
    }

    /**
     * 直接进行遍历，第一次遍历找到最大值的下标，第二次遍历找到是否存在2倍之后大于最大值的元素
     */
    public static int dominantIndex(int[] nums) {
        int maxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != maxIndex && nums[i] * 2 > nums[maxIndex]) {
                return -1;
            }
        }

        return maxIndex;
    }
}
