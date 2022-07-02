package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.
 * The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.
 * Your task is to move the box 'B' to the target position 'T' under the following rules:
 * - The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
 * - The character '.' represents the floor which means a free cell to walk.
 * - The character '#' represents the wall which means an obstacle (impossible to walk there).
 * - There is only one box 'B' and one target cell 'T' in the grid.
 * - The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. This is a push.
 * - The player cannot walk through the box.
 * Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.
 *
 * Example 1:
 * @link ../../../../resource/MinimumMovesToMoveBoxToTheirTargetLocation.jpg
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#",".","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 3
 * Explanation: We return only the number of times the box is pushed.
 *
 * Example 2:
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T","#","#","#","#"],
 *                ["#",".",".","B",".","#"],
 *                ["#","#","#","#",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: -1
 *
 * Example 3:
 * Input: grid = [["#","#","#","#","#","#"],
 *                ["#","T",".",".","#","#"],
 *                ["#",".","#","B",".","#"],
 *                ["#",".",".",".",".","#"],
 *                ["#",".",".",".","S","#"],
 *                ["#","#","#","#","#","#"]]
 * Output: 5
 * Explanation: push the box down, left, left, up and up.
 *
 * Constraints:
 * 1. m == grid.length
 * 2. n == grid[i].length
 * 3. 1 <= m, n <= 20
 * 4. grid contains only characters '.', '#', 'S', 'T', or 'B'.
 * 5. There is only one character 'S', 'B', and 'T' in the grid.
 */
public class MinimumMovesToMoveBoxToTheirTargetLocation {
    public static void main(String[] args) {
        System.out.println(minPushBox(new char[][]{
                {'#', '#', '#', '#', '#', '#'},
                {'#', 'T', '#', '#', '#', '#'},
                {'#', '.', '.', 'B', '.', '#'},
                {'#', '.', '#', '#', '.', '#'},
                {'#', '.', '.', '.', 'S', '#'},
                {'#', '#', '#', '#', '#', '#'}
        }));
    }

    private static final int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 求到达目标位置的最小步数还是首选 BFS，不过此时的状态不再单单是箱子的位置，还应该包括人的位置，二者一起组成 BFS 中的状态
     * 同时，要进入下一个状态的前提条件是，人必须要能够达到指定的推箱子的位置，这个判定就需要一个额外的 BFS 来做了，所以需要两个 BFS
     */
    public static int minPushBox(char[][] grid) {
        int[] start = new int[]{-1, -1}, end = new int[]{-1, -1}, player = new int[]{-1, -1};
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 'B') {
                    start = new int[]{i, j};
                } else if (grid[i][j] == 'T') {
                    end = new int[]{i, j};
                } else if (grid[i][j] == 'S') {
                    player = new int[]{i, j};
                }
            }
        }

        int step = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], player[0], player[1]});
        boolean[][][] visited = new boolean[grid.length][grid[0].length][4];
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                if (cur[0] == end[0] && cur[1] == end[1]) {
                    return step;
                }

                for (int i = 0; i < DIRS.length; i++) {
                    if (visited[cur[0]][cur[1]][i]) {
                        continue;
                    }
                    int[] dir = DIRS[i];
                    int r0 = cur[0] + dir[0], c0 = cur[1] + dir[1];
                    if (r0 < 0 || r0 >= grid.length || c0 < 0 || c0 >= grid[0].length || grid[r0][c0] == '#') {
                        continue;
                    }
                    int r = cur[0] - dir[0], c = cur[1] - dir[1];
                    if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == '#') {
                        continue;
                    }
                    // 判断 player 能否达到新的位置
                    if (!reachable(grid, r0, c0, cur)) {
                        continue;
                    }
                    visited[cur[0]][cur[1]][i] = true;
                    queue.offer(new int[]{r, c, cur[0], cur[1]});
                }
            }
            step++;
        }
        return -1;
    }

    private static boolean reachable(char[][] grid, int i, int j, int[] location) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{location[2], location[3]});
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[location[0]][location[1]] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == i && cur[1] == j) {
                return true;
            }
            for (int[] dir : DIRS) {
                int x = cur[0] - dir[0], y = cur[1] - dir[1];
                if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visited[x][y] || grid[x][y] == '#') {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
        return false;
    }
}
