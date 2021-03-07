package com.wz.greedy;

import java.util.Arrays;

/**
 * The numeric value of a lowercase character is defined as its position (1-indexed) in the alphabet,
 * so the numeric value of a is 1, the numeric value of b is 2, the numeric value of c is 3, and so on.
 * The numeric value of a string consisting of lowercase characters is defined as the sum of its characters' numeric values.
 * For example, the numeric value of the string "abe" is equal to 1 + 2 + 5 = 8.
 * You are given two integers n and k. Return the lexicographically smallest string with length equal to n and numeric value equal to k.
 * Note that a string x is lexicographically smaller than string y if x comes before y in dictionary order, that is,
 * either x is a prefix of y, or if i is the first position such that x[i] != y[i], then x[i] comes before y[i] in alphabetic order.
 *
 * Example 1:
 *
 * Input: n = 3, k = 27
 * Output: "aay"
 * Explanation: The numeric value of the string is 1 + 1 + 25 = 27, and it is the smallest string with such a value and length equal to 3.
 *
 * Example 2:
 * Input: n = 5, k = 73
 * Output: "aaszz"
 *
 * Constraints:
 * 1. 1 <= n <= 10^5
 * 2. n <= k <= 26 * n
 */
public class SmallestStringWithAGivenNumericValue {
    public static void main(String[] args) {
        System.out.println(getSmallestString(3, 27));
        System.out.println(getSmallestString(5, 73));
    }

    /**
     * 反过来理解就是 k 尽量放在字符串的末端，因为长度 n 已经给定，所以创建一个长度为 n 的数组，每个位置初始化为 'a'
     * 然后从后往前试图把剩下的值累加到每个位置上即可。因为每个位置初始化为 'a'，所以最大累加值是 25，从 k 中依次扣除
     */
    public static String getSmallestString(int n, int k) {
        char[] array = new char[n];
        Arrays.fill(array, 'a');
        k -= n;

        for (int i = n - 1; i >= 0; i--) {
            int num = Math.min(25, k);
            array[i] += num;
            k -= num;
        }

        return String.valueOf(array);
    }
}
