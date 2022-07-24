package com.wz.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * There is a strange printer with the following two special requirements:
 * - On each turn, the printer will print a solid rectangular pattern of a single color on the grid.
 *   This will cover up the existing colors in the rectangle.
 * - Once the printer has used a color for the above operation, the same color cannot be used again.
 * You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.
 * Return true if it is possible to print the matrix targetGrid, otherwise, return false.
 *
 * Example 1:
 * @link ../../../../resource/StrangePrinterII1.jpg
 * Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
 * Output: true
 *
 * Example 2:
 * @link ../../../../resource/StrangePrinterII2.jpg
 * Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
 * Output: true
 *
 * Example 3:
 * Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
 * Output: false
 * Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different turns.
 *
 * Constraints:
 * 1. m == targetGrid.length
 * 2. n == targetGrid[i].length
 * 3. 1 <= m, n <= 60
 * 4. 1 <= targetGrid[row][col] <= 60
 */
public class StrangePrinterII {
    public static void main(String[] args) {
        System.out.println(isPrintable(new int[][]{{1, 1, 1, 1}, {1, 2, 2, 1}, {1, 2, 2, 1}, {1, 1, 1, 1}}));
        System.out.println(isPrintable(new int[][]{{1, 2, 1}, {2, 1, 2}, {1, 2, 1}}));
        System.out.println(isPrintable(new int[][]{{1, 1, 1, 1}, {1, 1, 3, 3}, {1, 1, 3, 4}, {5, 5, 1, 4}}));
    }

    private static final int MAX = 61;

    /**
     * 先找到每个颜色所处坐标的范围 (minI, minJ, maxI, maxJ)
     * 然后遍历所有颜色，已经打印过的直接跳过，否则进行 DFS
     * 若发现新的颜色并且已经打印过则返回 false，否则对新的颜色进行 DFS
     */
    public static boolean isPrintable(int[][] targetGrid) {
        Set<Integer> colors = new HashSet<>();
        int[][] colorRange = buildColorRange(targetGrid, colors);
        boolean[] printed = new boolean[MAX];
        boolean[][] visited = new boolean[targetGrid.length][targetGrid[0].length];
        for (int color : colors) {
            if (printed[color] || dfs(targetGrid, colorRange, printed, visited, color)) {
                continue;
            }
            return false;
        }
        return true;
    }

    private static boolean dfs(int[][] grid, int[][] colorRange, boolean[] printed, boolean[][] visited, int color) {
        printed[color] = true;
        for (int i = colorRange[color][0]; i <= colorRange[color][2]; i++) {
            for (int j = colorRange[color][1]; j <= colorRange[color][3]; j++) {
                if (visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                // 发现新颜色
                if (grid[i][j] != color) {
                    // 已打印直接返回 false
                    if (printed[grid[i][j]]) {
                        return false;
                    }
                    if (!dfs(grid, colorRange, printed, visited, grid[i][j])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static int[][] buildColorRange(int[][] grid, Set<Integer> colors) {
        // (minI, minJ, maxI, maxJ)
        int[][] colorRange = new int[MAX][4];
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0 || j == 1) {
                    colorRange[i][j] = Integer.MAX_VALUE;
                } else {
                    colorRange[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int color = grid[i][j];
                colors.add(color);
                colorRange[color][0] = Math.min(colorRange[color][0], i);
                colorRange[color][1] = Math.min(colorRange[color][1], j);
                colorRange[color][2] = Math.max(colorRange[color][2], i);
                colorRange[color][3] = Math.max(colorRange[color][3], j);
            }
        }
        return colorRange;
    }
}
