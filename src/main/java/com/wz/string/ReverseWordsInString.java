package com.wz.string;

/**
 * Given an input string s, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 * Return a string of the words in reverse order concatenated by a single space.
 * Note that s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words. Do not include any extra spaces.
 *
 * Example 1:
 * Input: s = "the sky is blue"
 * Output: "blue is sky the"
 *
 * Example 2:
 * Input: s = "  hello world  "
 * Output: "world hello"
 * Explanation: Your reversed string should not contain leading or trailing spaces.
 */
public class ReverseWordsInString {
    public static void main(String[] args) {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("  hello  world  "));
    }

    /**
     * 去除首尾空格，将中间的多个连续空格替换为一个，然后按照空格拆分，最后逆序组合
     */
    public static String reverseWords(String s) {
        String[] array = s.trim().replaceAll(" + ", " ").split(" ");
        StringBuilder builder = new StringBuilder();
        for (int i = array.length - 1; i > 0; i--) {
            builder.append(array[i]).append(" ");
        }
        builder.append(array[0]);
        return builder.toString();
    }
}
