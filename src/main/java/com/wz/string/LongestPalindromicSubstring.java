package com.wz.string;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
    }

    /**
     * 动态规划
     * dp[i][j] 表示字符串区间 [i, j] 是否为回文串，分为以下情况：
     * 1. 如果 s[i] = s[j]，只有一个字符，肯定是回文串
     * 2. 如果 i - j == 1，说明是相邻字符，此时需要判断 s[i] 是否等于 s[j]
     * 3. 如果 i - j > 1，除了判断 s[i] 和 s[j] 相等之外，子串 [i+1, j-1] 也需要是回文串，即 dp[i+1][j-1] 为 true
     */
    public static String longestPalindrome(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }

        char[] array = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLength = 1;
        for (int j = 0; j < n; j++) {
            dp[j][j] = true;
            for (int i = 0; i < j; i++) {
                dp[i][j] = array[i] == array[j] && (j - i <= 1 || dp[i + 1][j - 1]);
                if (dp[i][j] && maxLength < j - i + 1) {
                    maxLength = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start + maxLength);
    }
}
