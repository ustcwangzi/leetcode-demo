package com.wz.backtracking;

/**
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 * Return the maximum amount of gold you can collect under the conditions:
 * 1. Every time you are located in a cell you will collect all the gold in that cell.
 * 2. From your position you can walk one step to the left, right, up or down.
 * 3. You can't visit the same cell more than once.
 * 4. Never visit a cell with 0 gold.
 * 5. You can start and stop collecting gold from any position in the grid that has some gold.
 *
 * Example 1:
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 *
 * Example 2:
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 *
 * Constraints:
 * 1. 1 <= grid.length, grid[i].length <= 15
 * 2. 0 <= grid[i][j] <= 100
 * 3. There are at most 25 cells containing gold.
 */
public class PathWithMaximumGold {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0, 6, 0},
                {5, 8, 7},
                {0, 9, 0}
        };
        System.out.println(getMaximumGold(grid));
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * DFS
     * 对于每个不为 0 的位置进行DFS，收集 gold，同时为防止重复访问，使用 visited 记录已遍历过的位置
     */
    public static int getMaximumGold(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(result, dfs(grid, visited, i, j));
            }
        }
        return result;
    }

    private static int dfs(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        int nextMax = 0;
        // 上下左右四个位置进行DFS，记录能获取到的最大值 nextMax
        for (int[] dir : DIRS) {
            nextMax = Math.max(nextMax, dfs(grid, visited, i + dir[0], j + dir[1]));
        }
        // 重新标记为未访问，以免影响下一次以其他位置为起点的DFS
        visited[i][j] = false;
        return grid[i][j] + nextMax;
    }
}
