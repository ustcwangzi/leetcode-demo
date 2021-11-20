package com.wz.other;

/**
 * A fancy string is a string where no three consecutive characters are equal.
 * Given a string s, delete the minimum possible number of characters from s to make it fancy.
 * Return the final string after the deletion. It can be shown that the answer will always be unique.
 *
 * Example 1:
 * Input: s = "leeetcode"
 * Output: "leetcode"
 * Explanation:
 * Remove an 'e' from the first group of 'e's to create "leetcode".
 * No three consecutive characters are equal, so return "leetcode".
 *
 * Example 2:
 * Input: s = "aaabaaaa"
 * Output: "aabaa"
 * Explanation:
 * Remove an 'a' from the first group of 'a's to create "aabaaaa".
 * Remove two 'a's from the second group of 'a's to create "aabaa".
 * No three consecutive characters are equal, so return "aabaa".
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists only of lowercase English letters.
 */
public class DeleteCharactersToMakeFancyString {
    public static void main(String[] args) {
        System.out.println(makeFancyString("leeetcode"));
    }

    /**
     * 遍历字符串，三个连续相等则跳过
     */
    public static String makeFancyString(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length() - 2; i++) {
            if (s.charAt(i) == s.charAt(i + 1) && s.charAt(i) == s.charAt(i + 2)) {
                continue;
            }
            builder.append(s.charAt(i));
        }
        if (s.length() >= 2) {
            builder.append(s.charAt(s.length() - 2));
        }
        builder.append(s.charAt(s.length() - 1));
        return builder.toString();
    }
}
