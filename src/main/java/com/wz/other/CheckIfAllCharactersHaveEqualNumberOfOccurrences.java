package com.wz.other;

/**
 * Given a string s, return true if s is a good string, or false otherwise.
 * A string s is good if all the characters that appear in s have the same number of occurrences (i.e., the same frequency).
 *
 * Example 1:
 * Input: s = "abacbc"
 * Output: true
 * Explanation: The characters that appear in s are 'a', 'b', and 'c'. All characters occur 2 times in s.
 *
 * Example 2:
 * Input: s = "aaabb"
 * Output: false
 * Explanation: The characters that appear in s are 'a' and 'b'.
 * 'a' occurs 3 times while 'b' occurs 2 times, which is not the same number of times.
 *
 * Constraints:
 * 1. 1 <= s.length <= 1000
 * 2. s consists of lowercase English letters.
 */
public class CheckIfAllCharactersHaveEqualNumberOfOccurrences {
    public static void main(String[] args) {
        System.out.println(areOccurrencesEqual("abacbc"));
        System.out.println(areOccurrencesEqual("aaabb"));
    }

    /**
     * 用数组统计每个字符出现次数，然后检查次数是否全部相等
     */
    public static boolean areOccurrencesEqual(String s) {
        int[] count = new int[26];
        int times = 0;
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            times = count[s.charAt(i) - 'a'];
        }
        for (int cur : count) {
            if (cur != 0 && cur != times) {
                return false;
            }
        }
        return true;
    }
}
