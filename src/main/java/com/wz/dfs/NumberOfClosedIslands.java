package com.wz.dfs;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected
 * group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 * Return the number of closed islands.
 *
 * Example 1:
 * @see ../../../../resource/NumberOfClosedIslands.jpg
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 *
 * Constraints:
 * 1. 1 <= grid.length, grid[0].length <= 100
 * 2. 0 <= grid[i][j] <=1
 */
public class NumberOfClosedIslands {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 0}
        };
        System.out.println(closedIsland(grid));
    }

    /**
     * 与 {@link SurroundedRegions} 类似
     * 首先将四个边界、以及与边界相连的 0 全都置为 1
     * 然后统计 grid 中 0 的数量，没遇到一个 0 ，将结果加1，同时将与之相连的 0 全都置为 1
     */
    public static int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 第一列和最后一列
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        // 第一行和最后一行
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }

        int result = 0;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (grid[i][j] == 0) {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 0) {
            return;
        }
        grid[i][j] = 1;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
