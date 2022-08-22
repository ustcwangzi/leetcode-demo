package com.wz.dynamicprogramming;

/**
 * Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings. Otherwise, return false.
 * A string is said to be palindrome if it the same string when reversed.
 *
 * Example 1:
 * Input: s = "abcbdd"
 * Output: true
 * Explanation: "abcbdd" = "a" + "bcb" + "dd", and all three substrings are palindromes.
 *
 * Example 2:
 * Input: s = "bcbddxy"
 * Output: false
 * Explanation: s cannot be split into 3 palindromes.
 *
 * Constraints:
 * 1. 3 <= s.length <= 2000
 * 2. s consists only of lowercase English letters.
 */
public class PalindromePartitioningIV {
    public static void main(String[] args) {
        System.out.println(checkPartitioning("abcbdd"));
        System.out.println(checkPartitioning("bcbddxy"));
    }

    /**
     * 遍历 s，从 i 开始向两侧延伸，判断 s[i...j] 是否为 palindrome
     * 然后检查 s[0...i], s[i+1...j], s[j+1...n-1] 是否为 palindrome
     */
    public static boolean checkPartitioning(String s) {
        int n = s.length();
        if (n == 3) {
            return true;
        }

        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            expend(s, i, i, dp);
            expend(s, i, i + 1, dp);
        }

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n - 1; ++j) {
                if (dp[0][i] && dp[i + 1][j] && dp[j + 1][n - 1]) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void expend(String s, int left, int right, boolean[][] dp) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            dp[left--][right++] = true;
        }
    }
}
