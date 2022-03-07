package com.wz.sort;

import java.util.Arrays;

/**
 * You are given an integer num. Rearrange the digits of num such that its value is minimized and it does not contain any leading zeros.
 * Return the rearranged number with minimal value.
 * Note that the sign of the number does not change after rearranging the digits.
 *
 * Example 1:
 * Input: num = 310
 * Output: 103
 * Explanation: The possible arrangements for the digits of 310 are 013, 031, 103, 130, 301, 310.
 * The arrangement with the smallest value that does not contain any leading zeros is 103.
 *
 * Example 2:
 * Input: num = -7605
 * Output: -7650
 * Explanation: Some possible arrangements for the digits of -7605 are -7650, -6705, -5076, -0567.
 * The arrangement with the smallest value that does not contain any leading zeros is -7650.
 *
 * Constraints:
 * -10^15 <= num <= 10^15
 */
public class SmallestValueOfTheRearrangedNumber {
    public static void main(String[] args) {
        System.out.println(smallestNumber(310));
        System.out.println(smallestNumber(-7605));
    }

    /**
     * 先对 num 进行排序，然后根据 num 的正负情况采用不同的策略：
     * 正数：找到第一个不为 0 的元素交换到位置 0 上，然后返回即可
     * 负数：直接逆向排序，然后返回即可
     */
    public static long smallestNumber(long num) {
        if (num == 0) {
            return 0;
        }

        boolean positive = num > 0;
        num = Math.abs(num);
        char[] array = String.valueOf(num).toCharArray();
        Arrays.parallelSort(array);

        if (positive) {
            int index = firstUnZero(array);
            char tmp = array[index];
            array[index] = array[0];
            array[0] = tmp;
            return Long.parseLong(new String(array));
        }
        StringBuilder builder = new StringBuilder(new String(array));
        return -Long.parseLong(builder.reverse().toString());
    }

    private static int firstUnZero(char[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != '0') {
                return i;
            }
        }
        return 0;
    }
}
