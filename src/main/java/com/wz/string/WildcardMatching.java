package com.wz.string;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 * 1. '?' Matches any single character.
 * 2. '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 * Note:
 * 1. s could be empty and contains only lowercase letters a-z.
 * 2. p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 *
 * Example 1:
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 *
 * Example 2:
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 *
 */
public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "*"));
    }

    /**
     * dp[i][j] 表示 s中前i个字符和p中前j个字符是否能匹配
     * dp[0][0] 表示s和p都为空，此时也是匹配的，为 true
     * 另外，当s为空，p为连续的星号时，由于星号是可以代表空串的，那么连续的星号的位置都应该为 true
     * 1. 若p中第j个字符是星号
     * 由于星号可以匹配空串，所以如果p中的前 j-1 个字符跟s中前i个字符匹配(dp[i][j-1])，则 dp[i][j] 为 true
     * 或者若p中的前j个字符跟s中的前i-1个字符匹配(dp[i-1][j] 为true)，则 dp[i][j] 为 true
     * 2. 若p中的第j个字符不是星号
     * 假设已经知道了s中前 i-1 个字符和p中前 j-1 个字符的匹配情况(即 dp[i-1][j-1])，现在只需要匹配s中的第i个字符跟p中的第j个字符，
     * 若二者相等(s[i-1] == p[j-1])，或者p中的第j个字符是问号(p[j-1] == '?')，就可以更新 dp[i][j]
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int i = 0; i < n && p.charAt(i++) == '*'; ) {
            dp[0][i] = true;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }
}
