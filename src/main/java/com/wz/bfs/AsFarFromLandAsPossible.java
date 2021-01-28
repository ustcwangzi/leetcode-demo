package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land,
 * find a water cell such that its distance to the nearest land cell is maximized, and return the distance.
 * If no land or water exists in the grid, return -1.
 * The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.
 *
 * Example 1:
 * 1    0    1
 * 0    0    0
 * 1    0    1
 * Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
 * Output: 2
 * Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
 *
 * Example 2:
 * 1    0    0
 * 0    0    0
 * 0    0    0
 * Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
 * Output: 4
 * Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 *
 * Constraints:
 * 1. n == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= n <= 100
 * 4. grid[i][j] is 0 or 1
 */
public class AsFarFromLandAsPossible {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1},
        };
        System.out.println(maxDistance(grid));
    }

    /**
     * 与 {@link ShortestPathInBinaryMatrix} 类似
     * 先将所有值为 1 的坐标入队，然后依次出队，向周围四个方向的 0 扩散，并且把 0 变为 1，标记为已遍历
     * 每遍历一个节点，就将距离加一，最后返回结果
     */
    public static int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 坐标、距离
                    queue.add(new int[]{i, j, 0});
                }
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int result = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            result = Math.max(result, cur[2]);
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                    grid[x][y] = 1;
                    queue.add(new int[]{x, y, cur[2] + 1});
                }
            }
        }
        return result == 0 ? -1 : result;
    }
}
