package com.wz.array;

import java.util.Arrays;

/**
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * Example:
 * Input: [0,1,0,12,3]
 * Output: [1,12,3,0,0]
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 12, 3};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 思路与{@link RemoveDuplicates}类似，用size记录非0元素下标
     * 在遍历的过程中找到所有非0的数字，然后将它们依次放在数组之中，最后用0将剩余的位置填满
     */
    public static void moveZeroes(int[] nums) {
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[size++] = nums[i];
            }
        }
        for (int i = size; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
