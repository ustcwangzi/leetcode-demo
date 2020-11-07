package com.wz.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and an integer k.
 * Return the maximum number of vowel letters in any substring of s with length k.
 * Vowel letters in English are (a, e, i, o, u).
 *
 * Example 1:
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 *
 * Example 2:
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of lowercase English letters.
 * 3. 1 <= k <= s.length
 */
public class MaximumNumberOfVowelsInSubstringOfGivenLength {
    public static void main(String[] args) {
        System.out.println(maxVowels("leetcode", 3));
    }

    /**
     * 滑动窗口
     * 遍历 s，统计数量 count，如果指针 i 超过 k，则判断 i-k 位置上是不是 vowel，若是，则从 count 减1，代表窗口右移
     */
    public static int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        int result = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i))) {
                count++;
            }
            if (i >= k && vowels.contains(s.charAt(i - k))) {
                count--;
            }
            result = Math.max(result, count);
        }
        return result;
    }
}
