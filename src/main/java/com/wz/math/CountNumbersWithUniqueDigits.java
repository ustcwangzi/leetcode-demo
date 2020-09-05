package com.wz.math;

/**
 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
 *
 * Example:
 * Input: 2
 * Output: 91
 * Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
 *              excluding 11,22,33,44,55,66,77,88,99
 *
 * Constraints:
 * 0 <= n <= 8
 */
public class CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(2));
    }

    /**
     * i == 1: 10, 0...9 共 10 个数，均不重复
     * i == 2: 9 * 9, 第一个位置上除 0 外有9种选择，第2个位置上除第一个已经选择的数，还包括数字0，也有9种选择
     * i == 3: 9 * 9 * 8, 前面两个位置同 i==2，第三个位置除前两个位置已经选择的数还有8个数可以用
     * ...
     * i == n: 9 * 9 * 8 * ... (9-i+2)
     * 需要注意的是，9- i + 2 > 0 即 i < 11，也就是i最大为10，正好把每个数都用了一遍
     */
    public static int countNumbersWithUniqueDigits(int n) {
        if (n == 0) {
            return 1;
        }

        int result = 10, count = 9;
        for (int i = 2; i <= n; i++) {
            count *= (11 - i);
            result += count;
        }
        return result;
    }
}
