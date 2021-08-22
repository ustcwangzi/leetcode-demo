package com.wz.dynamicprogramming;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Constraints:
 * 1. 1 <= coins.length <= 12
 * 2. 1 <= coins[i] <= 231 - 1
 * 3. 0 <= amount <= 104
 */
public class CoinChange {
    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }

    /**
     * 动态规划
     * dp[i] 表示金额为 i 时所需硬币个数
     * dp[0] = 0，表示无需硬币，其他值可以初始化为 amount+1，因为最小硬币是 1，所以最多需要 amount 个硬币，amount+1 也就相当于当前的最大值
     * 更新 dp[i] 的方法就是遍历每个硬币，如果遍历到的硬币值 coin <= i，就用 dp[i-coin] + 1 来更新 dp[i]，所以状态转移方程为：
     * dp[i] = min(dp[i], dp[i-coin] + 1)
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = amount + 1;
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
