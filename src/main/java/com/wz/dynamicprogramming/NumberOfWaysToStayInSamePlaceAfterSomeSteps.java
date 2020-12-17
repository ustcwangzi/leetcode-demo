package com.wz.dynamicprogramming;

/**
 * You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left,
 * 1 position to the right in the array or stay in the same place  (The pointer should not be placed outside the array at any time).
 * Given two integers steps and arrLen, return the number of ways such that your pointer still at index 0 after exactly steps steps.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: steps = 3, arrLen = 2
 * Output: 4
 * Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
 * Right, Left, Stay
 * Stay, Right, Left
 * Right, Stay, Left
 * Stay, Stay, Stay
 *
 * Example 2:
 * Input: steps = 2, arrLen = 4
 * Output: 2
 * Explanation: There are 2 differents ways to stay at index 0 after 2 steps
 * Right, Left
 * Stay, Stay
 *
 * Constraints:
 * 1. 1 <= steps <= 500
 * 2. 1 <= arrLen <= 10^6
 */
public class NumberOfWaysToStayInSamePlaceAfterSomeSteps {
    public static void main(String[] args) {
        System.out.println(numWays(3, 2));
    }

    public static int numWays(int steps, int arrLen) {
        int mod = 1000000007;
        int[] dp = new int[arrLen + 1];
        dp[1] = 1;
        for (int j = 1; j <= steps; j++) {
            int[] temp = new int[arrLen + 1];
            for (int i = 1; i <= arrLen; i++) {
                if (i == 1 && i == arrLen) {
                    temp[i] = dp[i] % mod;
                } else if (i == 1) {
                    temp[i] = (dp[i] + dp[i + 1]) % mod;
                } else if (i == arrLen) {
                    temp[i] = (dp[i] + dp[i - 1]) % mod;
                } else {
                    temp[i] = (((dp[i] + dp[i - 1]) % mod) + dp[i + 1]) % mod;
                }
                if (temp[i] == 0) {
                    break;
                }
            }
            dp = temp;
        }
        return dp[1];
    }
}
