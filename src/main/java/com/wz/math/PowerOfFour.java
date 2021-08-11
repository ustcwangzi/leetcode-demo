package com.wz.math;

/**
 * Given an integer n, return true if it is a power of four. Otherwise, return false.
 * An integer n is a power of four, if there exists an integer x such that n == 4x.
 *
 * Example 1:
 * Input: n = 16
 * Output: true
 *
 * Example 2:
 * Input: n = 5
 * Output: false
 *
 * Example 3:
 * Input: n = 1
 * Output: true
 *
 * Constraints:
 * -2^31 <= n <= 2^31 - 1
 */
public class PowerOfFour {
    public static void main(String[] args) {
        System.out.println(isPowerOfFour(1));
        System.out.println(isPowerOfFour2(1));
        System.out.println(isPowerOfFour(5));
        System.out.println(isPowerOfFour2(5));
    }

    /**
     * 不停地除以4，看最后的商是否为1
     */
    public static boolean isPowerOfFour(int n) {
        while (n > 0 && n % 4 == 0) {
            n /= 4;
        }
        return n == 1;
    }

    /**
     * 4^X==N   =>   log(4^X)==logN   =>   X*log4==logN   =>   X==(logN)/(log4)
     * 然后判断 X 是否为整数即可
     */
    public static boolean isPowerOfFour2(int n) {
        double lg = Math.log(n) / Math.log(4);
        return Math.abs(lg - Math.round(lg)) <= 0.00000000000001;
    }
}
