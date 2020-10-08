package com.wz.string;

/**
 * Given s1, s2, and s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * Example 1:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 *
 * Example 2:
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 */
public class InterleavingString {
    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }

    /**
     * 动态规划
     * dp[i][j] 代表 s1[0...i-1] 与 s2[0...j-1] 能够组成 s3[0...i+j-1]
     * 首先，若 s1、s2、s3 都是空，是满足条件的，因此 dp[0][0] = true
     * 对于第一列，只使用 s1 时，需要 s1[i-1] == s3[i-1]，并且前一个位置是 true
     * 对于第一行，只使用 s2 时，需要 s2[j-1] == s3[j-1]，并且前一个位置是 true
     * 其他位置，使用 s1 时，需要 s1[i-1] == s3[i+j-1]，使用 s2 时，需要 s2[j-1] == s3[i+j-1]，并且前一个位置是 true
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // 第一列
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        // 第一行
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        return dp[m][n];
    }
}
