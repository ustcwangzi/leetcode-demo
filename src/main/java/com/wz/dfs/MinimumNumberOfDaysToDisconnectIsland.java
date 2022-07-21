package com.wz.dfs;

/**
 * You are given an m x n binary grid grid where 1 represents land and 0 represents water.
 * An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.
 * The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
 * In one day, we are allowed to change any single land cell (1) into a water cell (0).
 * Return the minimum number of days to disconnect the grid.
 *
 * Example 1:
 * @link ../../../../resource/MinimumNumberOfDaysToDisconnectIsland1.jpg
 * Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
 * Output: 2
 * Explanation: We need at least 2 days to get a disconnected grid.
 * Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
 *
 * Example 2:
 * @link ../../../../resource/MinimumNumberOfDaysToDisconnectIsland2.jpg
 * Input: grid = [[1,1]]
 * Output: 2
 * Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 30
 * 4. grid[i][j] is either 0 or 1.
 */
public class MinimumNumberOfDaysToDisconnectIsland {
    public static void main(String[] args) {
        System.out.println(minDays(new int[][]{{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}));
        System.out.println(minDays(new int[][]{{1, 1}}));
    }

    /**
     * 是对 {@link NumberOfIslands} 的扩展
     * 总共有三种情况：
     * 1. island 数量不是 1，直接返回 0
     * 2. island 数量是 1，遍历数组，尝试将一个位置反转为 0，然后再计算 island 数量，如果不为 1，则说明反转一个即可，返回 1
     * 3. 情况2中反转一个位置，得不到 island 不为 1 的情况，则说明需要反转两个位置(反转四个中的两个即可分割)，返回 2
     * 注意，情况2中反转、计算 island 之后，需要再进行还原，以免后续计算
     */
    public static int minDays(int[][] grid) {
        if (numIslands(grid) != 1) {
            return 0;
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (numIslands(grid) != 1) {
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private static int numIslands(int[][] grid) {
        int count = 0, m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    count++;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;
        dfs(grid, visited, i - 1, j);
        dfs(grid, visited, i + 1, j);
        dfs(grid, visited, i, j - 1);
        dfs(grid, visited, i, j + 1);
    }
}
