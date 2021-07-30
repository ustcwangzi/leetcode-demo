package com.wz.other;

/**
 * Given two integers left and right that represent the range [left, right], return the bitwise AND of all numbers in this range, inclusive.
 *
 * Example 1:
 * Input: left = 5, right = 7
 * Output: 4
 *
 * Example 2:
 * Input: left = 1, right = 2147483647
 * Output: 0
 *
 * Constraints:
 * 0 <= left <= right <= 2^31 - 1
 */
public class BitwiseAndOfNumbersRange {
    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5, 7));
    }

    /**
     * 计算区间 [m,n] 内所有数字与的结果
     * 最后结果是该数字范围内所有数的左边共同部分，如 [26,30]，二进制如下：
     * 11010　　11011　　11100　　11101　　11110
     * 最终结果是 11
     * 直接平移 left 和 right，每次右移一位，直到 left 和 right 相等，记录平移次数 count，最后再把 left 左移 count 位就是结果
     */
    public static int rangeBitwiseAnd(int left, int right) {
        int count = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            ++count;
        }
        return left << count;
    }
}
