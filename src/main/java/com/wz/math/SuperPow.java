package com.wz.math;

/**
 * Your task is to calculate ab mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
 *
 * Example 1:
 * Input: a = 2, b = [3]
 * Output: 8
 *
 * Example 2:
 * Input: a = 2, b = [1,0]
 * Output: 1024
 */
public class SuperPow {
    public static void main(String[] args) {
        System.out.println(superPow(2, new int[]{3}));
        System.out.println(superPow(2, new int[]{1, 0}));
    }

    /**
     * 按位来处理，比如 2^23 = (2^2)^10 * 2^3, 从b的最高位开始，算出结果存入 result，
     * 然后到下一位是，result 的十次方再乘以 a 的该位次方再对 1337 取余
     */
    public static int superPow(int a, int[] b) {
        long result = 1;
        for (int value : b) {
            result = pow(result, 10) * pow(a, value) % 1337;
        }
        return (int) result;
    }

    private static long pow(long x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x % 1337;
        }
        return pow(x % 1337, n / 2) * pow(x % 1337, n - n / 2) % 1337;
    }
}
