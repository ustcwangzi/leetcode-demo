package com.wz.string;

/**
 * Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.
 * Return the power of the string.
 *
 * Example 1:
 * Input: s = "leetcode"
 * Output: 2
 * Explanation: The substring "ee" is of length 2 with the character 'e' only.
 *
 * Example 2:
 * Input: s = "abbcccddddeeeeedcba"
 * Output: 5
 * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
 *
 * Constraints:
 * 1. 1 <= s.length <= 500
 * 2. s contains only lowercase English letters.
 */
public class ConsecutiveCharacters {
    public static void main(String[] args) {
        System.out.println(maxPower("leetcode"));
    }

    public static int maxPower(String s) {
        char[] array = s.toCharArray();
        int count = 1, result = 1;
        char pre = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == pre) {
                count++;
            } else {
                pre = array[i];
                count = 1;
            }
            result = Math.max(result, count);
        }
        return result;
    }
}
