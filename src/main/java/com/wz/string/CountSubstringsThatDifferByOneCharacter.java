package com.wz.string;

/**
 * Given two strings s and t, find the number of ways you can choose a non-empty substring of s and replace
 * a single character by a different character such that the resulting substring is a substring of t.
 * In other words, find the number of substrings in s that differ from some substring in t by exactly one character.
 * For example, the underlined substrings in "computer" and "computation" only differ by the 'e'/'a', so this is a valid way.
 * Return the number of substrings that satisfy the condition above.
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: s = "aba", t = "baba"
 * Output: 6
 * Explanation: The following are the pairs of substrings from s and t that differ by exactly 1 character:
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * ("aba", "baba")
 * The underlined portions are the substrings that are chosen from s and t.
 *
 * Example 2:
 * Input: s = "ab", t = "bb"
 * Output: 3
 * Explanation: The following are the pairs of substrings from s and t that differ by 1 character:
 * ("ab", "bb")
 * ("ab", "bb")
 * ("ab", "bb")
 * The underlined portions are the substrings that are chosen from s and t.
 *
 * Example 3:
 * Input: s = "a", t = "a"
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= s.length, t.length <= 100
 * 2. s and t consist of lowercase English letters only.
 */
public class CountSubstringsThatDifferByOneCharacter {
    public static void main(String[] args) {
        System.out.println(countSubstrings("aba", "baba"));
        System.out.println(countSubstrings("a", "a"));
    }

    /**
     * dp[i][j] 表示从 s[i] 与从 t[j] 开始的相同子串个数
     * 如果 s[i] == t[j]，则 dp[i][j] = 1 + dp[i+1][j+1]
     * 否则 dp[i][j] = 0
     *
     * count[i][j] 表示从 s[i] 与从 t[j] 开始的字母个数相差1的子串个数
     * 如果 s[i] == t[j]，则 count[i][j] = count[i+1][j+1]
     * 否则 count[i][j] = 1 + dp[i+1][j+1]
     * 然后，将 count[i][j] 叠加到最终结果中
     */
    public static int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                }
            }
        }

        int[][] count = new int[m + 1][n + 1];
        int sum = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    count[i][j] = count[i + 1][j + 1];
                } else {
                    count[i][j] = 1 + dp[i + 1][j + 1];
                }
                sum += count[i][j];
            }
        }
        return sum;
    }
}
