package com.wz.other;

import java.util.Arrays;

/**
 * Given an integer num, return three consecutive integers (as a sorted array) that sum to num.
 * If num cannot be expressed as the sum of three consecutive integers, return an empty array.
 *
 * Example 1:
 * Input: num = 33
 * Output: [10,11,12]
 * Explanation: 33 can be expressed as 10 + 11 + 12 = 33.
 * 10, 11, 12 are 3 consecutive integers, so we return [10, 11, 12].
 *
 * Example 2:
 * Input: num = 4
 * Output: []
 * Explanation: There is no way to express 4 as the sum of 3 consecutive integers.
 *
 * Constraints:
 * 0 <= num <= 10^15
 */
public class FindThreeConsecutiveIntegersThatSumToGivenNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumOfThree(33)));
    }

    /**
     * 直接判断能够被3整除，可以的话，+1、-1即是最后的结果
     */
    public static long[] sumOfThree(long num) {
        if (num % 3 == 0) {
            return new long[]{num / 3 - 1, num / 3, num / 3 + 1};
        }
        return new long[0];
    }
}
