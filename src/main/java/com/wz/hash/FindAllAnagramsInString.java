package com.wz.hash;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
 *
 * Example 1:
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Example 2:
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 * Constraints:
 * 1. 1 <= s.length, p.length <= 3 * 10^4
 * 2. s and p consist of lowercase English letters.
 */
public class FindAllAnagramsInString {
    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc"));
        System.out.println(findAnagrams("abab", "ab"));
    }

    /**
     * 滑动窗口
     * 使用数组 count 统计 p 中每个字符的个数，然后使用 left、right 作为滑动窗口统计窗口内字符个数是否与 count 相等
     * 窗口长度小于 p 的长度时，right 右移，否则判断窗口内字符是否与 count 相等，相等则将 left 加入结果中
     * 然后，将 left 所指向的字符从窗口移除，同时将 left、right 都向右移动
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new LinkedList<>();
        if (s.length() < p.length()) {
            return result;
        }

        int[] count = new int[26], tmp = new int[26];
        for (int i = 0; i < p.length(); i++) {
            count[p.charAt(i) - 'a']++;
        }

        int left = 0, right = 0;
        while (right < s.length()) {
            tmp[s.charAt(right) - 'a']++;

            if (right - left + 1 < p.length()) {
                right++;
                continue;
            }

            // left、right 同时移动，因此窗口长度不小于 p 的长度时，一定是等于 p 的长度
            if (Arrays.equals(count, tmp)) {
                result.add(left);
            }
            tmp[s.charAt(left) - 'a']--;
            left++;
            right++;
        }
        return result;
    }
}
