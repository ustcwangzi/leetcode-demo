package com.wz.math;

/**
 * Implement int sqrt(int x).
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * Example 1:
 * Input: 4
 * Output: 2
 *
 * Example 2:
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 */
public class SqrtX {
    public static void main(String[] args) {
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt2(8));
        System.out.println(mySqrt2(8));
    }

    public static int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }

    /**
     * 连续 n 个奇数相加的结果一定是平方数
     */
    public static int mySqrt2(int x) {
        int i = 1, result = 0;
        while (x >= 0) {
            x -= i;
            i += 2;
            result++;
        }
        return result - 1;
    }
}
