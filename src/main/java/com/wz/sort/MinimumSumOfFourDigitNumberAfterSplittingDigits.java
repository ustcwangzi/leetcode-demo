package com.wz.sort;

import java.util.Arrays;

/**
 * You are given a positive integer num consisting of exactly four digits. Split num into two new integers new1 and new2 by using the digits found in num.
 * Leading zeros are allowed in new1 and new2, and all the digits found in num must be used.
 * For example, given num = 2932, you have the following digits: two 2's, one 9 and one 3.
 * Some of the possible pairs [new1, new2] are [22, 93], [23, 92], [223, 9] and [2, 329].
 * Return the minimum possible sum of new1 and new2.
 *
 * Example 1:
 * Input: num = 2932
 * Output: 52
 * Explanation: Some possible pairs [new1, new2] are [29, 23], [223, 9], etc.
 * The minimum sum can be obtained by the pair [29, 23]: 29 + 23 = 52.
 *
 * Example 2:
 * Input: num = 4009
 * Output: 13
 * Explanation: Some possible pairs [new1, new2] are [0, 49], [490, 0], etc.
 * The minimum sum can be obtained by the pair [4, 9]: 4 + 9 = 13.
 *
 * Constraints:
 * 1000 <= num <= 9999
 */
public class MinimumSumOfFourDigitNumberAfterSplittingDigits {
    public static void main(String[] args) {
        System.out.println(minimumSum(2932));
        System.out.println(minimumSum(4009));
    }

    /**
     * 对每一位的元素重新排序，假设排序后为 ABCD，那么最终结果为 AC + BD
     */
    public static int minimumSum(int num) {
        int[] array = new int[4];
        int index = 0;
        while (num > 0) {
            int cur = num % 10;
            array[index++] = cur;
            num /= 10;
        }
        Arrays.sort(array);
        return array[0] * 10 + array[2] + array[1] * 10 + array[3];
    }
}
