package com.wz.dynamicprogramming;

/**
 * You are given a string s containing lowercase letters and an integer k. You need to :
 * First, change some characters of s to other lowercase English letters.
 * Then divide s into k non-empty disjoint substrings such that each substring is palindrome.
 * Return the minimal number of characters that you need to change to divide the string.
 *
 * Example 1:
 * Input: s = "abc", k = 2
 * Output: 1
 * Explanation: You can split the string into "ab" and "c", and change 1 character in "ab" to make it palindrome.
 *
 * Example 2:
 * Input: s = "aabbc", k = 3
 * Output: 0
 * Explanation: You can split the string into "aa", "bb" and "c", all of them are palindrome.
 *
 * Constraints:
 * 1. 1 <= k <= s.length <= 100.
 * 2. s only contains lowercase English letters.
 */
public class PalindromePartitioningIII {
    public static void main(String[] args) {
        System.out.println(palindromePartition("abc", 2));
    }

    public static int palindromePartition(String s, int k) {
        int n = s.length();
        if (n == k) {
            return 0;
        }
        int[][] dp1 = new int[n][n];
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp1[j][i] = dp1[j + 1][i - 1];
                } else {
                    dp1[j][i] = dp1[j + 1][i - 1] + 1;
                }
            }
        }

        Integer[][] dp2 = new Integer[n][k + 1];
        return helper(dp1, dp2, s, 0, k);
    }

    private static int helper(int[][] replaced, Integer[][] dp2, String s, int pos, int cnt) {
        if (cnt < 0 || cnt == 0 && pos != s.length()) {
            return Integer.MAX_VALUE;
        }
        if (pos == s.length()) {
            return cnt == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (dp2[pos][cnt] != null) {
            return dp2[pos][cnt];
        }

        int min = Integer.MAX_VALUE;
        for (int i = pos; i < s.length(); i++) {
            int curReplace = replaced[pos][i];
            int next = helper(replaced, dp2, s, i + 1, cnt - 1);
            if (next != Integer.MAX_VALUE) {
                min = Math.min(min, next + curReplace);
            }
        }
        dp2[pos][cnt] = min;
        return min;
    }
}
