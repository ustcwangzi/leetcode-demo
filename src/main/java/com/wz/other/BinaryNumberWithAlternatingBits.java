package com.wz.other;

/**
 * Given a positive integer, check whether it has alternating bits: namely, if two adjacent bits will always have different values.
 *
 * Example 1:
 * Input: n = 5
 * Output: true
 * Explanation: The binary representation of 5 is: 101
 *
 * Example 2:
 * Input: n = 7
 * Output: false
 * Explanation: The binary representation of 7 is: 111.
 *
 * Example 3:
 * Input: n = 10
 * Output: true
 * Explanation: The binary representation of 10 is: 1010.
 *
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */
public class BinaryNumberWithAlternatingBits {
    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(5));
        System.out.println(hasAlternatingBits(7));
    }

    /**
     * 通过移位操作逐步获取每一位上的数字
     * 使用 pre 记录上一位数字，cur 记录当前位数字，若两者相等，则直接返回 false
     */
    public static boolean hasAlternatingBits(int n) {
        int pre = n & 1;
        n >>= 1;
        while (n != 0) {
            int cur = n & 1;
            if (cur == pre) {
                return false;
            }
            pre = cur;
            n >>= 1;
        }
        return true;
    }
}
