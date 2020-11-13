package com.wz.string;

import java.util.Arrays;

/**
 * Given a string s, return the length of the longest substring between two equal characters,
 * excluding the two characters. If there is no such substring return -1.
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: s = "aa"
 * Output: 0
 * Explanation: The optimal substring here is an empty substring between the two 'a's.
 *
 * Example 2:
 * Input: s = "cbzxy"
 * Output: -1
 * Explanation: There are no characters that appear twice in s.
 *
 * Example 3:
 * Input: s = "cabbac"
 * Output: 4
 * Explanation: The optimal substring here is "abba". Other non-optimal substrings include "bb" and "".
 *
 * Constraints:
 * 1. 1 <= s.length <= 300
 * 2. s contains only lowercase English letters.
 */
public class LargestSubstringBetweenTwoEqualCharacters {
    public static void main(String[] args) {
        System.out.println(maxLengthBetweenEqualCharacters("cbzxy"));
        System.out.println(maxLengthBetweenEqualCharacters("cabbac"));
    }

    /**
     * 遍历字符串，用 array 记录每个字符的下标，遇到相同字符的时候，计算前后下标的差来得出子字符串的长度
     */
    public static int maxLengthBetweenEqualCharacters(String s) {
        int[] array = new int[26];
        Arrays.fill(array, -1);
        int result = -1;
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i) - 'a';
            if (array[cur] >= 0) {
                result = Math.max(result, i - array[cur] - 1);
            } else {
                array[cur] = i;
            }
        }
        return result;
    }
}
