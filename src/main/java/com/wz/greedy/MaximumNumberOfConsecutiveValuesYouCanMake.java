package com.wz.greedy;

import java.util.Arrays;

/**
 * You are given an integer array coins of length n which represents the n coins that you own. The value of the ith coin is coins[i].
 * You can make some value x if you can choose some of your n coins such that their values sum up to x.
 * Return the maximum number of consecutive integer values that you can make with your coins starting from and including 0.
 * Note that you may have multiple coins of the same value.
 *
 * Example 1:
 * Input: coins = [1,3]
 * Output: 2
 * Explanation: You can make the following values:
 * - 0: take []
 * - 1: take [1]
 * You can make 2 consecutive integer values starting from 0.
 *
 * Example 2:
 * Input: coins = [1,1,1,4]
 * Output: 8
 * Explanation: You can make the following values:
 * - 0: take []
 * - 1: take [1]
 * - 2: take [1,1]
 * - 3: take [1,1,1]
 * - 4: take [4]
 * - 5: take [4,1]
 * - 6: take [4,1,1]
 * - 7: take [4,1,1,1]
 * You can make 8 consecutive integer values starting from 0.
 *
 * Constraints:
 * 1. coins.length == n
 * 2. 1 <= n <= 4 * 10^4
 * 3. 1 <= coins[i] <= 4 * 10^4
 */
public class MaximumNumberOfConsecutiveValuesYouCanMake {
    public static void main(String[] args) {
        System.out.println(getMaximumConsecutive(new int[]{1, 1, 1, 4}));
    }

    /**
     * 硬币之和组成连续的整数
     * 排序 + 前缀和，如果当前硬币与前缀和之差超过 1，就不连续了
     */
    public static int getMaximumConsecutive(int[] coins) {
        Arrays.parallelSort(coins);
        int sum = 0;
        for (int coin : coins) {
            if (coin - sum > 1) {
                return sum + 1;
            }
            sum += coin;
        }
        return sum + 1;
    }
}
