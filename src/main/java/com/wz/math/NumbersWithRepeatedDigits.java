package com.wz.math;

/**
 * Given a positive integer N, return the number of positive integers less than or equal to N that have at least 1 repeated digit.
 *
 * Example 1:
 * Input: 20
 * Output: 1
 * Explanation: The only positive number (<= 20) with at least 1 repeated digit is 11.
 *
 * Example 2:
 * Input: 100
 * Output: 10
 * Explanation: The positive numbers (<= 100) with atleast 1 repeated digit are 11, 22, 33, 44, 55, 66, 77, 88, 99, and 100.
 */
public class NumbersWithRepeatedDigits {
    public static void main(String[] args) {
        System.out.println(numDupDigitsAtMostN(20));

        result = 0;
        System.out.println(numDupDigitsAtMostN(100));
    }

    private static int result = 0;

    public static int numDupDigitsAtMostN(int N) {
        dfs(0, 0, N);
        return N - result + 1;
    }

    /**
     * 统计不重复数字
     */
    private static void dfs(long value, int used, int N) {
        if (value > N) {
            return;
        }
        result += 1;
        for (int i = 0; i < 10; i++) {
            // 排除最高位为0的情况
            if (i == 0 && used == 0) {
                continue;
            }
            // 二进制对应位置上的值为1，说明value已经存在这个值。比如used的二进制表示，右数第5位是1，说明value中已经存在4
            if ((used & (1 << i)) != 0) {
                continue;
            }
            dfs(value * 10 + i, used | (1 << i), N);
        }
    }
}
