package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are n points on a road you are driving your taxi on. The n points on the road are labeled from 1 to n in the direction you are going,
 * and you want to drive from point 1 to point n to make money by picking up passengers. You cannot change the direction of the taxi.
 * The passengers are represented by a 0-indexed 2D integer array rides, where rides[i] = [starti, endi, tipi] denotes the ith passenger
 * requesting a ride from point starti to point endi who is willing to give a tipi dollar tip.
 * For each passenger i you pick up, you earn endi - starti + tipi dollars. You may only drive at most one passenger at a time.
 * Given n and rides, return the maximum number of dollars you can earn by picking up the passengers optimally.
 * Note: You may drop off a passenger and pick up a different passenger at the same point.
 *
 * Example 1:
 * Input: n = 5, rides = [[2,5,4],[1,5,1]]
 * Output: 7
 * Explanation: We can pick up passenger 0 to earn 5 - 2 + 4 = 7 dollars.
 *
 * Example 2:
 * Input: n = 20, rides = [[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]
 * Output: 20
 * Explanation: We will pick up the following passengers:
 * - Drive passenger 1 from point 3 to point 10 for a profit of 10 - 3 + 2 = 9 dollars.
 * - Drive passenger 2 from point 10 to point 12 for a profit of 12 - 10 + 3 = 5 dollars.
 * - Drive passenger 5 from point 13 to point 18 for a profit of 18 - 13 + 1 = 6 dollars.
 * We earn 9 + 5 + 6 = 20 dollars in total.
 *
 * Constraints:
 * 1. 1 <= n <= 10^5
 * 2. 1 <= rides.length <= 3 * 10^4
 * 3. rides[i].length == 3
 * 4. 1 <= starti < endi <= n
 * 5. 1 <= tipi <= 10^5
 */
public class MaximumEarningsFromTaxi {
    public static void main(String[] args) {
        System.out.println(maxTaxiEarnings(5, new int[][]{{2, 5, 4}, {1, 5, 1}}));
    }

    /**
     * dp[i] 代表到达 i 时获得的最大利润，对 rides 按照终点进行排序，然后遍历每个位置 1～n
     * 对于每个位置 i，从 rides 中选择到达 i 的最大利润
     */
    public static long maxTaxiEarnings(int n, int[][] rides) {
        Arrays.parallelSort(rides, Comparator.comparingInt(o -> o[1]));
        long[] dp = new long[n + 1];
        int j = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            while (j < rides.length && rides[j][1] == i) {
                int[] ride = rides[j];
                // 因为不能重叠，若当前选择为 ride，则前一个的终点最大是 ride[0]，利润是 dp[ride[0]]
                dp[i] = Math.max(dp[i], dp[ride[0]] + ride[1] - ride[0] + ride[2]);
                j++;
            }
        }
        return dp[n];
    }
}
