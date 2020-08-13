package com.wz.array;

/**
 * Say you have an array for which the i-th element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [2,4,1], k = 2
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Example 2:
 * Input: [3,2,6,5,0,3], k = 2
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
 *              Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class BestTimeToBuyAndSellStockIV {
    public static void main(String[] args) {
        int[] prices = new int[]{2, 4, 1};
        System.out.println(maxProfit(2, prices));

        prices = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(maxProfit(2, prices));
    }

    /**
     * 局部最优和全局最优解法
     * 维护两个变量：前i天最多进行j次交易，最好利润global[i][j]；前i天最多进行j次交易，并且最后一次交易在当天卖出，最好利润local[i][j]
     * 则存在递推公式：
     * local[i][j] = max(global[i-1][j-1] + max(diff,0), local[i-1][j] + diff)
     * global[i][j] = max(local[i][j], global[i-1][j])
     * 其中 diff = prices[i] - prices[i-1]
     * 全局最优比较好理解，就是当前局部最好和过往全局最好中大的那个，因为最后一次交易如果在当天则一定在局部最优中，否则一定在过往全局最优中
     *                  换个说法就是如果第i天没有交易，就是global[i-1][j]，如果第i天有交易，就是local[i][j]
     * 局部最优也是看两个量，第一个是全局i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（不赚钱则当天买入+卖出，即收益为0）
     *                  第二个是取局部i-1天进行j次交易，然后加上今天的交易（local[i-1][j]肯定存在第i-1天卖出的交易，
     *                  加上diff相当于现在变成第i天卖出，即把第i-1天卖出合并到第i天来卖出，并不会增加交易次数，
     *                  而且这里无论diff是不是大于0都要加上，否则就不满足local[i][j]必须在最后一天卖出的条件）
     * 另外需要注意的是，当k>n/2时也就相当于不限制交易次数，同{@link BestTimeToBuyAndSellStockII}
     */
    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }
        if (k > n / 2) {
            return maxProfitWithoutLimit(prices);
        }

        int[][] local = new int[n][k + 1];
        int[][] global = new int[n][k + 1];
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(profit, 0), local[i - 1][j] + profit);
                global[i][j] = Math.max(local[i][j], global[i - 1][j]);
            }
        }

        return global[n - 1][k];
    }

    private static int maxProfitWithoutLimit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }
}
