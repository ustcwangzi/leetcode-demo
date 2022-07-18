package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:
 * @link ../../../../resource/MinimumCostToCutStick1.jpg
 * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
 * You should perform the cuts in order, you can change the order of the cuts as you wish.
 * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
 * When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut).
 * Please refer to the first example for a better explanation.
 * Return the minimum total cost of the cuts.
 *
 * Example 1:
 * @link ../../../../resource/MinimumCostToCutStick2.jpg
 * Input: n = 7, cuts = [1,3,4,5]
 * Output: 16
 * Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:
 * @link ../../../../resource/MinimumCostToCutStick3.jpg
 * The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
 * Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).
 *
 * Example 2:
 * Input: n = 9, cuts = [5,6,1,4,2]
 * Output: 22
 * Explanation: If you try the given cuts ordering the cost will be 25.
 * There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.
 *
 * Constraints:
 * 1. 2 <= n <= 10^6
 * 2. 1 <= cuts.length <= min(n - 1, 100)
 * 3. 1 <= cuts[i] <= n - 1
 * 4. All the integers in cuts array are distinct.
 */
public class MinimumCostToCutStick {
    public static void main(String[] args) {
        System.out.println(minCost(7, new int[]{1, 3, 4, 5}));
        System.out.println(minCost(9, new int[]{5, 6, 1, 4, 2}));
    }

    /**
     * 正着做感觉挺麻烦的，不妨反过来考虑，因为不管怎么切，最终的状态是确定的，故可将问题转化为将 n+1 段木条合并，
     * 每次合并的代价为两部分的和，求全部合并后的最小代价，这则是一个典型的区间 dp 问题，dp[i][j] 表示合并区间 i 到 j 的最小代价
     */
    public static int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int m = cuts.length + 1;

        int[] sum = new int[m];
        sum[0] = cuts[0];
        for (int i = 1; i < cuts.length; i++) {
            sum[i] = sum[i - 1] + cuts[i] - cuts[i - 1];
        }
        sum[cuts.length] = n;

        int[][] dp = new int[m][m];
        dp[0][1] = sum[1];
        for (int i = 1; i + 1 < m; i++) {
            dp[i][i + 1] = sum[i + 1] - sum[i - 1];
        }

        for (int l = 2; l < m; l++) {
            for (int i = 0; i + l < m; i++) {
                int j = i + l;
                int segSum = i == 0 ? sum[j] : sum[j] - sum[i - 1];
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = minCost(dp[i][j], dp[i][k] + dp[k + 1][j] + segSum, dp[i][k - 1] + dp[k][j] + segSum);
                }
            }
        }
        return dp[0][m - 1];
    }

    public static int minCost(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        }
        if (b <= a && b <= c) {
            return b;
        }
        return c;
    }
}
