package com.wz.math;

/**
 * Given an integer, write a function to determine if it is a power of three.
 *
 * Example 1:
 * Input: 27
 * Output: true
 *
 * Example 2:
 * Input: 0
 * Output: false
 *
 * Example 3:
 * Input: 9
 * Output: true
 *
 * Example 4:
 * Input: 45
 * Output: false
 *
 * Follow up:
 * Could you do it without using any loop / recursion?
 */
public class PowerOfThree {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(27));
        System.out.println(isPowerOfThree2(27));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree2(9));
        System.out.println(isPowerOfThree(45));
        System.out.println(isPowerOfThree2(45));
    }

    /**
     * 不停地除以3，看最后的商是否为1
     */
    public static boolean isPowerOfThree(int n) {
        while (n > 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    /**
     * 由于输入是int，范围是0-2^31，在此范围中允许的最大的3的次方数为3^19=1162261467，只要看这个数能否被 n 整除即可
     */
    public static boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
