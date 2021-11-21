package com.wz.other;

/**
 * Given a string s and an array of strings words, determine whether s is a prefix string of words.
 * A string s is a prefix string of words if s can be made by concatenating the first k strings in words for some positive k no larger than words.length.
 * Return true if s is a prefix string of words, or false otherwise.
 *
 * Example 1:
 * Input: s = "iloveleetcode", words = ["i","love","leetcode","apples"]
 * Output: true
 * Explanation:
 * s can be made by concatenating "i", "love", and "leetcode" together.
 *
 * Example 2:
 * Input: s = "iloveleetcode", words = ["apples","i","love","leetcode"]
 * Output: false
 * Explanation:
 * It is impossible to make s using a prefix of arr.
 *
 * Constraints:
 * 1. 1 <= words.length <= 100
 * 2. 1 <= words[i].length <= 20
 * 3. 1 <= s.length <= 1000
 * 4. words[i] and s consist of only lowercase English letters.
 */
public class CheckIfStringIsPrefixOfArray {
    public static void main(String[] args) {
        System.out.println(isPrefixString("iloveleetcode", new String[]{"i", "love", "leetcode", "apples"}));
        System.out.println(isPrefixString("iloveleetcode", new String[]{"apples", "i", "love", "leetcode"}));
    }

    /**
     * 遍历 words，使用 StringBuilder 收集字符串，若发现当前字符串与 s 相等，则返回 ture
     * 若当前字符串长度已超过 s，则返回 false，遍历结果还没找到与 s 相等的字符，也返回 false
     */
    public static boolean isPrefixString(String s, String[] words) {
        StringBuilder builder = new StringBuilder();
        for (String word : words) {
            builder.append(word);
            if (s.equals(builder.toString())) {
                return true;
            }
            if (builder.length() > s.length()) {
                return false;
            }
        }
        return false;
    }
}
