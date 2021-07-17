package com.wz.practice;

/**
 * 描述
 * 给定两个字符串str1和str2，输出两个字符串的最长公共子序列。如果最长公共子序列为空，则返回"-1"。目前给出的数据，仅仅会存在一个最长的公共子序列
 *
 * 示例1
 * 输入："1A2C3D4B56","B1D23A456A"
 * 返回值："123456"
 *
 * 示例2
 * 输入："abc","def"
 * 返回值："-1"
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("1A2C3D4B56", "B1D23A456A"));
    }

    /**
     * 动态规划
     * 先求出最长公共子序列的长度 {@link com.wz.dynamicprogramming.LongestCommonSubsequence}
     * 然后进行逆序推导，若当前字符相同则加入结果，否则将公共子序列的长度较大的向左移动
     */
    public static String longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        if (dp[m][n] == 0) {
            return "-1";
        }

        StringBuilder builder = new StringBuilder();
        while (m > 0 && n > 0) {
            if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
                builder.append(s1.charAt(m - 1));
                m--;
                n--;
            } else if (dp[m - 1][n] > dp[m][n - 1]) {
                m--;
            } else {
                n--;
            }
        }
        return builder.reverse().toString();
    }
}
