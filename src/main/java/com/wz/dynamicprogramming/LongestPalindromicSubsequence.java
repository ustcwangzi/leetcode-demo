package com.wz.dynamicprogramming;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 * Input:
 * "bbbab"
 * Output:
 * 4
 * One possible longest palindromic subsequence is "bbbb".
 *
 * Example 2:
 * Input:
 * "cbbd"
 * Output:
 * 2
 * One possible longest palindromic subsequence is "bb".
 *
 * Constraints:
 * 1. 1 <= s.length <= 1000
 * 2. s consists only of lowercase English letters.
 */
public class LongestPalindromicSubsequence {
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }

    /**
     * 动态规划
     * dp[i][j] 表示 s[i,j] 的最长回文子序列长度
     * 如果 s[i]==s[j]，那么 i 和 j 就可以增加 2 个回文串的长度，中间 dp[i+1][j-1]的值，加上 2 就是 dp[i][j] 的值
     * 如果 s[i]!=s[j]，那么可以去掉 i 或 j 其中的一个字符，然后比较两种情况下所剩的字符串谁的回文子序列更长
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }
}
