package com.wz.dfs;

import java.util.Arrays;

/**
 * You are given an m x n binary grid, where each 1 represents a brick and 0 represents an empty space. A brick is stable if:
 * - It is directly connected to the top of the grid, or
 * - At least one other brick in its four adjacent cells is stable.
 * You are also given an array hits, which is a sequence of erasures we want to apply.
 * Each time we want to erase the brick at the location hits[i] = (rowi, coli).
 * The brick on that location (if it exists) will disappear. Some other bricks may no longer be stable because of that erasure and will fall.
 * Once a brick falls, it is immediately erased from the grid (i.e., it does not land on other stable bricks).
 *
 * Return an array result, where each result[i] is the number of bricks that will fall after the ith erasure is applied.
 * Note that an erasure may refer to a location with no brick, and if it does, no bricks drop.
 *
 * Example 1:
 * Input: grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
 * Output: [2]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 *  [1,1,1,0]]
 * We erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 *  [0,1,1,0]]
 * The two underlined bricks are no longer stable as they are no longer connected to the top nor adjacent to another stable brick, so they will fall. The resulting grid is:
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * Hence the result is [2].
 *
 * Example 2:
 * Input: grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
 * Output: [0,0]
 * Explanation: Starting with the grid:
 * [[1,0,0,0],
 *  [1,1,0,0]]
 * We erase the underlined brick at (1,1), resulting in the grid:
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * All remaining bricks are still stable, so no bricks fall. The grid remains the same:
 * [[1,0,0,0],
 *  [1,0,0,0]]
 * Next, we erase the underlined brick at (1,0), resulting in the grid:
 * [[1,0,0,0],
 *  [0,0,0,0]]
 * Once again, all remaining bricks are still stable, so no bricks fall.
 * Hence the result is [0,0].
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 200
 * 4. grid[i][j] is 0 or 1.
 * 5. 1 <= hits.length <= 4 * 10^4
 * 6. hits[i].length == 2
 * 7. 0 <= xi <= m - 1
 * 8. 0 <= yi <= n - 1
 * 9. All (xi, yi) are unique.
 */
public class BricksFallingWhenHit {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(hitBricks(new int[][]{{1, 0, 0, 0}, {1, 1, 1, 0}}, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(hitBricks(new int[][]{{1, 0, 0, 0}, {1, 1, 0, 0}}, new int[][]{{1, 1}, {1, 0}})));
    }

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 先把所有该消的都消掉，消掉后和第一行连通的点都是'稳定点'，把这些点进行标记
     * 然后按消除顺序反过来遍历进行还原，当把 (i,j) 恢复之后，再计算'稳定点'个数
     * 假如'稳定点'个数上涨了 x 个，说明消除 (i,j) 使得有 x-1 个点从'稳定点'变得不稳定，那也就是这次消除后消失的数量
     */
    public static int[] hitBricks(int[][] grid, int[][] hits) {
        // 1 -> 0, 0 -> -1
        for (int[] hit : hits) {
            grid[hit[0]][hit[1]] -= 1;
        }
        // 标记'稳定点'
        for (int j = 0; j < grid[0].length; j++) {
            dfs(grid, 0, j);
        }

        int[] result = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int[] hit = hits[i];
            // 还原
            grid[hit[0]][hit[1]] += 1;
            if (grid[hit[0]][hit[1]] == 1 && stable(grid, hit[0], hit[1])) {
                // 需要减去自身，因为自身会被消除
                result[i] = dfs(grid, hit[0], hit[1]) - 1;
            }
        }
        return result;
    }

    private static int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = 2;
        int result = 1;
        for (int[] dir : DIRS) {
            result += dfs(grid, i + dir[0], j + dir[1]);
        }
        return result;
    }

    private static boolean stable(int[][] grid, int i, int j) {
        if (i == 0) {
            return true;
        }
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 2) {
                return true;
            }
        }
        return false;
    }
}
