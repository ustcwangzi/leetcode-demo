package com.wz.dynamicprogramming;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *
 * Example:
 * Input: [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class BestTimeToBuyAndSellStockWithCooldown {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1, 2, 3, 0, 2}));
    }

    /**
     * buy[i] 表示第 i 天买股票剩下的最大收益，等于第 i-2 天卖股票剩下的利润 － 第 i 天股票价钱
     * sell[i] 表示第 i 天卖股票剩下的最大收益，等于第 i-1 天买股票剩下的最大利润 ＋ 第 i 天股票价钱
     * 若第 i 天卖出，则第 i+1 天是不能买入的，因此想要第 i 天买入，必须最迟在第 i-2 要卖出
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        int[] buy = new int[n], sell = new int[n];
        buy[0] = -prices[0];
        buy[1] = Math.max(buy[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for (int i = 2; i < n; i++) {
            // 买之前要卖掉之前的股票
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[n - 1];
    }
}
