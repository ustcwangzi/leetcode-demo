package com.wz.string;

import java.util.HashSet;
import java.util.Set;

/**
 * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase.
 * For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.
 * Given a string s, return the longest substring of s that is nice.
 * If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.
 *
 * Example 1:
 * Input: s = "YazaAay"
 * Output: "aAa"
 * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
 * "aAa" is the longest nice substring.
 *
 * Example 2:
 * Input: s = "Bb"
 * Output: "Bb"
 * Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
 *
 * Example 3:
 * Input: s = "c"
 * Output: ""
 * Explanation: There are no nice substrings.
 *
 * Example 4:
 * Input: s = "dDzeE"
 * Output: "dD"
 * Explanation: Both "dD" and "eE" are the longest nice substrings.
 * As there are multiple longest nice substrings, return "dD" since it occurs earlier.
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s consists of uppercase and lowercase English letters.
 */
public class LongestNiceSubstring {
    public static void main(String[] args) {
        System.out.println(longestNiceSubstring("YazaAay"));
        System.out.println(longestNiceSubstring("dDzeE"));
    }

    /**
     * 先将全部字符放入 set 中，然后遍历 s，若当前字符的大写和小写都在 set 中，则继续遍历
     * 否则，说明当前字符不满足条件，将 s 分为左右两部分 s[0...i-1] 与 s[i+1...n-1]，然后分别寻找两部分中满足条件的最长子串
     */
    public static String longestNiceSubstring(String s) {
        if (s.length() < 2) {
            return "";
        }

        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (set.contains(Character.toLowerCase(cur)) && set.contains(Character.toUpperCase(cur))) {
                continue;
            }
            String left = longestNiceSubstring(s.substring(0, i));
            String right = longestNiceSubstring(s.substring(i + 1));
            return left.length() >= right.length() ? left : right;
        }
        return s;
    }
}
