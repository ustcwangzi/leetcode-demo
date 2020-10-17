package com.wz.string;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 */
public class ValidPalindromeII {
    public static void main(String[] args) {
        System.out.println(validPalindrome("aba"));
        System.out.println(validPalindrome("abca"));
    }

    /**
     * 当遇到不匹配的时候，可以删除左边的字符，也可以删除右边的字符，两种情况都要算一遍，只要有一种能返回true，那么结果就返回true
     */
    public static boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        char[] array = s.toCharArray();
        while (left < right) {
            if (array[left] != array[right]) {
                // 选择删除一个字符
                return valid(array, left, right - 1) || valid(array, left + 1, right);
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean valid(char[] array, int left, int right) {
        while (left < right) {
            if (array[left++] != array[right--]) {
                return false;
            }
        }
        return true;
    }
}
