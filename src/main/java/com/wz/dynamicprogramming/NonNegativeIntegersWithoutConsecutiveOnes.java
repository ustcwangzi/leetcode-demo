package com.wz.dynamicprogramming;

/**
 * Given a positive integer n, find the number of non-negative integers less than or equal to n,
 * whose binary representations do NOT contain consecutive ones.
 *
 * Example 1:
 * Input: 5
 * Output: 5
 * Explanation:
 * Here are the non-negative integers <= 5 with their corresponding binary representations:
 * 0 : 0
 * 1 : 1
 * 2 : 10
 * 3 : 11
 * 4 : 100
 * 5 : 101
 * Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
 * Note: 1 <= n <= 109
 */
public class NonNegativeIntegersWithoutConsecutiveOnes {
    public static void main(String[] args) {
        System.out.println(findIntegers(5));
    }

    /**
     * one[i] be the number of non-consecutive-1 solutions with the i-th significant bit being 1;
     * zero[i] be the number of non-consecutive-1 solutions with the i-th significant bit being 0;
     * the most tricky part is how to count the solutions smaller than num.
     * We first calculate number of all n-bits solutions: result = one[n - 1] + zero[n - 1].
     * then we subtract the solutions which is larger than num, we iterate from the MSB to LSB of num:
     * if bit[i] == 1
     *      if bit[i + 1] == 0, there's no solutions in res that is larger than num, we go further into smaller bit and subtract.
     *      if bit[i + 1] == 1, we know in those res solutions it won't have any consecutive ones. when bit[i + 1] == 1,
     *          in one[i + 1], the i-th bit in valid solutions must be 0, which are all smaller than 'num',
     *          we don't need to check smaller bits and subtract, so we break form the loop.
     * if bit[i] == 0
     *      if bit[i + 1] == 1, there's no solutions in res that is larger than num, we go further into smaller bit and subtract.
     *      if bit[i + 1] == 0, we know zero[i + 1] includes solutions of i-th == 0 (00***) and i-th bit == 1 (01***),
     *          we know the i-th bit of num is 0, so we need to subtract all the 01*** solutions because it is larger than num.
     *          Therefore, one[i] is subtracted from result.
     */
    public static int findIntegers(int num) {
        StringBuilder builder = new StringBuilder(Integer.toBinaryString(num)).reverse();
        int n = builder.length();
        int[] ones = new int[n], zeros = new int[n];
        ones[0] = zeros[0] = 1;
        for (int i = 1; i < n; ++i) {
            zeros[i] = ones[i - 1] + zeros[i - 1];
            ones[i] = zeros[i - 1];
        }
        int result = zeros[n - 1] + ones[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (builder.charAt(i) == '1' && builder.charAt(i + 1) == '1') {
                break;
            }
            if (builder.charAt(i) == '0' && builder.charAt(i + 1) == '0') {
                result -= ones[i];
            }
        }
        return result;
    }
}
