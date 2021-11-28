package com.wz.hash;

/**
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water,
 * and there is exactly one island (i.e., one or more connected land cells).
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island.
 * One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 * Example 1:
 * @link ../../../../resource/IslandPerimeter.jpg
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 *
 * Example 2:
 * Input: grid = [[1]]
 * Output: 4
 *
 * Example 3:
 * Input: grid = [[1,0]]
 * Output: 4
 *
 * Constraints:
 * 1. row == grid.length
 * 2. col == grid[i].length
 * 3. 1 <= row, col <= 100
 * 4. grid[i][j] is 0 or 1.
 */
public class IslandPerimeter {
    public static void main(String[] args) {
        System.out.println(islandPerimeter(new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0},
        }));
        System.out.println(islandPerimeter(new int[][]{{1}}));
    }

    /**
     * 岛的周长
     * 一个格子有四条边，但是当两个格子相邻，周长为6，若某个格子四周都有格子，可以发现这个格子一条边都不能算在周长里
     * 计算周长，可以对每个格子的四条边分别来处理，首先看左边的边，只有当左边的边处于第一个位置或者当前格子的左面是水的时候，左边的边计入周长，
     * 其他三条边的计算方法都和左边的边相似
     */
    public static int islandPerimeter(int[][] grid) {
        int result = 0, m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                // 上
                if (i == 0 || grid[i - 1][j] == 0) {
                    result++;
                }
                // 下
                if (i == m - 1 || grid[i + 1][j] == 0) {
                    result++;
                }
                // 左
                if (j == 0 || grid[i][j - 1] == 0) {
                    result++;
                }
                // 右
                if (j == n - 1 || grid[i][j + 1] == 0) {
                    result++;
                }
            }
        }
        return result;
    }
}
