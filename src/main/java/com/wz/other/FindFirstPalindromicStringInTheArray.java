package com.wz.other;

/**
 * Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".
 *
 * A string is palindromic if it reads the same forward and backward.
 *
 *
 *
 * Example 1:
 * Input: words = ["abc","car","ada","racecar","cool"]
 * Output: "ada"
 * Explanation: The first string that is palindromic is "ada".
 * Note that "racecar" is also palindromic, but it is not the first.
 *
 * Example 2:
 * Input: words = ["notapalindrome","racecar"]
 * Output: "racecar"
 * Explanation: The first and only string that is palindromic is "racecar".
 *
 * Example 3:
 * Input: words = ["def","ghi"]
 * Output: ""
 * Explanation: There are no palindromic strings, so the empty string is returned.
 *
 * Constraints:
 * 1. 1 <= words.length <= 100
 * 2. 1 <= words[i].length <= 100
 * 3. words[i] consists only of lowercase English letters.
 */
public class FindFirstPalindromicStringInTheArray {
    public static void main(String[] args) {
        System.out.println(firstPalindrome(new String[]{"abc", "car", "ada", "racecar", "cool"}));
        System.out.println(firstPalindrome(new String[]{"notapalindrome", "racecar"}));
    }

    /**
     * 直接遍历判断每个字符串是否回文即可
     */
    public static String firstPalindrome(String[] words) {
        for (String word : words) {
            if (isPalindrome(word)) {
                return word;
            }
        }
        return "";
    }

    private static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
