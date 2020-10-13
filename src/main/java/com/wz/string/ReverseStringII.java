package com.wz.string;

/**
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting
 * from the start of the string. If there are less than k characters left, reverse all of them.
 * If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
 *
 * Example:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 *
 * Restrictions:
 * 1. The string consists of lower English letters only.
 * 2. Length of the given string and k will in the range [1, 10000]
 */
public class ReverseStringII {
    public static void main(String[] args) {
        System.out.println(reverseStr("abcdefg", 2));
    }

    /**
     * 每 2*k 个元素翻转前 k 个元素，那么每次的补长为 2*k，每次需要翻转的元素位置为 index ～ index+k-1
     */
    public static String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        int index = 0;
        while (index < array.length) {
            // 每次需要翻转的位置
            reverse(array, index, Math.min(index + k - 1, array.length - 1));
            // 步长
            index = index + 2 * k;
        }
        return String.valueOf(array);
    }

    private static void reverse(char[] array, int i, int j) {
        while (i < j) {
            char tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
            i++;
            j--;
        }
    }
}
