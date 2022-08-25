package com.wz.dynamicprogramming;

/**
 * You are given two strings, word1 and word2. You want to construct a string in the following manner:
 * - Choose some non-empty subsequence subsequence1 from word1.
 * - Choose some non-empty subsequence subsequence2 from word2.
 * - Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
 * Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes can be constructed, return 0.
 * A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s without changing the order of the remaining characters.
 * A palindrome is a string that reads the same forward as well as backward.
 *
 * Example 1:
 * Input: word1 = "cacb", word2 = "cbba"
 * Output: 5
 * Explanation: Choose "ab" from word1 and "cba" from word2 to make "abcba", which is a palindrome.
 *
 * Example 2:
 * Input: word1 = "ab", word2 = "ab"
 * Output: 3
 * Explanation: Choose "ab" from word1 and "a" from word2 to make "aba", which is a palindrome.
 *
 * Example 3:
 * Input: word1 = "aa", word2 = "bb"
 * Output: 0
 * Explanation: You cannot construct a palindrome from the described method, so return 0.
 *
 * Constraints:
 * 1. 1 <= word1.length, word2.length <= 1000
 * 2. word1 and word2 consist of lowercase English letters.
 */
public class MaximizePalindromeLengthFromSubsequences {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("cacb", "cbba"));
        System.out.println(longestPalindrome("ab", "ab"));
    }

    /**
     * 是对 {@link LongestPalindromicSubstring} 的扩展
     */
    public static int longestPalindrome(String word1, String word2) {
        String word = word1 + word2;
        int n = word.length(), result = 0;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (word.charAt(i) == word.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    // Check if this palindrome begins with word1[i] and ends with word2[j]
                    if (i < word1.length() && j >= word1.length()) {
                        result = Math.max(result, dp[i][j]);
                    }
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return result;
    }
}
