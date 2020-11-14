package com.wz.dynamicprogramming;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: 1
 * Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
 *
 * Example 2:
 * Input: s = "a"
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= s.length <= 2000
 * 2. s consists of lower-case English letters only.
 */
public class PalindromePartitioningII {
    public static void main(String[] args) {
        System.out.println(minCut("aab"));
    }

    /**
     * 动态规划
     * dp[i] 表示 s[0, i] 范围内的最小分割数
     * 假设已经知道 s[0, j-1] 的最小分割数 dp[j-1]，只需要判断区间 s[j, i] 内的子串是否为回文串，是的话，dp[i] = 1 + dp[j-1]
     * palindrome[i][j] 表示 s[i, j] 内的子串是否为回文串，palindrome[i][j] = (s[i] == s[j]) && palindrome[i+1][j-1]
     */
    public static int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n];
        boolean[][] palindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            // 对于 s[0, i]，最多只用分割 i 次
            dp[i] = i;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j < 2 || palindrome[j + 1][i - 1])) {
                    palindrome[j][i] = true;
                    // j=0 说明 s[0, i] 是回文串，不用分割，否则用 dp[j-1] + 1 来更新 dp[i]
                    dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        return dp[n - 1];
    }
}
