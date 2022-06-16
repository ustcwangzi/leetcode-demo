package com.wz.dfs;

/**
 * You are given an m x n integer array grid where grid[i][j] could be:
 *
 * 1 representing the starting square. There is exactly one starting square.
 * 2 representing the ending square. There is exactly one ending square.
 * 0 representing empty squares we can walk over.
 * -1 representing obstacles that we cannot walk over.
 * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.
 *
 *
 *
 * Example 1:
 * @link ../../../../resource/UniquePathsIII1.jpg
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * Output: 2
 * Explanation: We have the following two paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 *
 * Example 2:
 * @link ../../../../resource/UniquePathsIII2.jpg
 * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * Output: 4
 * Explanation: We have the following four paths:
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 *
 * Example 3:
 * @link ../../../../resource/UniquePathsIII3.jpg
 * Input: grid = [[0,1],[2,0]]
 * Output: 0
 * Explanation: There is no path that walks over every empty square exactly once.
 * Note that the starting and ending square can be anywhere in the grid.
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 20
 * 4. 1 <= m * n <= 20
 * 5. -1 <= grid[i][j] <= 2
 * 6. There is exactly one starting cell and one ending cell.
 */
public class UniquePathsIII {
    public static void main(String[] args) {
        System.out.println(uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
        System.out.println(uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}}));
    }

    private static final int[][] DIRS = new int[][]{{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    /**
     * 从 start 到 end 需要经历 count 个节点
     * 从 start 开始 DFS，没经过一个节点 count--
     */
    public static int uniquePathsIII(int[][] grid) {
        int count = 0;
        int[] start = new int[2], target = new int[2];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != -1) {
                    count++;
                }
                if (grid[i][j] == 1) {
                    start = new int[]{i, j};
                } else if (grid[i][j] == 2) {
                    target = new int[]{i, j};
                }
            }
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[start[0]][start[1]] = true;
        return dfs(grid, start, target, visited, count - 1);
    }

    private static int dfs(int[][] grid, int[] cur, int[] target, boolean[][] visited, int count) {
        if (cur[0] == target[0] && cur[1] == target[1] && count == 0) {
            return 1;
        }

        int result = 0;
        for (int[] dir : DIRS) {
            int x = cur[0] + dir[0], y = cur[1] + dir[1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != -1 && !visited[x][y]) {
                visited[x][y] = true;
                result += dfs(grid, new int[]{x, y}, target, visited, count - 1);
                visited[x][y] = false;
            }
        }
        return result;
    }
}
