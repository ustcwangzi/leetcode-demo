package com.wz.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
 * In one shift operation:
 * Element at grid[i][j] moves to grid[i][j + 1].
 * Element at grid[i][n - 1] moves to grid[i + 1][0].
 * Element at grid[m - 1][n - 1] moves to grid[0][0].
 * Return the 2D grid after applying shift operation k times.
 *
 * Example 1:
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * Output: [[9,1,2],[3,4,5],[6,7,8]]
 *
 * Example 2:
 * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 *
 * Example 3:
 * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * Output: [[1,2,3],[4,5,6],[7,8,9]]
 */
public class Shift2DGrid {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        System.out.println(shiftGrid(grid, 1));

        grid = new int[][]{
                {3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}
        };
        System.out.println(shiftGrid(grid, 4));

        grid = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        System.out.println(shiftGrid(grid, 9));
    }

    /**
     * 记grid的行数为 m，列数为 n，显然经过 m*n 次移动后和不移动效果是一样的，所以可以先令 k = k%(m*n)
     * 剩下的k就是每一个元素需要移动的次数，将 grid 展开成一维数组，移动之后拼接结果
     */
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        k %= m * n;
        int[][] tmp = new int[m][n];
        // 将二维数组展开为一维数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = (i * n + j) + k;
                index = index < m * n ? index : index - m * n;
                // 移动后的位置
                tmp[index / n][index % n] = grid[i][j];
            }
        }

        List<List<Integer>> result = new ArrayList<>(n);
        for (int[] array : tmp) {
            List<Integer> row = new ArrayList<>(m);
            for (int value : array) {
                row.add(value);
            }
            result.add(row);
        }

        return result;
    }
}
