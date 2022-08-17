package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * Given n cuboids where the dimensions of the ith cuboid is cuboids[i] = [widthi, lengthi, heighti] (0-indexed).
 * Choose a subset of cuboids and place them on each other.
 * You can place cuboid i on cuboid j if widthi <= widthj and lengthi <= lengthj and heighti <= heightj.
 * You can rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
 * Return the maximum height of the stacked cuboids.
 *
 * Example 1:
 * @link ../../../../resource/MaximumHeightByStackingCuboids.jpg
 * Input: cuboids = [[50,45,20],[95,37,53],[45,23,12]]
 * Output: 190
 * Explanation:
 * Cuboid 1 is placed on the bottom with the 53x37 side facing down with height 95.
 * Cuboid 0 is placed next with the 45x20 side facing down with height 50.
 * Cuboid 2 is placed next with the 23x12 side facing down with height 45.
 * The total height is 95 + 50 + 45 = 190.
 *
 * Example 2:
 * Input: cuboids = [[38,25,45],[76,35,3]]
 * Output: 76
 * Explanation:
 * You can't place any of the cuboids on the other.
 * We choose cuboid 1 and rotate it so that the 35x3 side is facing down and its height is 76.
 *
 * Example 3:
 * Input: cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
 * Output: 102
 * Explanation:
 * After rearranging the cuboids, you can see that all cuboids have the same dimension.
 * You can place the 11x7 side down on all cuboids so their heights are 17.
 * The maximum height of stacked cuboids is 6 * 17 = 102.
 *
 * Constraints:
 * 1. n == cuboids.length
 * 2. 1 <= n <= 100
 * 3. 1 <= widthi, lengthi, heighti <= 100
 */
public class MaximumHeightByStackingCuboids {
    public static void main(String[] args) {
        System.out.println(maxHeight(new int[][]{{50, 45, 20}, {95, 37, 53}, {45, 23, 12}}));
        System.out.println(maxHeight(new int[][]{{38, 25, 45}, {76, 35, 3}}));
        System.out.println(maxHeight(new int[][]{{7, 11, 17}, {7, 17, 11}, {11, 7, 17}, {11, 17, 7}, {17, 7, 11}, {17, 11, 7}}));
    }

    /**
     * 是对 {@link LongestIncreasingSubsequence} 的扩展
     * 为了保持 width, length, height 是对应的，先对每个 cuboid 内部进行排序，然后为了求出 LIS，再对整体 cuboids 进行逆序排序
     * 接着遍历排序后的 cuboids，dp[i] 表示使用 cuboids[i] 能够得到的最大高度
     * 对于每个 dp[i] 先初始化为 cuboids[i][2]，即只使用自身的最大高度
     * 再遍历所有之前的 cuboid，若 width, length, height 均比自己大，则可以进行替换，取 max{dp[i], dp[j] + cuboids[i][2]}
     */
    public static int maxHeight(int[][] cuboids) {
        for (int[] cuboid : cuboids) {
            Arrays.parallelSort(cuboid);
        }
        Arrays.parallelSort(cuboids, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o2[0], o1[0]);
            }
            if (o1[1] != o2[1]) {
                return Integer.compare(o2[1], o1[1]);
            }
            return Integer.compare(o2[2], o1[2]);
        });

        int n = cuboids.length, result = cuboids[0][2];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[i][0] <= cuboids[j][0] && cuboids[i][1] <= cuboids[j][1] && cuboids[i][2] <= cuboids[j][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
