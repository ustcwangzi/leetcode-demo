package com.wz.dfs;

/**
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 * Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 *
 * Example 1:
 * @link ../../../../resource/DetectCyclesIn2DGrid1.jpg
 * Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles shown in different colors in the image below:
 * @link ../../../../resource/DetectCyclesIn2DGrid2.jpg
 *
 * Example 2:
 * @link ../../../../resource/DetectCyclesIn2DGrid3.jpg
 * Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 * Output: true
 * Explanation: There is only one valid cycle highlighted in the image below:
 * @link ../../../../resource/DetectCyclesIn2DGrid4.jpg
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 500
 * 4. grid consists only of lowercase English letters.
 */
public class DetectCyclesIn2DGrid {
    public static void main(String[] args) {
        System.out.println(containsCycle(new char[][]{
                {'a', 'a', 'a', 'a'},
                {'a', 'b', 'b', 'a'},
                {'a', 'b', 'b', 'a'},
                {'a', 'a', 'a', 'a'}
        }));
        System.out.println(containsCycle(new char[][]{
                {'c', 'c', 'c', 'a'},
                {'c', 'd', 'c', 'c'},
                {'c', 'c', 'e', 'c'},
                {'f', 'c', 'c', 'c'},
        }));
        System.out.println(containsCycle(new char[][]{
                {'a', 'b', 'b'},
                {'b', 'z', 'b'},
                {'b', 'b', 'a'}
        }));
    }

    /**
     * DFS
     * 遍历过程中记录已经遍历过的节点，再次遇到就说明有环
     * 注意，遍历过程中需要记录到达当前节点的上一个节点位置，因为只能往下一个节点走而不能重复回到上一个节点，以免死循环
     */
    public static boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && dfs(i, j, i, j, grid, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 使用 preI、preJ 记录到达当前 i、j 的上一个位置
     */
    private static boolean dfs(int i, int j, int preI, int preJ, char[][] grid, boolean[][] visited) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != grid[preI][preJ]) {
            return false;
        }
        if (visited[i][j]) {
            return true;
        }

        visited[i][j] = true;
        // 下一个位置不能和上一个位置重复
        return ((i + 1 != preI || j != preJ) && dfs(i + 1, j, i, j, grid, visited))
                || ((i - 1 != preI || j != preJ) && dfs(i - 1, j, i, j, grid, visited))
                || ((i != preI || j + 1 != preJ) && dfs(i, j + 1, i, j, grid, visited))
                || ((i != preI || j - 1 != preJ) && dfs(i, j - 1, i, j, grid, visited));
    }
}
