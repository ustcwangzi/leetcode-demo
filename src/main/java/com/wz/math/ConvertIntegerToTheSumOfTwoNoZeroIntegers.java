package com.wz.math;

import java.util.Arrays;

/**
 * Given an integer n. No-Zero integer is a positive integer which doesn't contain any 0 in its decimal representation.
 * Return a list of two integers [A, B] where:
 * 1. A and B are No-Zero integers.
 * 2. A + B = n
 * It's guarateed that there is at least one valid solution. If there are many valid solutions you can return any of them.
 *
 * Example 1:
 * Input: n = 2
 * Output: [1,1]
 * Explanation: A = 1, B = 1. A + B = n and both A and B don't contain any 0 in their decimal representation.
 *
 * Example 2:
 * Input: n = 11
 * Output: [2,9]
 */
public class ConvertIntegerToTheSumOfTwoNoZeroIntegers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNoZeroIntegers(2)));
        System.out.println(Arrays.toString(getNoZeroIntegers(11)));
    }

    public static int[] getNoZeroIntegers(int n) {
        int i = 1;
        while (i < n) {
            if (!containZero(i) && !containZero(n - i)) {
                break;
            }
            i++;
        }
        return new int[]{i, n - i};
    }

    private static boolean containZero(int i) {
        while (i > 0) {
            if (i % 10 == 0) {
                return true;
            }
            i /= 10;
        }
        return false;
    }
}
