package com.wz.math;

/**
 * Given an integer, write a function to determine if it is a power of two.
 *
 * Example 1:
 * Input: 1
 * Output: true
 * Explanation: 2^0 = 1
 *
 * Example 2:
 * Input: 16
 * Output: true
 * Explanation: 2^4 = 16
 *
 * Example 3:
 * Input: 218
 * Output: false
 */
public class PowerOfTwo {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(218));
        System.out.println(isPowerOfTwo2(218));
    }

    /**
     * 如果 n 是 2 的幂，则其二进制表示中是否只有一位为1，因此 n 与 n−1 的二进制表示进行与运算，结果为 0
     */
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && ((n & (n - 1))) == 0;
    }

    /**
     * 2^X==N   =>   log(2^X)==logN   =>   X*log2==logN   =>   X==(logN)/(log2)
     * 然后判断 X 是否为整数即可
     */
    public static boolean isPowerOfTwo2(int n) {
        double lg = Math.log(n) / Math.log(2);
        return Math.abs(lg - Math.round(lg)) <= 0.00000000000001;
    }
}
