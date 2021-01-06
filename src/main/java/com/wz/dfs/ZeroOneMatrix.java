package com.wz.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * Example 1:
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [0,0,0]]
 *
 * Example 2:
 * Input:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,1,1]]
 * Output:
 * [[0,0,0],
 *  [0,1,0],
 *  [1,2,1]]
 *
 * Note:
 * 1. The number of elements of the given matrix will not exceed 10,000.
 * 2. There are at least one 0 in the given matrix.
 * 3. The cells are adjacent in only four directions: up, down, left and right.
 */
public class ZeroOneMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        updateMatrix(matrix);
        for (int[] array : matrix) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * BFS
     * 首先遍历矩阵，将值为 0 的点都存入 queue，因为所有为 0 的点都是起点，将值为 1 的点改为 MAX_VALUE
     * 然后开始 BFS 遍历，从 queue 中取出一个数字，遍历其周围四个点，
     * 如果没有越界并且周围点的值大于当前值加 1，将周围点的值更新为当前值加 1，然后把周围点的坐标加入 queue
     * 否则直接跳过，因为周围点的距离更小的话，就没有更新的必要
     */
    public static int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[][] dirs = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] dir : dirs) {
                int x = cell[0] + dir[0], y = cell[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < n && matrix[cell[0]][cell[1]] + 1 < matrix[x][y]) {
                    matrix[x][y] = matrix[cell[0]][cell[1]] + 1;
                    queue.add(new int[]{x, y});
                }
            }
        }

        return matrix;
    }
}
