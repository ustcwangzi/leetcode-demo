package com.wz.string;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none)
 * of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * It's guaranteed the answer fits on a 32-bit signed integer.
 *
 * Example:
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 */
public class DistinctSubsequences {
    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit"));
    }

    /**
     * 动态规划
     * dp[i][j] 代表 s[0...i-1] 组成 t[0...j-1] 所有可能数
     * 1. s 和 t 都为空时，可能数为 1，即 dp[0][0] = 1
     * 2. s 为空、t 不为空时，可能数为 0
     * 3. s 不为空、t 为空时，可能数为 1，即不用任何字符，dp[i][0] = 1
     * 4. 若 s[i-1] != t[j-1]，则不能使用最后一个字符 s[i-1]，因此 dp[i][j] = dp[i-1][j]
     * 5. 若 s[i-1] != t[j-1]，可以不使用最后一个字符 s[i-1]，对应可能数为 dp[i-1][j]，也可以使用最后一个字符 s[i-1]，
     *    即新增的可能次数，也就是 s 和 t 同时没有最后一个字符(s[i-1] 和 t[j-1]) 时可能次数，相当于为这些可能次数都新增了一次可能次数，
     *    因此，新增的可能次数为 dp[i-1][j-1]，此时 dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
     */
    public static int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        // t 为空时
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // 可以选择使用或不使用 s[i-1]
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // 不使用 s[i-1]
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}
