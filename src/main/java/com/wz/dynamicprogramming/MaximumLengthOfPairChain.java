package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
 *
 * Example 1:
 * Input: [[1,2], [2,3], [3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4]
 *
 * Note:
 * The number of given pairs will be in the range [1, 1000].
 */
public class MaximumLengthOfPairChain {
    public static void main(String[] args) {
        System.out.println(findLongestChain(new int[][]{{1, 2}, {2, 3}, {3, 4}}));
    }

    /**
     * 动态规划
     * dp[i] 表示以 pairs[i] 为结尾的链表最大长度，对于每个 i，找在 i 前面的 0～j，
     * 如果存在 pairs[j][1] < pairs[i][0]，说明 pairs[j] 可以放在 pairs[i] 前面，更新 dp[i] = max{dp[i], dp[j]+1}
     */
    public static int findLongestChain(int[][] pairs) {
        Arrays.parallelSort(pairs, Comparator.comparingInt(o -> o[1]));
        int n = pairs.length, result = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (pairs[j][1] < pairs[i][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
