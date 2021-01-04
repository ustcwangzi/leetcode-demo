package com.wz.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent,
 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
 * Note:
 * 1. The order of returned grid coordinates does not matter.
 * 2. Both m and n are less than 150.
 *
 * Example:
 * Given the following 5x5 matrix:
 *
 *   Pacific ~   ~   ~   ~   ~
 *        ~  1   2   2   3  (5) *
 *        ~  3   2   3  (4) (4) *
 *        ~  2   4  (5)  3   1  *
 *        ~ (6) (7)  1   4   5  *
 *        ~ (5)  1   1   2   4  *
 *           *   *   *   *   * Atlantic
 *
 * Return:
 * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
 */
public class PacificAtlanticWaterFlow {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4},
        };
        System.out.println(pacificAtlantic(matrix));
    }

    /**
     * 与 {@link SurroundedRegions} 类似
     */
    public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return result;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] pacific = new boolean[m][];
        for (int i = 0; i < m; ++i) {
            pacific[i] = new boolean[n];
        }

        boolean[][] atlantic = new boolean[m][];
        for (int i = 0; i < m; ++i) {
            atlantic[i] = new boolean[n];
        }

        for (int i = 0; i < m; ++i) {
            dfs(matrix, pacific, i, 0);
        }
        for (int j = 1; j < n; ++j) {
            dfs(matrix, pacific, 0, j);
        }
        for (int i = 0; i < m; ++i) {
            dfs(matrix, atlantic, i, n - 1);
        }
        for (int j = 0; j < n - 1; ++j) {
            dfs(matrix, atlantic, m - 1, j);
        }
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private static void dfs(int[][] matrix, boolean[][] ocean, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
        if (ocean[i][j]) return;
        ocean[i][j] = true;
        if (i - 1 >= 0 && matrix[i - 1][j] >= matrix[i][j]) {
            dfs(matrix, ocean, i - 1, j);
        }
        if (i + 1 < m && matrix[i + 1][j] >= matrix[i][j]) {
            dfs(matrix, ocean, i + 1, j);
        }
        if (j - 1 >= 0 && matrix[i][j - 1] >= matrix[i][j]) {
            dfs(matrix, ocean, i, j - 1);
        }
        if (j + 1 < n && matrix[i][j + 1] >= matrix[i][j]) {
            dfs(matrix, ocean, i, j + 1);
        }
    }
}
