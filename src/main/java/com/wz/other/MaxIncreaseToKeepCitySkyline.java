package com.wz.other;

/**
 * There is a city composed of n x n blocks, where each block contains a single building shaped like a vertical square prism.
 * You are given a 0-indexed n x n integer matrix grid where grid[r][c] represents the height of the building located in the block at row r and column c.
 * A city's skyline is the the outer contour formed by all the building when viewing the side of the city from a distance.
 * The skyline from each cardinal direction north, east, south, and west may be different.
 * We are allowed to increase the height of any number of buildings by any amount (the amount can be different per building).
 * The height of a 0-height building can also be increased. However, increasing the height of a building should not affect the city's skyline from any cardinal direction.
 * Return the maximum total sum that the height of the buildings can be increased by without changing the city's skyline from any cardinal direction.
 *
 * Example 1:
 * @see ../../../../resource/MaxIncreaseToKeepCitySkyline.jpg
 * Input: grid = [[3,0,8,4],[2,4,5,7],[9,2,6,3],[0,3,1,0]]
 * Output: 35
 * Explanation: The building heights are shown in the center of the above image.
 * The skylines when viewed from each cardinal direction are drawn in red.
 * The grid after increasing the height of buildings without affecting skylines is:
 * gridNew = [ [8, 4, 8, 7],
 *             [7, 4, 7, 7],
 *             [9, 4, 8, 7],
 *             [3, 3, 3, 3] ]
 *
 * Example 2:
 * Input: grid = [[0,0,0],[0,0,0],[0,0,0]]
 * Output: 0
 * Explanation: Increasing the height of any building will result in the skyline changing.
 *
 * Constraints:
 * 1. n == grid.length
 * 2. n == grid[r].length
 * 3. 2 <= n <= 50
 * 4. 0 <= grid[r][c] <= 100
 */
public class MaxIncreaseToKeepCitySkyline {
    public static void main(String[] args) {
        System.out.println(maxIncreaseKeepingSkyline(new int[][]{
                {8, 4, 8, 7},
                {7, 4, 7, 7},
                {9, 4, 8, 7},
                {3, 3, 3, 3}
        }));
    }

    /**
     * 从四个方向看去，就会有城市的天际线，这个天际线就是由这些建筑的最高的边形成的，在不改变天际线的前提下，最多可以增高建筑的总高度
     * 天际线就是最高的建筑物，即每行或每列最高建筑不能变，而其他建筑物在不超过该高度情况下进行升高
     * 因此首先求出各行各列的最大值，然后就遍历每个建筑，累计所有建筑的可增加高度，就是所求的最大增高
     */
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 每行每列的最大高度
        int[] rows = new int[m], cols = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rows[i] = Math.max(rows[i], grid[i][j]);
                cols[j] = Math.max(cols[j], grid[i][j]);
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 累加最大可增加高度
                count += Math.min(rows[i] - grid[i][j], cols[j] - grid[i][j]);
            }
        }
        return count;
    }
}
