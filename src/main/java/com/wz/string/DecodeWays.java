package com.wz.string;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 * Input: s = "0"
 * Output: 0
 *
 * Constraints:
 * 1 <= s.length <= 100
 * s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {
    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("0"));
    }

    /**
     * 动态规划
     * dp[i] 表示 s[0...i-1] 的解码方法个数, dp[0]=1 代表空串有一种解码，对于 dp[i]
     * 若 s[i-1] == '0'，因为无法单独拆分，因此 dp[i] = 0
     * 若 s[i-1] != '0'，则 dp[i] = dp[i - 1]
     * 此时，要再看 s[i-2]s[i-1]组成的数字是否在 10～26 之间，如果满足，则当前 dp[i] 值需要再加上 dp[i-2]
     */
    public static int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            // 位于 10～26 之间，需要加上 dp[i-2]
            if (i > 1 && (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6'))) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}
