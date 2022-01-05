package com.wz.other;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a string word, return the sum of the number of vowels ('a', 'e', 'i', 'o', and 'u') in every substring of word.
 * A substring is a contiguous (non-empty) sequence of characters within a string.
 * Note: Due to the large constraints, the answer may not fit in a signed 32-bit integer. Please be careful during the calculations.
 *
 * Example 1:
 * Input: word = "aba"
 * Output: 6
 * Explanation:
 * All possible substrings are: "a", "ab", "aba", "b", "ba", and "a".
 * - "b" has 0 vowels in it
 * - "a", "ab", "ba", and "a" have 1 vowel each
 * - "aba" has 2 vowels in it
 * Hence, the total sum of vowels = 0 + 1 + 1 + 1 + 1 + 2 = 6.
 *
 * Example 2:
 * Input: word = "abc"
 * Output: 3
 * Explanation:
 * All possible substrings are: "a", "ab", "abc", "b", "bc", and "c".
 * - "a", "ab", and "abc" have 1 vowel each
 * - "b", "bc", and "c" have 0 vowels each
 * Hence, the total sum of vowels = 1 + 1 + 1 + 0 + 0 + 0 = 3.
 *
 * Example 3:
 * Input: word = "ltcd"
 * Output: 0
 * Explanation: There are no vowels in any substring of "ltcd".
 *
 * Constraints:
 * 1. 1 <= word.length <= 10^5
 * 2. word consists of lowercase English letters.
 */
public class VowelsOfAllSubstrings {
    public static void main(String[] args) {
        System.out.println(countVowels("aba"));
        System.out.println(countVowels("abc"));
    }

    /**
     * 遍历 word，对于第 i 个字符，如果它是元音，那么包含它的子串的左端点可以选择 0, 1, ..., i 一共 i+1 种，
     * 右端点可以选择 i, i+1, ..., n-1 一共 n-i 种，因此包含它的子串个数为：(i+1)*(n-i)
     */
    public static long countVowels(String word) {
        int n = word.length();
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        long result = 0;
        for (int i = 0; i < n; i++) {
            if (set.contains(word.charAt(i))) {
                result += (i + 1L) * (n - i);
            }
        }
        return result;
    }
}
