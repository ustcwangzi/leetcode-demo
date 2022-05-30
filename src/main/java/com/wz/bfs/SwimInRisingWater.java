package com.wz.bfs;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally
 * adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time.
 * Of course, you must stay within the boundaries of the grid during your swim.
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 * Example 1:
 * @link ../../../../resource/SwimInRisingWater1.jpg
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 * Example 2:
 * @link ../../../../resource/SwimInRisingWater2.jpg
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 * Constraints:
 * 1. n == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= n <= 50
 * 4. 0 <= grid[i][j] < n^2
 * 5. Each value grid[i][j] is unique.
 */
public class SwimInRisingWater {
    public static void main(String[] args) {
        System.out.println(swimInWater(new int[][]{{0, 2}, {1, 3}}));
        System.out.println(swimInWater(new int[][]{{0, 1, 2, 3, 4}, {24, 23, 22, 21, 5}, {12, 13, 14, 15, 16}, {11, 17, 18, 19, 20}, {10, 9, 8, 7, 6}}));
    }

    private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 贪心 + BFS
     * 就是要从 [0,0] 到达 [n-1,n-1]，寻找所有路径中最小的"最大 depth"
     */
    public static int swimInWater(int[][] grid) {
        int n = grid.length, result = 0;
        boolean[][] visited = new boolean[n][n];
        // 每次选择最小的 depth
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        // (i, j, depth)
        queue.offer(new int[]{0, 0, grid[0][0]});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            result = Math.max(result, cur[2]);
            if (cur[0] == n - 1 && cur[1] == n - 1) {
                return result;
            }

            for (int[] dir : DIRS) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x < 0 || x >= n || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y, grid[x][y]});
            }
        }
        return result;
    }
}
