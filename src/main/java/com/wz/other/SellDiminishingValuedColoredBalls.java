package com.wz.other;

import java.util.Arrays;

/**
 * You have an inventory of different colored balls, and there is a customer that wants orders balls of any color.
 * The customer weirdly values the colored balls. Each colored ball's value is the number of balls of that color you currently have in your inventory.
 * For example, if you own 6 yellow balls, the customer would pay 6 for the first yellow ball. After the transaction, there are only 5 yellow balls left,
 * so the next yellow ball is then valued at 5 (i.e., the value of the balls decreases as you sell more to the customer).
 * You are given an integer array, inventory, where inventory[i] represents the number of balls of the ith color that you initially own.
 * You are also given an integer orders, which represents the total number of balls that the customer wants. You can sell the balls in any order.
 * Return the maximum total value that you can attain after selling orders colored balls. As the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: inventory = [2,5], orders = 4
 * Output: 14
 * Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
 * The maximum total value is 2 + 5 + 4 + 3 = 14.
 *
 * Example 2:
 * Input: inventory = [3,5], orders = 6
 * Output: 19
 * Explanation: Sell the 1st color 2 times (3 + 2) and the 2nd color 4 times (5 + 4 + 3 + 2).
 * The maximum total value is 3 + 2 + 5 + 4 + 3 + 2 = 19.
 *
 * Constraints:
 * 1. 1 <= inventory.length <= 10^5
 * 2. 1 <= inventory[i] <= 10^9
 * 3. 1 <= orders <= min(sum(inventory[i]), 10^9)
 */
public class SellDiminishingValuedColoredBalls {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2, 5}, 4));
    }

    /**
     * 获取到最大值 inventory[i] 和次大值 inventory[j]，然后将第 i 种球卖出 inventory[i] - inventory[j] 次
     * 接下来会有两个最大值 inventory[i] == inventory[j]，需要再寻找次大值 inventory[k]，然后将第 i 和第 j 种球都卖出 inventory[i] - inventory[k] 次
     */
    public static int maxProfit(int[] inventory, int orders) {
        Arrays.sort(inventory);
        int curIndex = inventory.length - 1, curValue = inventory[curIndex];
        long profit = 0;
        while (orders > 0) {
            while (curIndex >= 0 && inventory[curIndex] == curValue) {
                curIndex--;
            }
            // if all colors of balls are the same value, nextValue is 0
            int nextValue = curIndex < 0 ? 0 : inventory[curIndex];
            // number of colors of balls with same value
            int numSameColor = inventory.length - 1 - curIndex;
            // number of items to buy
            int nums = (curValue - nextValue) * numSameColor;
            if (orders >= nums) {
                // buy all items
                profit += (long) (curValue + nextValue + 1) * (curValue - nextValue) / 2 * numSameColor;
            } else {
                // orders left is less than the number of items to buy
                int numFullRow = orders / numSameColor;
                int remainder = orders % numSameColor;
                profit += (long) (curValue + curValue - numFullRow + 1) * numFullRow / 2 * numSameColor;
                profit += (long) (curValue - numFullRow) * remainder;
            }
            orders -= nums;
            curValue = nextValue;
        }
        return (int) (profit % 1000000007);
    }
}
