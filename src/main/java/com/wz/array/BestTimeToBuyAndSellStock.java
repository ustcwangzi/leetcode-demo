package com.wz.array;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 * Example 2:
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices));

        prices = new int[]{7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices));
    }

    /**
     * 动态规划
     * 因为卖出必须在买入之前，因此直接从左到右遍历
     * 记录截止到目前的最低价minPrice作为买入价，以及当前卖出收益：prices[i]-minPrice
     */
    public static int maxProfit(int[] prices) {
        int result = 0;
        if (prices.length < 2) {
            return result;
        }

        // 截止目前最低进价
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            // 根据当前卖出的收益更新结果
            result = Math.max(result, prices[i] - minPrice);
        }
        return result;
    }
}
