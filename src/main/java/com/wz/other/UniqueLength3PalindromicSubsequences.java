package com.wz.other;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
 * Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
 * A palindrome is a string that reads the same forwards and backwards.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde".
 *
 * Example 1:
 * Input: s = "aabca"
 * Output: 3
 * Explanation: The 3 palindromic subsequences of length 3 are:
 * - "aba" (subsequence of "aabca")
 * - "aaa" (subsequence of "aabca")
 * - "aca" (subsequence of "aabca")
 *
 * Example 2:
 * Input: s = "adc"
 * Output: 0
 * Explanation: There are no palindromic subsequences of length 3 in "adc".
 *
 * Example 3:
 * Input: s = "bbcbaba"
 * Output: 4
 * Explanation: The 4 palindromic subsequences of length 3 are:
 * - "bbb" (subsequence of "bbcbaba")
 * - "bcb" (subsequence of "bbcbaba")
 * - "bab" (subsequence of "bbcbaba")
 * - "aba" (subsequence of "bbcbaba")
 *
 * Constraints:
 * 1. 3 <= s.length <= 10^5
 * 2. s consists of only lowercase English letters.
 */
public class UniqueLength3PalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequence("aabca"));
    }

    /**
     * 三个字符组成回文，则两边的字符一定是相同的，因此可以遍历 26 个字母，找到当前字符在 s 中第一次和最后一次出现的位置 left、right
     * 若相等，则说明没出现（-1）或只出现过一次，无法组成回文，直接跳过
     * 否则，使用 Set 统计 (left,right) 之间不同字符的个数，就是以当前字符作为两边能够组成的回文个数
     */
    public static int countPalindromicSubsequence(String s) {
        int result = 0;
        for (int i = 0; i < 26; i++) {
            int left = s.indexOf(i + 'a'), right = s.lastIndexOf(i + 'a');
            if (left == right) {
                continue;
            }
            Set<Character> set = new HashSet<>();
            for (int j = left + 1; j < right; j++) {
                set.add(s.charAt(j));
            }
            result += set.size();
        }
        return result;
    }
}
