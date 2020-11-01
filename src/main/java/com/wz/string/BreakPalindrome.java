package com.wz.string;

/**
 * Given a palindromic string palindrome, replace exactly one character by any lowercase English letter
 * so that the string becomes the lexicographically smallest possible string that isn't a palindrome.
 * After doing so, return the final string.  If there is no way to do so, return the empty string.
 *
 * Example 1:
 * Input: palindrome = "abccba"
 * Output: "aaccba"
 *
 * Example 2:
 * Input: palindrome = "a"
 * Output: ""
 *
 * Constraints:
 * 1. 1 <= palindrome.length <= 1000
 * 2. palindrome consists of only lowercase English letters.
 */
public class BreakPalindrome {
    public static void main(String[] args) {
        System.out.println(breakPalindrome("abccba"));
        System.out.println(breakPalindrome("a"));
    }

    /**
     * 从左向右遍历 palindrome，把第一个不是 a 的字符转换成 a
     * 如果没有发生替换操作，则说明全是 a，这是判断 palindrome 是否是1，若是1则直接返回""，否则将最后一个字符转换成 b
     */
    public static String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        StringBuilder builder = new StringBuilder(palindrome);
        for (int i = 0; i < n / 2; i++) {
            if (builder.charAt(i) != 'a') {
                builder.setCharAt(i, 'a');
                return builder.toString();
            }
        }

        if (n == 1) {
            return "";
        }

        // 说明全是 a，将最后一个字符换成 b
        builder.setCharAt(n - 1, 'b');
        return builder.toString();
    }
}
