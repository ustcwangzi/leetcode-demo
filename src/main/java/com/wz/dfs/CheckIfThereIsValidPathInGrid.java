package com.wz.dfs;

import java.util.*;

/**
 * Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
 * 1 which means a street connecting the left cell and the right cell.
 * 2 which means a street connecting the upper cell and the lower cell.
 * 3 which means a street connecting the left cell and the lower cell.
 * 4 which means a street connecting the right cell and the lower cell.
 * 5 which means a street connecting the left cell and the upper cell.
 * 6 which means a street connecting the right cell and the upper cell.
 * @see ../../../../resource/CheckIfThereIsValidPathInGrid1.jpg
 * You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts
 * from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
 * Notice that you are not allowed to change any street.
 * Return true if there is a valid path in the grid or false otherwise.
 *
 * Example 1:
 * @see ../../../../resource/CheckIfThereIsValidPathInGrid2.jpg
 * Input: grid = [[2,4,3],[6,5,2]]
 * Output: true
 * Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
 *
 * Example 2:
 * @see ../../../../resource/CheckIfThereIsValidPathInGrid3.jpg
 * Input: grid = [[1,2,1],[1,2,1]]
 * Output: false
 * Explanation: As shown you the street at cell (0, 0) is not connected with any street of any other cell and you will get stuck at cell (0, 0)
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 300
 * 4. 1 <= grid[i][j] <= 6
 */
public class CheckIfThereIsValidPathInGrid {
    public static void main(String[] args) {
        System.out.println(hasValidPath(new int[][]{{2, 4, 3}, {6, 5, 2}}));
        System.out.println(hasValidPath(new int[][]{{1, 2, 1}, {1, 2, 1}}));
    }

    private static final Set<Integer> top = new HashSet<>(Arrays.asList(2, 5, 6));
    private static final Set<Integer> bottom = new HashSet<>(Arrays.asList(2, 3, 4));
    private static final Set<Integer> left = new HashSet<>(Arrays.asList(1, 3, 5));
    private static final Set<Integer> right = new HashSet<>(Arrays.asList(1, 4, 6));

    /**
     * DFS
     * 使用四个 set 保存同类型的路径，然后依次进行 DFS
     */
    public static boolean hasValidPath(int[][] grid) {
        return dfs(grid, 0, 0, null);
    }

    private static boolean dfs(int[][] grid, int i, int j, Set<Integer> set) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            return false;
        }

        int type = grid[i][j];
        if (set != null && !set.contains(type)) {
            return false;
        }
        // 到达终点
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }

        // 标记为已遍历
        grid[i][j] = 0;
        // 向上遍历，当前类型是 top，可选类型是 bottom
        if (top.contains(type) && dfs(grid, i - 1, j, bottom)) {
            return true;
        }
        // 向下遍历，当前类型是 bottom，可选类型是 top
        if (bottom.contains(type) && dfs(grid, i + 1, j, top)) {
            return true;
        }
        // 向左遍历，当前类型是 left，可选类型是 right
        if (left.contains(type) && dfs(grid, i, j - 1, right)) {
            return true;
        }
        // 向右遍历，当前类型是 right，可选类型是 left
        return right.contains(type) && dfs(grid, i, j + 1, left);
    }
}
