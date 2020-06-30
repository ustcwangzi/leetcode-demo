package com.wz.lists;

/**
 * A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that
 * each row, column, and both diagonals all have the same sum.
 * Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
 * Note:
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * 0 <= grid[i][j] <= 15
 *
 * Example 1:
 * Input: [[4,3,8,4],
 *         [9,5,1,9],
 *         [2,7,6,2]]
 * Output: 1
 * Explanation:
 * The following subgrid is a 3 x 3 magic square:
 * 438
 * 951
 * 276
 *
 * while this one is not:
 * 384
 * 519
 * 762
 *
 * In total, there is only one magic square inside the given grid.
 */
public class MagicSquaresInGrid {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {4, 3, 8, 4},
                {9, 5, 1, 9},
                {2, 7, 6, 2}
        };
        System.out.println(numMagicSquaresInside(grid));
    }

    /**
     * 正方形的各行各列及对角线之和必须是15才行，而且最中间的位置必须是5，否则根本无法组成满足要求的正方形
     * 因此遍历所有的3x3大小的正方形，先验证下该正方形中的数字是否只有1到9中的数字，且不能由重复出现，
     * 使用一个数组来标记出现过的数字，若当前数字已经出现了，直接返回true。接着计算各行各列及对角线之和是否为15
     */
    public static int numMagicSquaresInside(int[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length - 2; ++i) {
            for (int j = 0; j < grid[0].length - 2; ++j) {
                if (valid(i, j, grid)) {
                    result++;
                }
            }
        }
        return result;
    }

    private static boolean valid(int i, int j, int[][] grid) {
        if (grid[i + 1][j + 1] != 5) {
            return false;
        }

        int[] array = new int[10];
        for (int x = i; x < i + 2; ++x) {
            for (int y = j; y < j + 2; ++y) {
                int k = grid[x][y];
                if (k < 1 || k > 9 || array[k] == 1) {
                    return false;
                }
                array[k] = 1;
            }
        }

        if (15 != grid[i][j] + grid[i][j + 1] + grid[i][j + 2]) {
            return false;
        }
        if (15 != grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2]) {
            return false;
        }
        if (15 != grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2]) {
            return false;
        }
        if (15 != grid[i][j] + grid[i + 1][j] + grid[i + 2][j]) {
            return false;
        }
        if (15 != grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1]) {
            return false;
        }
        if (15 != grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2]) {
            return false;
        }
        if (15 != grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2]) {
            return false;
        }
        if (15 != grid[i + 2][j] + grid[i + 1][j + 1] + grid[i][j + 2]) {
            return false;
        }
        return true;
    }
}
