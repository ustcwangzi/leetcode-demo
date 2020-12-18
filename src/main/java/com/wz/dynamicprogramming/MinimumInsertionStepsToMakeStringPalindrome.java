package com.wz.dynamicprogramming;

/**
 * Given a string s. In one step you can insert any character at any index of the string.
 * Return the minimum number of steps to make s palindrome.
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 * Example 1:
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we don't need any insertions.
 *
 * Example 2:
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 *
 * Example 3:
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 *
 * Constraints:
 * 1. 1 <= s.length <= 500
 * 2. All characters of s are lower case English letters.
 */
public class MinimumInsertionStepsToMakeStringPalindrome {
    public static void main(String[] args) {
        System.out.println(minInsertions("zzazz"));
        System.out.println(minInsertions("mbadm"));
    }

    /**
     * dp[i][j] 表示使 s[i...j] 成为回文字符串，最少需要添加字符数
     * 若 s[i] == s[j]，dp[i][j] = dp[i+1][j-1]
     * 否则，dp[i][j] = 1 + min{dp[i][j-1], dp[i+1][j]}
     * 根据状态转移公式，在求 dp[i][j] 之前，要先求得 dp[i+1][j-1]，dp[i+1][j]，dp[i][j-1]
     * 因此需要：i: 大->小，j: 小->大 遍历
     */
    public static int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[0][n - 1];
    }
}
