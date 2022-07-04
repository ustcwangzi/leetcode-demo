package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
 * You can move up, down, left, or right from and to an empty cell in one step.
 * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1)
 * given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.
 *
 * Example 1:
 * @link ../../../../resource/ShortestPathInGridWithObstaclesElimination1.jpg
 * Input: grid = [[0,0,0],[1,1,0],[0,0,0],[0,1,1],[0,0,0]], k = 1
 * Output: 6
 * Explanation:
 * The shortest path without eliminating any obstacle is 10.
 * The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
 *
 * Example 2:
 * @link ../../../../resource/ShortestPathInGridWithObstaclesElimination2.jpg
 * Input: grid = [[0,1,1],[1,1,1],[1,0,0]], k = 1
 * Output: -1
 * Explanation: We need to eliminate at least two obstacles to find such a walk.
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 40
 * 4. 1 <= k <= m * n
 * 5. grid[i][j] is either 0 or 1.
 * 6. grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class ShortestPathInGridWithObstaclesElimination {
    public static void main(String[] args) {
        System.out.println(shortestPath(new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 0}}, 1));
        System.out.println(shortestPath(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 0, 0}}, 1));
    }

    private static final int[][] DIRS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * 在普通 BFS 的基础上增加了一个维度来标识当前消除的障碍物个数
     */
    public static int shortestPath(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length, step = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        boolean[][][] visited = new boolean[m][n][k + 1];
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (cur[0] == m - 1 && cur[1] == n - 1) {
                    return step;
                }

                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1], z = cur[2];
                    if (x < 0 || x >= m || y < 0 || y >= n) {
                        continue;
                    }

                    z += grid[x][y];
                    if (z <= k && !visited[x][y][z]) {
                        queue.offer(new int[]{x, y, z});
                        visited[x][y][z] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
