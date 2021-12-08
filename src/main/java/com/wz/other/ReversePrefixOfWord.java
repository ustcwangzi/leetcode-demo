package com.wz.other;

/**
 * Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and
 * ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
 * For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that starts at 0 and ends at 3 (inclusive).
 * The resulting string will be "dcbaefd".
 * Return the resulting string.
 *
 * Example 1:
 * Input: word = "abcdefd", ch = "d"
 * Output: "dcbaefd"
 * Explanation: The first occurrence of "d" is at index 3.
 * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
 *
 * Example 2:
 * Input: word = "abcd", ch = "z"
 * Output: "abcd"
 * Explanation: "z" does not exist in word.
 * You should not do any reverse operation, the resulting string is "abcd".
 *
 * Constraints:
 * 1. 1 <= word.length <= 250
 * 2. word consists of lowercase English letters.
 * 3. ch is a lowercase English letter.
 */
public class ReversePrefixOfWord {
    public static void main(String[] args) {
        System.out.println(reversePrefix("abcdefd", 'd'));
        System.out.println(reversePrefix("abcdefd", 'z'));
    }

    /**
     * 遍历字符串，使用 StringBuilder 收集字符，第一次遇到 ch 时将 StringBuilder 反转
     */
    public static String reversePrefix(String word, char ch) {
        StringBuilder builder = new StringBuilder();
        boolean reversed = false;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            builder.append(cur);
            if (!reversed && cur == ch) {
                builder.reverse();
                reversed = true;
            }
        }
        return builder.toString();
    }
}
