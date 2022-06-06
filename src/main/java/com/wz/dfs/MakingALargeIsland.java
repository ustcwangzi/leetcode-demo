package com.wz.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 *
 * Example 1:
 * Input: grid = [[1,0],[0,1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 *
 * Example 2:
 * Input: grid = [[1,1],[1,0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 *
 * Example 3:
 * Input: grid = [[1,1],[1,1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * Constraints:
 * 1. n == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= n <= 500
 * 4. grid[i][j] is either 0 or 1.
 */
public class MakingALargeIsland {
    public static void main(String[] args) {
        System.out.println(largestIsland(new int[][]{{1, 0}, {0, 1}}));
        System.out.println(largestIsland(new int[][]{{1, 1}, {1, 0}}));
    }

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 对 {@link MaxAreaOfIsland} 的扩展，{@link MaxAreaOfIsland} 直接 DFS 即可
     * 本题可以将一个 0 反转为 1，因为需要先记录每个 island 的大小，然后尝试将与之相邻的 0 反转，并记录最大值
     * 为了方便记录每个 island 的大小，对数组进行 DFS，在 DFS 过程中对同属一个 island 的位置进行染色
     * 然后再次遍历数组，找到每个 0 的所有相邻 island，尝试将当前位置进行反转，并记录反转后的最大值
     */
    public static int largestIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 记录每个 island 的大小
        Map<Integer, Integer> map = new HashMap<>();
        // 因为数组只包含 0、1，因此染色从 2 开始
        int result = 0, color = 2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 1) {
                    continue;
                }

                // 对同一个 island 进行染色
                int count = dfs(grid, i, j, color);
                map.put(color, count);
                result = Math.max(result, count);
                color++;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    continue;
                }

                // 找到每个 0 的所有相邻 island
                Set<Integer> neighbors = new HashSet<>();
                for (int[] dir : DIRS) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != 0) {
                        neighbors.add(grid[x][y]);
                    }
                }

                int count = 1;
                // 尝试将当前位置进行反转，并记录反转后的最大值
                for (int neighbor : neighbors) {
                    count += map.getOrDefault(neighbor, 0);
                }
                result = Math.max(result, count);
            }
        }
        return result;
    }

    private static int dfs(int[][] grid, int i, int j, int color) {
        if (grid[i][j] != 1) {
            return 0;
        }

        int count = 1;
        grid[i][j] = color;
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                count += dfs(grid, x, y, color);
            }
        }
        return count;
    }
}
