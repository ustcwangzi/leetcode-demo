package com.wz.array;

/**
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 * Input: [3,0,1]
 * Output: 2
 *
 * Example 2:
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 */
public class MissingNumber {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 0, 1};
        System.out.println(missingNumber(nums));

        nums = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumber(nums));
    }

    /**
     * 思路与{@link FirstMissingPositive}类似，把nums[i]放到nums[nums[i]]上
     */
    public static int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 0 && nums[i] <= nums.length - 1 && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
