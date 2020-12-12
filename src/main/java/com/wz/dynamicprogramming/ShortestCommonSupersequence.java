package com.wz.dynamicprogramming;

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences.
 * If multiple answers exist, you may return any of them.
 * (A string S is a subsequence of string T if deleting some number of characters from T
 * (possibly 0, and the characters are chosen anywhere from T) results in the string S.)
 *
 * Example 1:
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 *
 * Note:
 * 1. 1 <= str1.length, str2.length <= 1000
 * 2. str1 and str2 consist of lowercase English letters.
 */
public class ShortestCommonSupersequence {
    public static void main(String[] args) {
        System.out.println(shortestCommonSupersequence("abac", "cab"));
    }

    /**
     * 先找到 LCS，然后通过 dp 数组，反向构建出 Common Supersequence
     */
    public static String shortestCommonSupersequence(String str1, String str2) {
        int[][] dp = longestCommonSubsequence(str1, str2);
        int i = str1.length(), j = str2.length();
        StringBuilder builder = new StringBuilder();
        while (i > 0 || j > 0) {
            if (i == 0) {
                builder.append(str2.charAt(--j));
            } else if (j == 0) {
                builder.append(str1.charAt(--i));
            } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                builder.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                builder.append(str1.charAt(--i));
            } else {
                builder.append(str2.charAt(--j));
            }
        }
        return builder.reverse().toString();
    }

    private static int[][] longestCommonSubsequence(String str1, String str2) {
        int m = str1.length(), n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }
}
