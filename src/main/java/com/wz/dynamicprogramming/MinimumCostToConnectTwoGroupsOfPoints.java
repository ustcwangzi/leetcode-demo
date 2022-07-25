package com.wz.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given two groups of points where the first group has size1 points, the second group has size2 points, and size1 >= size2.
 * The cost of the connection between any two points are given in an size1 x size2 matrix where cost[i][j]
 * is the cost of connecting point i of the first group and point j of the second group.
 * The groups are connected if each point in both groups is connected to one or more points in the opposite group.
 * In other words, each point in the first group must be connected to at least one point in the second group,
 * and each point in the second group must be connected to at least one point in the first group.
 * Return the minimum cost it takes to connect the two groups.
 *
 * Example 1:
 * @link ../../../../resource/MinimumCostToConnectTwoGroupsOfPoints1.jpg
 * Input: cost = [[15, 96], [36, 2]]
 * Output: 17
 * Explanation: The optimal way of connecting the groups is:
 * 1--A
 * 2--B
 * This results in a total cost of 17.
 *
 * Example 2:
 * @link ../../../../resource/MinimumCostToConnectTwoGroupsOfPoints2.jpg
 * Input: cost = [[1, 3, 5], [4, 1, 1], [1, 5, 3]]
 * Output: 4
 * Explanation: The optimal way of connecting the groups is:
 * 1--A
 * 2--B
 * 2--C
 * 3--A
 * This results in a total cost of 4.
 * Note that there are multiple points connected to point 2 in the first group and point A in the second group.
 * This does not matter as there is no limit to the number of points that can be connected. We only care about the minimum total cost.
 *
 * Example 3:
 * Input: cost = [[2, 5, 1], [3, 4, 7], [8, 1, 2], [6, 2, 4], [3, 8, 8]]
 * Output: 10
 *
 * Constraints:
 * 1. size1 == cost.length
 * 2. size2 == cost[i].length
 * 3. 1 <= size1, size2 <= 12
 * 4. size1 >= size2
 * 5. 0 <= cost[i][j] <= 100
 */
public class MinimumCostToConnectTwoGroupsOfPoints {
    public static void main(String[] args) {
        List<List<Integer>> cost = new ArrayList<>();
        cost.add(Arrays.asList(15, 96));
        cost.add(Arrays.asList(36, 2));
        System.out.println(connectTwoGroups(cost));

        cost = new ArrayList<>();
        cost.add(Arrays.asList(1, 3, 5));
        cost.add(Arrays.asList(4, 1, 1));
        cost.add(Arrays.asList(1, 5, 3));
        System.out.println(connectTwoGroups(cost));
    }

    /**
     * 要求每行、每列都至少有一个元素相连，动态规划，dp[i][s] 表示左侧的 i 个元素与右侧集合的最小 cost，s 表示右侧集合的 bitmask
     * 那么显然 dp[0][0] = 0，最终结果为 dp[m][(1<<n)-1]，状态转移为：
     * dp[i][s | 1<<j] = min{dp[i][s | 1<<j],          // 遍历所有 j
     *                       dp[i][s] + cost[i][j],    // i 可以多次使用
     *                       dp[i-1][s] + cost[i][j]}  // 由 i-1 过来
     */
    public static int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size();
        int[][] dp = new int[m + 1][1 << n];
        Arrays.stream(dp).forEach(array -> Arrays.fill(array, Integer.MAX_VALUE / 2));
        dp[0][0] = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int mask = 0; mask < 1 << n; mask++) {
                    dp[i + 1][mask | 1 << j] = Math.min(dp[i + 1][mask | 1 << j],
                            Math.min(dp[i + 1][mask] + cost.get(i).get(j), dp[i][mask] + cost.get(i).get(j))
                    );
                }
            }
        }
        return dp[m][(1 << n) - 1];
    }
}
