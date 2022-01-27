package com.wz.twopointer;

/**
 * You are given an integer array prices representing the daily price history of a stock, where prices[i] is the stock price on the ith day.
 * A smooth descent period of a stock consists of one or more contiguous days such that the price on each day is lower than
 * the price on the preceding day by exactly 1. The first day of the period is exempted from this rule.
 * Return the number of smooth descent periods.
 *
 * Example 1:
 * Input: prices = [3,2,1,4]
 * Output: 7
 * Explanation: There are 7 smooth descent periods:
 * [3], [2], [1], [4], [3,2], [2,1], and [3,2,1]
 * Note that a period with one day is a smooth descent period by the definition.
 *
 * Example 2:
 * Input: prices = [8,6,7,7]
 * Output: 4
 * Explanation: There are 4 smooth descent periods: [8], [6], [7], and [7]
 * Note that [8,6] is not a smooth descent period as 8 - 6 ≠ 1.
 *
 * Example 3:
 * Input: prices = [1]
 * Output: 1
 * Explanation: There is 1 smooth descent period: [1]
 *
 * Constraints:
 * 1. 1 <= prices.length <= 10^5
 * 2. 1 <= prices[i] <= 10^5
 */
public class NumberOfSmoothDescentPeriodsOfStock {
    public static void main(String[] args) {
        System.out.println(getDescentPeriods(new int[]{3, 2, 1, 4}));
    }

    /**
     * 对于每个位置 left，计算以 prices[left] 为起始的最长递减子数组结束位置 right
     * 并将 right-left 加到结果中，否则将 right 赋值给 left，并进行继续遍历
     */
    public static long getDescentPeriods(int[] prices) {
        long result = prices.length, left = 0;
        for (int right = 1; right < prices.length; right++) {
            if (prices[right] == prices[right - 1] - 1) {
                result += right - left;
            } else {
                left = right;
            }
        }
        return result;
    }
}
