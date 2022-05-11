package com.wz.bfs;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 * Example 1:
 * @link ../../../../resource/TrappingRainWaterII1.jpg
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 *
 * Example 2:
 * @link ../../../../resource/TrappingRainWaterII2.jpg
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 * Constraints:
 * 1. m == heightMap.length
 * 2. n == heightMap[i].length
 * 3. 1 <= m, n <= 200
 * 4. 0 <= heightMap[i][j] <= 2 * 10^4
 */
public class TrappingRainWaterII {
    public static void main(String[] args) {
        System.out.println(trapRainWater(new int[][]{{1, 4, 3, 1, 3, 2}, {3, 2, 1, 3, 2, 4}, {2, 3, 3, 2, 3, 1}}));
        System.out.println(trapRainWater(new int[][]{{3, 3, 3, 3, 3}, {3, 2, 2, 2, 3}, {3, 2, 1, 2, 3}, {3, 2, 2, 2, 3}, {3, 3, 3, 3, 3}}));
    }

    /**
     * BFS + PriorityQueue
     * 每个 Cell 蓄多少水是由它最高的一圈外围决定的，又取决于那圈外围中最矮的高度，因此每次都是以最小高度往里面传递，从最外层开始进行 BFS
     * 先把第一行、最后一行、第一列、最后一列加入堆中，然后从堆中依次取出堆顶，对当前堆顶 cur 进行 BFS，计算四个方向传递过来的水量
     */
    public static int trapRainWater(int[][] heightMap) {
        int m = heightMap.length, n = heightMap[0].length;
        if (m <= 2 || n <= 2) {
            return 0;
        }

        Queue<Cell> queue = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        // 第一列和最后一列
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n - 1] = true;
            queue.offer(new Cell(i, 0, heightMap[i][0]));
            queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
        }
        // 第一行和最后一行
        for (int j = 1; j < n; j++) {
            visited[0][j] = true;
            visited[m - 1][j] = true;
            queue.offer(new Cell(0, j, heightMap[0][j]));
            queue.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
        }

        int result = 0;
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            // 计算四个方向传递过来的水量
            for (int[] dir : dirs) {
                int row = cur.row + dir[0], col = cur.col + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    result += Math.max(0, cur.height - heightMap[row][col]);
                    // 注意要将最大高度进行传递
                    queue.offer(new Cell(row, col, Math.max(cur.height, heightMap[row][col])));
                }
            }
        }
        return result;
    }

    private static class Cell implements Comparable<Cell> {
        int row;
        int col;
        int height;

        public Cell(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        @Override
        public int compareTo(Cell other) {
            return this.height - other.height;
        }
    }
}
