package com.wz.other;

/**
 * Given an integer n (in base 10) and a base k, return the sum of the digits of n after converting n from base 10 to base k.
 * After converting, each digit should be interpreted as a base 10 number, and the sum should be returned in base 10.
 *
 * Example 1:
 * Input: n = 34, k = 6
 * Output: 9
 * Explanation: 34 (base 10) expressed in base 6 is 54. 5 + 4 = 9.
 *
 * Example 2:
 * Input: n = 10, k = 10
 * Output: 1
 * Explanation: n is already in base 10. 1 + 0 = 1.
 *
 * Constraints:
 * 1. 1 <= n <= 100
 * 2. 2 <= k <= 10
 */
public class SumOfDigitsInBaseK {
    public static void main(String[] args) {
        System.out.println(sumBase(24, 6));
    }

    /**
     * 在将 n 转换为 k 进制的时候对每一位进行累加即可
     */
    public static int sumBase(int n, int k) {
        int result = 0;
        while (n > 0) {
            result += n % k;
            n /= k;
        }
        return result;
    }
}
