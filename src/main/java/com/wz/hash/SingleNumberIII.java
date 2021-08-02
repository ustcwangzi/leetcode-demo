package com.wz.hash;

import java.util.Arrays;

/**
 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 * Find the two elements that appear only once. You can return the answer in any order.
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 *
 * Example 2:
 * Input: nums = [-1,0]
 * Output: [-1,0]
 *
 * Constraints:
 * 1. 2 <= nums.length <= 3 * 10^4
 * 2. -2^31 <= nums[i] <= 2^31 - 1
 * 3. Each integer in nums will appear twice, only two integers will appear once.
 */
public class SingleNumberIII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
        System.out.println(Arrays.toString(singleNumber(new int[]{-1, 0})));
    }

    /**
     * 仅有两个值是出现一次的，其它都恰好出现两次，因此全部进行异或的结果等于最终答案的异或
     * 然后再找到它们的最小不同比特位来将元素分为两组，如 3 和 5，其二进制分别是 11 和 101，最小不同比特位是 10，异或结果是 110
     * 然后对原数组进行与操作，一部分和 diff 与的结果是 0，另一部分和 diff 与的结果是 1，并且两个答案一定是分别落在两个组中的
     * 这样分别对两组的元素进行异或就是最终答案
     */
    public static int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // 找到最小不同比特位
        diff &= -diff;

        int[] result = new int[2];
        for (int num : nums) {
            // 按 与 的结果将元素分为两组
            if ((num & diff) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        return result;
    }
}
