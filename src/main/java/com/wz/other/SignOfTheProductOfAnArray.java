package com.wz.other;

/**
 * There is a function signFunc(x) that returns:
 * 1. 1 if x is positive.
 * 2. -1 if x is negative.
 * 3. 0 if x is equal to 0.
 * You are given an integer array nums. Let product be the product of all values in the array nums.
 * Return signFunc(product).
 *
 * Example 1:
 * Input: nums = [-1,-2,-3,-4,3,2,1]
 * Output: 1
 * Explanation: The product of all values in the array is 144, and signFunc(144) = 1
 *
 * Example 2:
 * Input: nums = [1,5,0,2,-3]
 * Output: 0
 * Explanation: The product of all values in the array is 0, and signFunc(0) = 0
 *
 * Example 3:
 * Input: nums = [-1,1,-1,1,-1]
 * Output: -1
 * Explanation: The product of all values in the array is -1, and signFunc(-1) = -1
 *
 * Constraints:
 * 1. 1 <= nums.length <= 1000
 * 2. -100 <= nums[i] <= 100
 */
public class SignOfTheProductOfAnArray {
    public static void main(String[] args) {
        System.out.println(arraySign(new int[]{-1, -2, -3, -4, 3, 2, 1}));
        System.out.println(arraySign(new int[]{1, 5, 0, 2, -3}));
        System.out.println(arraySign(new int[]{-1, 1, -1, 1, -1}));
    }

    /**
     * 直接遍历数组，统计负数的个数，遇到 0 直接返回 0，最后判断负数个数是偶数还是奇数，偶数说明最后乘积结果是正数，否则是负数
     */
    public static int arraySign(int[] nums) {
        int negativeCount = 0;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            if (num < 0) {
                negativeCount++;
            }
        }
        return (negativeCount & 1) == 0 ? 1 : -1;
    }
}
