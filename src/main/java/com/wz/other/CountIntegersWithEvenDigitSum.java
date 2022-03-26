package com.wz.other;

/**
 * Given a positive integer num, return the number of positive integers less than or equal to num whose digit sums are even.
 * The digit sum of a positive integer is the sum of all its digits.
 *
 * Example 1:
 * Input: num = 4
 * Output: 2
 * Explanation:
 * The only integers less than or equal to 4 whose digit sums are even are 2 and 4.
 *
 * Example 2:
 * Input: num = 30
 * Output: 14
 * Explanation:
 * The 14 integers less than or equal to 30 whose digit sums are even are
 * 2, 4, 6, 8, 11, 13, 15, 17, 19, 20, 22, 24, 26, and 28.
 *
 * Constraints:
 * 1 <= num <= 1000
 */
public class CountIntegersWithEvenDigitSum {
    public static void main(String[] args) {
        System.out.println(countEven(4));
        System.out.println(countEven(30));
    }

    /**
     * 直接从 1～num 判断每个 num 的各位之和是否为偶数即可
     */
    public static int countEven(int num) {
        int result = 0;
        while (num > 0) {
            result += valid(num) ? 1 : 0;
            num--;
        }
        return result;
    }

    private static boolean valid(int num) {
        int count = 0;
        while (num > 0) {
            count += num % 10;
            num /= 10;
        }
        return (count & 1) == 0;
    }
}
