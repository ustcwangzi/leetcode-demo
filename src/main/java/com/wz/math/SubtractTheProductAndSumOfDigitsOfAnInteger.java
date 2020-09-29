package com.wz.math;

/**
 * Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 *
 * Example:
 * Input: n = 234
 * Output: 15
 * Explanation:
 * Product of digits = 2 * 3 * 4 = 24
 * Sum of digits = 2 + 3 + 4 = 9
 * Result = 24 - 9 = 15
 */
public class SubtractTheProductAndSumOfDigitsOfAnInteger {
    public static void main(String[] args) {
        System.out.println(subtractProductAndSum(234));
    }

    public static int subtractProductAndSum(int n) {
        if (n == 0) {
            return 0;
        }
        int product = 1, sum = 0;
        while (n != 0) {
            int d = n % 10;
            product *= d;
            sum += d;
            n /= 10;
        }
        return product - sum;
    }
}
