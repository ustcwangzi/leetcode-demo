package com.wz.math;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Example 1:
 * Input: 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 * Input: 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 */
public class FactorialTrailingZeroes {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(3));
        System.out.println(trailingZeroes(5));
    }

    /**
     * 求一个数的阶乘末尾 0 的个数，也就是要找乘数中 10 的个数，而 10 可分解为 2 和 5
     * 2 的数量又远大于 5 的数量（比如 1 到 10 中有 2个5，5个2），那么题目转换为找出5的个数
     */
    public static int trailingZeroes(int n) {
        int result = 0;
        while (n > 0) {
            n /= 5;
            result += n;
        }
        return result;
    }
}
