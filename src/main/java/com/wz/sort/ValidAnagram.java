package com.wz.sort;

import java.util.Arrays;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 *
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 *
 * Constraints:
 * 1. 1 <= s.length, t.length <= 5 * 10^4
 * 2. s and t consist of lowercase English letters.
 *
 */
public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
    }

    /**
     * 相同字母异序词
     * 使用数组统计每个字符出现次数，s 出现则次数加一，t 中出现则次数减一，判断最后数组中次数是否全部为 0
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        return Arrays.stream(count).filter(value -> value != 0).count() == 0;
    }
}
