package com.wz.lists;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
 *
 * Example 1:
 * Input: [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 *              Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Example 2:
 * Input: [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
 *              engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Example 3:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args) {
        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(maxProfit(prices));

        prices = new int[]{1, 2, 3, 4, 5};
        System.out.println(maxProfit(prices));

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices));
    }

    /**
     * 动态规划
     * 以i作为分界，计算i之前进行一次交易的最大收益preProfit[i]，以及i之后进行一次交易的最大收益postProfit[i]
     * 最后最大收益是为：max(preProfit[i] + postProfit[i])
     * 计算i之前和i之后进行一次交易的最大收益思路与{@link BestTimeToBuyAndSellStock}类似
     */
    public static int maxProfit(int[] prices) {
        int result = 0;
        if (prices.length < 2) {
            return result;
        }

        // 计算i之前进行一次交易的收益
        int minPrice = prices[0];
        int[] preProfit = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            preProfit[i] = Math.max(preProfit[i - 1], prices[i] - minPrice);
        }

        // 计算i之后进行一次交易的收益
        int maxPrice = prices[prices.length - 1];
        int[] postProfit = new int[prices.length];
        for (int i = prices.length - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            postProfit[i] = Math.max(postProfit[i + 1], maxPrice - prices[i]);
        }

        for (int i = 0; i < prices.length; i++) {
            result = Math.max(result, preProfit[i] + postProfit[i]);
        }
        return result;
    }
}
