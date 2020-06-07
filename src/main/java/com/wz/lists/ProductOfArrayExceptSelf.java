package com.wz.lists;

import java.util.Arrays;

/**
 * Given an array nums of n integers where n > 1,  return an array output such that output[i]
 * is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array
 * (including the whole array) fits in a 32 bit integer.
 *
 * Note: Please solve it without division and in O(n).
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }

    /**
     * 对于某一个数字，其前面所有数字的乘积 乘以 后面所有的数乘积，就是我们要的结果
     * 先从左遍历，将乘积的累积存入结果中，然后从右遍历，用到一个临时变量right，初始化为1，每次不断累积，最终得到正确结果
     */
    public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        if (nums.length == 0) {
            return result;
        }
        result[0] = 1;
        // 从左到右计算左边数的乘积
        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // 从右到左计算右边数的乘积
        int right = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }
        return result;
    }
}
