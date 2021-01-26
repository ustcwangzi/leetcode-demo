package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 * 1. Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * 2. C_1 is at location (0, 0) (ie. has value grid[0][0])
 * 3. C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * 4. If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 *
 * Example 1:
 * Input: [[0,1],[1,0]]
 * @see ../../../../resource/ShortestPathInBinaryMatrix1_1.jpg
 * Output: 2
 * @see ../../../../resource/ShortestPathInBinaryMatrix1_2.jpg
 *
 * Example 2:
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 * @see ../../../../resource/ShortestPathInBinaryMatrix2_1.jpg
 * Output: 4
 * @see ../../../../resource/ShortestPathInBinaryMatrix2_2.jpg
 *
 * Note:
 * 1. 1 <= grid.length == grid[0].length <= 100
 * 2. grid[r][c] is 0 or 1
 */
public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        System.out.println(shortestPathBinaryMatrix(new int[][]{{0, 1}, {1, 0}}));
    }

    private static final int[][] DIRS = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {1, -1}, {-1, -1}};

    /**
     * BFS
     * 将 (0,0,1) 加入队列，(0,0) 代表开始位置，1 代表路径，然后依次出队进行八个方向的遍历，
     * 下一个位置没有出界并且值为 0，则加入队列，加入时将路径长度加一，遍历过程中发现达到终点，则返回路径
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (grid[cur[0]][cur[1]] == 1) {
                continue;
            }
            grid[cur[0]][cur[1]] = 1;
            if (cur[0] == m - 1 && cur[1] == n - 1) {
                return cur[2];
            }

            for (int[] dir : DIRS) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1], cost = cur[2];
                if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                    queue.add(new int[]{x, y, cost + 1});
                }
            }
        }
        return -1;
    }
}
