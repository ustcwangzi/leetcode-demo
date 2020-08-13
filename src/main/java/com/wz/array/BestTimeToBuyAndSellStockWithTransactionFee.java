package com.wz.array;

/**
 * Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
 * and a non-negative integer fee representing a transaction fee.
 * You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
 * You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
 * Return the maximum profit you can make.
 *
 * Example 1:
 * Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * Output: 8
 * Explanation: The maximum profit can be achieved by:
 * Buying at prices[0] = 1
 * Selling at prices[3] = 8
 * Buying at prices[4] = 4
 * Selling at prices[5] = 9
 * The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {
    public static void main(String[] args) {
        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        System.out.println(maxProfit1(prices, 2));
        System.out.println(maxProfit2(prices, 2));
    }

    /**
     * 动态规划
     * cash[i]: 第i天结束手里没有股票，已经获得的最大收益
     * hold[i]: 第i天结束手里有股票，已经获得的最大收益
     * cash 更新的策略是：今天结束之后手里没有股票，那么可能是今天没买（保持昨天的状态），也可能是今天把股票卖出了
     * 即 cash[i] = max(cash[i-1], hole[i-1] + prices[i] - fee)
     * hold 更新的策略是：今天结束之后手里有股票，那么可能是今天没卖（保持昨天的状态），也可能是今天买了股票
     * 即 hold[i] = max(hold[i-1], cash[i-1] - prices[i])
     */
    public static int maxProfit1(int[] prices, int fee) {
        int[] cash = new int[prices.length];
        int[] hold = new int[prices.length];
        cash[0] = 0;
        hold[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i] - fee);
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
        }

        return cash[prices.length - 1];
    }

    /**
     * 与方案一思路类似，只是进行了空间压缩
     * 从方案一中可以看到今天的cash和hold仅仅依赖于前一天的值，因此可以不用数组
     */
    public static int maxProfit2(int[] prices, int fee) {
        int cash = 0, hold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int preCash = cash;
            cash = Math.max(preCash, hold + prices[i] - fee);
            hold = Math.max(hold, preCash - prices[i]);
        }

        return cash;
    }
}
