package com.wz.sort;

import java.util.Arrays;

/**
 * There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:
 * 1. In each step, you will choose any 3 piles of coins (not necessarily consecutive).
 * 2. Of your choice, Alice will pick the pile with the maximum number of coins.
 * 3. You will pick the next pile with maximum number of coins.
 * 4. Your friend Bob will pick the last pile.
 * 5. Repeat until there are no more piles of coins.
 * Given an array of integers piles where piles[i] is the number of coins in the ith pile.
 * Return the maximum number of coins which you can have.
 *
 * Example 1:
 * Input: piles = [2,4,1,2,7,8]
 * Output: 9
 * Explanation: Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins, you the pile with 7 coins and Bob the last one.
 * Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins, you the pile with 2 coins and Bob the last one.
 * The maximum number of coins which you can have are: 7 + 2 = 9.
 * On the other hand if we choose this arrangement (1, 2, 8), (2, 4, 7) you only get 2 + 4 = 6 coins which is not optimal.
 *
 * Example 2:
 * Input: piles = [9,8,7,6,5,1,2,3,4]
 * Output: 18
 *
 * Constraints:
 * 1. 3 <= piles.length <= 10^5
 * 2. piles.length % 3 == 0
 * 3. 1 <= piles[i] <= 10^4
 */
public class MaximumNumberOfCoinsYouCanGet {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{9, 8, 7, 6, 5, 1, 2, 3, 4}));
    }

    /**
     * 每次从 piles 中选出 最大值、次大值和最小值，最大值给 Alice，最小值给 Bob，自己拿次大值，直到 piles 中全部元素分配完成
     */
    public static int maxCoins(int[] piles) {
        Arrays.parallelSort(piles);
        int result = 0, i = piles.length - 2, n = piles.length / 3;
        while (n-- > 0) {
            result += piles[i];
            i -= 2;
        }
        return result;
    }
}
