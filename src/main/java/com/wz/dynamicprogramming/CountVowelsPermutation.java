package com.wz.dynamicprogramming;

/**
 * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
 * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
 * Each vowel 'a' may only be followed by an 'e'.
 * Each vowel 'e' may only be followed by an 'a' or an 'i'.
 * Each vowel 'i' may not be followed by another 'i'.
 * Each vowel 'o' may only be followed by an 'i' or a 'u'.
 * Each vowel 'u' may only be followed by an 'a'.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 1
 * Output: 5
 * Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
 *
 * Example 2:
 * Input: n = 2
 * Output: 10
 * Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
 *
 * Constraints:
 * 1 <= n <= 2 * 10^4
 */
public class CountVowelsPermutation {
    public static void main(String[] args) {
        System.out.println(countVowelPermutation(2));
    }

    /**
     * dp[i][j] 为第 i 个元音字母放第 j 个位置的情况下可以组成的字符串的个数
     */
    public static int countVowelPermutation(int n) {
        int mod = 1000000007;
        long[][] dp = new long[5][n + 1];
        dp[0][0] = dp[1][0] = dp[2][0] = dp[3][0] = dp[4][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[0][i] = (dp[1][i - 1] + dp[2][i - 1] + dp[4][i - 1]) % mod;
            dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % mod;
            dp[2][i] = (dp[1][i - 1] + dp[3][i - 1]) % mod;
            dp[3][i] = dp[2][i - 1];
            dp[4][i] = (dp[3][i - 1] + dp[2][i - 1]) % mod;
        }

        long count = 0;
        for (int i = 0; i < 5; i++) {
            count = (count + dp[i][n - 1]) % mod;
        }

        return (int) (count % mod);
    }
}
