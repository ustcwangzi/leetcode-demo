package com.wz.math;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 *
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 *
 * Note:
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 2^31 − 1 when the division result overflows.
 */
public class DivideTwoIntegers {
    public static void main(String[] args) {
        System.out.println(divide(10, 3));
        System.out.println(divide(7, -3));
    }

    /**
     * 把除数向左移位(×2)然后与被除数比较，直到发现仅次于被除数的那个值，减去该值后继续
     */
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return -dividend;
        }
        if (dividend == 0) {
            return 0;
        }

        int sign = -1;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            sign = 1;
        }

        // 防止溢出
        long m = Math.abs((long) dividend), n = Math.abs((long) divisor);
        int result = 0;
        while (m >= n) {
            long t = n, p = 1;
            while (m >= (t << 1)) {
                t <<= 1;
                p <<= 1;
            }
            result += p;
            m -= t;
        }
        return sign == 1 ? result : -result;
    }
}
