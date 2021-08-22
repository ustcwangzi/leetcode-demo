package com.wz.dynamicprogramming;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 * You may assume that you have an infinite number of each kind of coin.
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * Example 1:
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * Example 2:
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Constraints:
 * 1. 1 <= coins.length <= 300
 * 2. 1 <= coins[i] <= 5000
 * 3. All the values of coins are unique.
 * 4. 0 <= amount <= 5000
 */
public class CoinChange2 {
    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1, 2, 5}));
    }

    /**
     * 是 {@link CoinChange} 的变形，{@link CoinChange} 是求最少能用多少硬币组成 amount，本题是组成 amount 有多少种不同的组合方式
     * 依然是动态规划，dp[i] 表示金额为 i 时组合方式个数，逐个遍历 coins，如果当前 coin <= i，就将 dp[i-coin] 累加到 dp[i]，所以状态转移方程为：
     * dp[i] += dp[i-coin]
     */
    public static int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
            }
        }
        return dp[amount];
    }
}
