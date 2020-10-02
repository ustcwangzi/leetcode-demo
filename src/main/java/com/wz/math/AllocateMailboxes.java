package com.wz.math;

import java.util.Arrays;

/**
 * Given the array houses and an integer k. where houses[i] is the location of the ith house along a street,
 * your task is to allocate k mailboxes in the street.
 * Return the minimum total distance between each house and its nearest mailbox.
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 * Example 1:
 * Input: houses = [1,4,8,10,20], k = 3
 * Output: 5
 * Explanation: Allocate mailboxes in position 3, 9 and 20.
 * Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
 *
 * Example 2:
 * Input: houses = [2,3,5,12,18], k = 2
 * Output: 9
 * Explanation: Allocate mailboxes in position 3 and 14.
 * Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.
 */
public class AllocateMailboxes {
    public static void main(String[] args) {
        System.out.println(minDistance(new int[]{1, 4, 8, 10, 20}, 3));
        System.out.println(minDistance(new int[]{2, 3, 5, 12, 18}, 2));
    }

    /**
     * dp[i][j] 表示前 i 个房子放 j 个邮箱的最小距离
     * distance[i][j] 表示从房子 i 个到房子 j 放一个邮箱的得到的最小距离
     * 则 dp[i][1] = distance[0][i]
     * 转移方程为 dp[i][j] = min{min(dp[i][j], dp[m−1][j−1] + distance[m][i]}
     * 表示前 k−1 个房子放 j−1 个邮箱，然后区间 [k,i] 这些房间用一个邮箱的最短距离
     * 最后结果为 dp[n-1][k]
     */
    public static int minDistance(int[] houses, int k) {
        Arrays.parallelSort(houses);
        int n = houses.length;
        int[][] dp = new int[n][k + 1], distance = new int[n][n];

        // 放在两个房子中间时，距离最小
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int mid = i + j >>> 1;
                for (int m = i; m <= j; m++) {
                    distance[i][j] += Math.abs(houses[m] - houses[mid]);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
            dp[i][1] = distance[0][i];
        }

        for (int i = 1; i < n; i++) {
            // j是个数，但i是下标
            for (int j = 2; j <= k && j <= i + 1; j++) {
                // m 枚举的是最后一个邮箱的覆盖范围的起点，前 j−1 个邮箱各自负责一个房间的时候，最后一个邮箱会覆盖最多房间
                for (int m = j - 1; m <= i; m++) {
                    dp[i][j] = Math.min(dp[i][j], dp[m - 1][j - 1] + distance[m][i]);
                }
            }
        }
        return dp[n - 1][k];
    }
}
