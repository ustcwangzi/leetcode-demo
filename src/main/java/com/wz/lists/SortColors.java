package com.wz.lists;

import java.util.Arrays;

/**
 * Given an array with n objects colored red, white or blue,
 * sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * <p>
 * Note: You are not suppose to use the library's sort function for this problem.
 * Could you come up with a one-pass algorithm using only constant space?
 * <p>
 * Example:
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 */
public class SortColors {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 维护两个指针redIndex和blueIndex，redIndex之前放置红色，blueIndex之后放置蓝色，保留白色在中间
     */
    public static void sortColors(int[] nums) {
        int redIndex = 0, blueIndex = nums.length - 1;
        for (int i = 0; i <= blueIndex; i++) {
            if (nums[i] == 0) {
                swap(nums, i, redIndex++);
            } else if (nums[i] == 2) {
                swap(nums, i, blueIndex--);
                i--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
