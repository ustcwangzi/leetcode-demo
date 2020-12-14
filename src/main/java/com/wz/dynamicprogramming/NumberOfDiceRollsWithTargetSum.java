package com.wz.dynamicprogramming;

/**
 * You have d dice, and each die has f faces numbered 1, 2, ..., f.
 * Return the number of possible ways (out of fd total ways) modulo 10^9 + 7 to roll the dice so the sum of the face up numbers equals target.
 *
 * Example 1:
 * Input: d = 1, f = 6, target = 3
 * Output: 1
 * Explanation:
 * You throw one die with 6 faces.  There is only one way to get a sum of 3.
 *
 * Example 2:
 * Input: d = 2, f = 6, target = 7
 * Output: 6
 * Explanation:
 * You throw two dice, each with 6 faces.  There are 6 ways to get a sum of 7:
 * 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 *
 * Constraints:
 * 1. 1 <= d, f <= 30
 * 2. 1 <= target <= 1000
 */
public class NumberOfDiceRollsWithTargetSum {
    public static void main(String[] args) {
        System.out.println(numRollsToTarget(2, 6, 7));
    }

    /**
     * dp[i][j] 表示用前 i 个骰子得到总和 j 的方法总数
     * dp[0][0] = 1，表示没有掷骰子时，得到 0 的方法数为 1
     * 对于其他情况，枚举这一次掷的骰子的点数
     * dp[i][j] = sum(dp[i-1][j-k]) (1 <= k <= f and k <= j)
     */
    public static int numRollsToTarget(int d, int f, int target) {
        int mod = 1000000007;
        long[][] dp = new long[d + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= d; i++) {
            for (int j = 1; j <= target; j++) {
                for (int k = 1; k <= f && k <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                    dp[i][j] %= mod;
                }
            }
        }

        return (int) dp[d][target];
    }
}
