package com.wz.dynamicprogramming;

/**
 * There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n),
 * some houses that has been painted last summer should not be painted again.
 * A neighborhood is a maximal group of continuous houses that are painted with the same color.
 * (For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods  [{1}, {2,2}, {3,3}, {2}, {1,1}]).
 * Given an array houses, an m * n matrix cost and an integer target where:
 * houses[i]: is the color of the house i, 0 if the house is not painted yet.
 * cost[i][j]: is the cost of paint the house i with the color j+1.
 * Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods,
 * if not possible return -1.
 *
 * Example 1:
 * Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 9
 * Explanation: Paint houses of this way [1,2,2,1,1]
 * This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
 * Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
 *
 * Example 2:
 * Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
 * Output: 11
 * Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
 * This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
 * Cost of paint the first and last house (10 + 1) = 11.
 *
 * Example 3:
 * Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[1,10],[10,1],[1,10]], m = 5, n = 2, target = 5
 * Output: 5
 *
 * Constraints:
 * 1. m == houses.length == cost.length
 * 2. n == cost[i].length
 * 3. 1 <= m <= 100
 * 4. 1 <= n <= 20
 * 5. 1 <= target <= m
 * 6. 0 <= houses[i] <= n
 * 7. 1 <= cost[i][j] <= 10^4
 */
public class PaintHouseIII {
    public static void main(String[] args) {
        System.out.println(minCost(new int[]{0, 0, 0, 0, 0}, new int[][]{
                {1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}
        }, 5, 2, 3));
    }

    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int MAX = Integer.MAX_VALUE / 2;
        int[][][] dp = new int[m][n][m + 1];
        for (int k = 0; k <= m; k++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (k == 0 || k > i + 1) {
                        dp[i][j][k] = MAX;
                    } else if (i == 0) {
                        if (houses[i] != 0) {
                            dp[i][j][k] = j + 1 == houses[i] ? 0 : MAX;
                        } else {
                            dp[i][j][k] = cost[i][j];
                        }
                    } else {
                        // current house is painted, current house cannot be other color.
                        if (houses[i] != 0) {
                            if (j + 1 != houses[i]) {
                                dp[i][j][k] = MAX;
                            } else {
                                dp[i][j][k] = dp[i - 1][j][k];
                                for (int l = 0; l < n; l++) {
                                    if (l == j) {
                                        continue;
                                    }
                                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][l][k - 1]);
                                }
                            }
                        } else {
                            // same color with the last one
                            dp[i][j][k] = dp[i - 1][j][k] + cost[i][j];
                            for (int l = 0; l < n; l++) {
                                if (l == j) {
                                    continue;
                                }
                                dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][l][k - 1] + cost[i][j]);
                            }
                        }
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            result = Math.min(result, dp[m - 1][j][target]);
        }
        return result >= MAX ? -1 : result;
    }
}
