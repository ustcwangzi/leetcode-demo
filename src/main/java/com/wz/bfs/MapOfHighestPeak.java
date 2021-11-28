package com.wz.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
 * 1. If isWater[i][j] == 0, cell (i, j) is a land cell.
 * 2. If isWater[i][j] == 1, cell (i, j) is a water cell.
 * You must assign each cell a height in a way that follows these rules:
 * 1. The height of each cell must be non-negative.
 * 2. If the cell is a water cell, its height must be 0.
 * 3. Any two adjacent cells must have an absolute height difference of at most 1.
 * A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 * Find an assignment of heights such that the maximum height in the matrix is maximized.
 * Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.
 *
 * Example 1:
 * @link ../../../../resource/MapOfHighestPeak1.jpg
 * Input: isWater = [[0,1],[0,0]]
 * Output: [[1,0],[2,1]]
 * Explanation: The image shows the assigned heights of each cell.
 * The blue cell is the water cell, and the green cells are the land cells.
 *
 * Example 2:
 * @link ../../../../resource/MapOfHighestPeak2.jpg
 * Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * Output: [[1,1,0],[0,1,1],[1,2,2]]
 * Explanation: A height of 2 is the maximum possible height of any assignment.
 * Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
 *
 * Constraints:
 * 1. m == isWater.length
 * 2. n == isWater[i].length
 * 3. 1 <= m, n <= 1000
 * 4. isWater[i][j] is 0 or 1.
 * 5. There is at least one water cell.
 */
public class MapOfHighestPeak {
    public static void main(String[] args) {
        int[][] result = highestPeak(new int[][]{{0, 1}, {0, 0}});
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }

        result = highestPeak(new int[][]{{0, 0, 1}, {1, 0, 0}, {0, 0, 0}});
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * BFS
     * 将水域(isWater == 1)高度设置为 0，然后从水域开始，遍历周围未设置的格子，将其高度记为 h + 1，逐层添加直至所有格子处理完成
     * 每遍历一层，高度加一
     */
    public static int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        boolean[][] visited = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        int high = 0;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                isWater[cur[0]][cur[1]] = high;
                // 访问相邻节点
                for (int[] dir : dirs) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            high++;
        }
        return isWater;
    }
}
