package com.wz.lists;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given the array prices where prices[i] is the price of the ith item in a shop. There is a special discount for items in the shop,
 * if you buy the ith item, then you will receive a discount equivalent to prices[j] where j is the minimum index such
 * that j > i and prices[j] <= prices[i], otherwise, you will not receive any discount at all.
 * Return an array where the ith element is the final price you will pay for the ith item of the shop considering the special discount.
 *
 * Example 1:
 * Input: prices = [8,4,6,2,3]
 * Output: [4,2,4,2,3]
 * Explanation:
 * For item 0 with price[0]=8 you will receive a discount equivalent to prices[1]=4, therefore, the final price you will pay is 8 - 4 = 4.
 * For item 1 with price[1]=4 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 4 - 2 = 2.
 * For item 2 with price[2]=6 you will receive a discount equivalent to prices[3]=2, therefore, the final price you will pay is 6 - 2 = 4.
 * For items 3 and 4 you will not receive any discount at all.
 *
 * Example 2:
 * Input: prices = [1,2,3,4,5]
 * Output: [1,2,3,4,5]
 * Explanation: In this case, for all items, you will not receive any discount at all.
 *
 * Example 3:
 * Input: prices = [10,1,1,6]
 * Output: [9,0,1,6]
 */
public class FinalPricesWithSpecialDiscountInShop {
    public static void main(String[] args) {
        int[] prices = new int[]{8, 4, 6, 2, 3};
        System.out.println(Arrays.toString(finalPrices(prices)));

        prices = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(finalPrices(prices)));

        prices = new int[]{10, 1, 1, 6};
        System.out.println(Arrays.toString(finalPrices(prices)));
    }

    /**
     * 求出每个元素右边第一个小于等于自己的元素 min，然后对于每个 price 来说，最后的结果就是 price-min
     * 求右边第一个小于等于自己的元素可以使用单调栈，只允许大于栈顶的元素入栈，否则弹出栈顶，对于位置 i 来说：
     * 1. 若大于栈顶，则将 i 入栈
     * 2. 否则，弹出栈顶 top，同时得到小于等于 prices[top] 的右边第一个元素就是 prices[i]
     * 重复以上步骤
     * 以 [8,4,6,2,3] 为例说明以上过程
     * i    stack   min
     * 0    [0]
     * 1    [1]     [4]     弹出0，加入1，min[0]=prices[1]
     * 2    [1,2]   [4]     加入2
     * 3    [3]     [4,2,2] 弹出2，min[2]=prices[3]，弹出1，min[1]=prices[3]，加入3
     * 4    [3,4]   [4,2,2] 加入4
     */
    public static int[] finalPrices(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] rightMin = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            while (!stack.empty() && prices[stack.peek()] >= prices[i]) {
                rightMin[stack.pop()] = prices[i];
            }
            stack.push(i);
        }

        for (int i = 0; i < prices.length; i++) {
            prices[i] -= rightMin[i];
        }
        return prices;
    }
}
