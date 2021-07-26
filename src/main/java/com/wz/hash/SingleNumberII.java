package com.wz.hash;

/**
 * Given an integer array nums where every element appears three times except for one, which appears exactly once. Find the single element and return it.
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 * Example 1:
 * Input: nums = [2,2,3,2]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [0,1,0,1,0,1,99]
 * Output: 99
 *
 * Constraints:
 * 1. 1 <= nums.length <= 3 * 10^4
 * 2. -2^31 <= nums[i] <= 2^31 - 1
 * 3. Each element in nums appears exactly three times except for one element which appears once.
 */
public class SingleNumberII {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 3, 2}));
    }

    /**
     * 三个相同的数相加，不仅其和能被 3 整除，其和二进制位上的每一位也能被 3 整除，因此只需要记录每一位累加的结果即可
     * 如 [3,3,3,1,2,2,2] 转换成二进制为 [11,11,11,01,10,10,10]，在第 1 位上，1 出现 4 次，第 2 位上，1 出现 6 次
     * 那么把每一位上 1 的个数 mod 3 剩下的 1 就是结果，4 mod 3 = 1; 6 mod 3 = 0，剩下的二进制位为 01
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            // 统计每一位上 1 的个数
            int sum = 0;
            for (int num : nums) {
                sum += (num >> i) & 1;
            }
            // 依次对 3 取余
            result |= (sum % 3) << i;
        }
        return result;
    }
}
