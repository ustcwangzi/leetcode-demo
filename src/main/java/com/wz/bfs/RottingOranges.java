package com.wz.bfs;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n grid where each cell can have one of three values:
 * 1. 0 representing an empty cell,
 * 2. 1 representing a fresh orange, or
 * 3. 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * Example 1:
 * @link ../../../../resource/RottingOranges.jpg
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 10
 * 4. grid[i][j] is 0, 1, or 2.
 */
public class RottingOranges {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(orangesRotting(grid));
    }

    /**
     * BFS
     * 先统计所有橘子的数量 total，然后将状态为 2 的加入队列，队列非空则循环，每次循环时将队列的 size 累加到腐败的数量 rotten 中
     * 如果 rotten 达到 total 则直接返回，每循环一次将时间加一，否则依次出队，并将当前坐标的上、下、左、右四个方向的橘子设置为 2，并入队
     */
    public static int orangesRotting(int[][] grid) {
        int total = 0, rotten = 0, result = 0;
        int m = grid.length, n = grid[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    total++;
                }
                if (grid[i][j] == 2) {
                    queue.add(new Pair<>(i, j));
                }
            }
        }
        if (total == 0) {
            return 0;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            rotten += size;
            if (rotten == total) {
                return result;
            }

            result++;
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> cur = queue.poll();
                // 下
                int x = cur.getKey() + 1;
                if (x < m && grid[x][cur.getValue()] == 1) {
                    grid[x][cur.getValue()] = 2;
                    queue.add(new Pair<>(x, cur.getValue()));
                }

                // 上
                x = cur.getKey() - 1;
                if (x >= 0 && grid[x][cur.getValue()] == 1) {
                    grid[x][cur.getValue()] = 2;
                    queue.add(new Pair<>(x, cur.getValue()));
                }

                // 右
                int y = cur.getValue() + 1;
                if (y < n && grid[cur.getKey()][y] == 1) {
                    grid[cur.getKey()][y] = 2;
                    queue.add(new Pair<>(cur.getKey(), y));
                }

                // 左
                y = cur.getValue() - 1;
                if (y >= 0 && grid[cur.getKey()][y] == 1) {
                    grid[cur.getKey()][y] = 2;
                    queue.add(new Pair<>(cur.getKey(), y));
                }
            }
        }
        return -1;
    }
}
