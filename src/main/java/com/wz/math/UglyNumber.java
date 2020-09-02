package com.wz.math;

/**
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Example 1:
 * Input: 6
 * Output: true
 * Explanation: 6 = 2 × 3
 *
 * Example 2:
 * Input: 14
 * Output: false
 * Explanation: 14 is not ugly since it includes another prime factor 7.
 *
 * Note:
 * 1. 1 is typically treated as an ugly number.
 * 2. Input is within the 32-bit signed integer range: [−231,  231 − 1].
 */
public class UglyNumber {
    public static void main(String[] args) {
        System.out.println(isUgly(6));
        System.out.println(isUgly(14));
    }

    /**
     * 分别不停的除以 2，3，5，看最后剩下来的数字是否为1即可
     */
    public static boolean isUgly(int num) {
        if (num <= 0) {
            return false;
        }

        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }

        return num == 1;
    }
}
