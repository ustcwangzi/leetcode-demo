package com.wz.dynamicprogramming;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
 * Also, since the answer may be very large, you should return the output mod 109 + 7.
 *
 * Example 1:
 * Input: "*"
 * Output: 9
 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
 *
 * Example 2:
 * Input: "1*"
 * Output: 9 + 9 = 18
 *
 * Note:
 * 1. The length of the input string will fit in range [1, 105].
 * 2. The input string will only contain the character '*' and digits '0' - '9'.
 */
public class DecodeWaysII {
    public static void main(String[] args) {
        System.out.println(numDecodings("1*"));
    }

    public static int numDecodings(String s) {
        if (s.charAt(0) == '0') {
            return 0;
        }

        int mod = 1000000007, n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = ((s.charAt(0) == '*') ? 9 : 1);
        for (int i = 2; i <= n; i++) {
            char first = s.charAt(i - 2);
            char second = s.charAt(i - 1);

            if (second == '*') {
                dp[i] += 9 * dp[i - 1];
            } else if (second > '0') {
                dp[i] += dp[i - 1];
            }

            if (first == '1' || first == '2') {
                if (second == '*') {
                    if (first == '1') {
                        dp[i] += 9 * dp[i - 2];
                    } else {
                        dp[i] += 6 * dp[i - 2];
                    }
                } else if (((first - '0') * 10 + (second - '0')) <= 26) {
                    dp[i] += dp[i - 2];
                }
            } else if (first == '*') {
                if (second == '*') {
                    dp[i] += 15 * dp[i - 2];
                } else if (second <= '6') {
                    dp[i] += 2 * dp[i - 2];
                } else {
                    dp[i] += dp[i - 2];
                }
            }
            dp[i] %= mod;
        }

        return dp[n];
    }
}
