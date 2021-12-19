package com.wz.dfs;

/**
 * Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized.
 * The two subsequences are disjoint if they do not both pick a character at the same index.
 * Return the maximum possible product of the lengths of the two palindromic subsequences.
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing
 * the order of the remaining characters. A string is palindromic if it reads the same forward and backward.
 *
 * Example 1:
 * @link ../../../../resource/MaximumProductOfTheLengthOfTwoPalindromicSubsequences.jpg
 * example-1
 * Input: s = "leetcodecom"
 * Output: 9
 * Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2nd subsequence.
 * The product of their lengths is: 3 * 3 = 9.
 *
 * Example 2:
 * Input: s = "bb"
 * Output: 1
 * Explanation: An optimal solution is to choose "b" (the first character) for the 1st subsequence and "b" (the second character) for the 2nd subsequence.
 * The product of their lengths is: 1 * 1 = 1.
 *
 * Example 3:
 * Input: s = "accbcaxxcxx"
 * Output: 25
 * Explanation: An optimal solution is to choose "accca" for the 1st subsequence and "xxcxx" for the 2nd subsequence.
 * The product of their lengths is: 5 * 5 = 25.
 *
 * Constraints:
 * 1. 2 <= s.length <= 12
 * 2. s consists of lowercase English letters only.
 */
public class MaximumProductOfTheLengthOfTwoPalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(maxProduct("leetcodecom"));
        System.out.println(maxProduct("bb"));
    }

    /**
     * DFS，对于每个位置有两种选择：使用当前字符、不使用当前字符，因此有三种情况：s1 使用当前字符、s2 使用当前字符、s1 和 s2 都不使用当前字符
     */
    public static int maxProduct(String s) {
        if (s.length() <= 1) {
            return 0;
        }
        return dfs(s, "", "", 0, 0);
    }

    private static int dfs(String s, String s1, String s2, int index, int max) {
        if (index >= s.length()) {
            if (isPalindrome(s1) && isPalindrome(s2)) {
                return Math.max(max, s1.length() * s2.length());
            }
            return max;
        }

        return Math.max(dfs(s, s1 + s.charAt(index), s2, index + 1, max),
                Math.max(dfs(s, s1, s2 + s.charAt(index), index + 1, max), dfs(s, s1, s2, index + 1, max)));
    }

    private static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
