package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * There are n cities connected by m flights. Each flight starts from city u and arrives at v with a price w.
 * Now given all the cities and flights, together with starting city src and the destination dst,
 * your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph looks like this:
 * @link ../../../../resource/CheapestFlightsWithinKStops.jpg
 * The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 *
 * Example 2:
 * Input:
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph looks like this:
 * @link ../../../../resource/CheapestFlightsWithinKStops.jpg
 * The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 * Constraints:
 * 1. The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 * 2. The size of flights will be in range [0, n * (n - 1) / 2].
 * 3. The format of each flight will be (src, dst, price).
 * 4. The price of each flight will be in the range [1, 10000].
 * 5. k is in the range of [0, n - 1].
 * 6. There will not be any duplicated flights or self cycles.
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        System.out.println(findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 1));
    }

    /**
     * 动态规划
     * dp[k][i]，其中dp[k][i]意思是最多飞k次航班，飞到目的地为i的地方的费用最少是多少，初始化dp[0][src]=0，dp[i][src]=0
     * 遍历所有 flights，每个 flight，dp[i][flight[1]] = min{dp[i][flight[1]], dp[i-1][flight[0]] + flight[2]}
     * 表示最多飞 k 次航班到达目的地 i 的费用等于 最多飞 k 次到达目的地 i 的费用 和 最多飞 k-1 次到达目的地 i 的前一站的费用
     *  + 目的地 i 的前一站飞到目的地的费用 中取最小，最后返回 dp[K+1][dst]
     */
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[K + 2][n];
        int max = 1000000000;
        Arrays.stream(dp).forEach(array -> Arrays.fill(array, max));
        dp[0][src] = 0;

        for (int i = 1; i <= K + 1; i++) {
            dp[i][src] = 0;
            for (int[] flight : flights) {
                dp[i][flight[1]] = Math.min(dp[i][flight[1]], dp[i - 1][flight[0]] + flight[2]);
            }
        }
        return dp[K + 1][dst] == max ? -1 : dp[K + 1][dst];
    }
}
