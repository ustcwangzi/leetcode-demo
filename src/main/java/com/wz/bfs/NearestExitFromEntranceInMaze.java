package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+').
 * You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.
 * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze.
 * Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.
 * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
 *
 * Example 1:
 * @link ../../../../resource/NearestExitFromEntranceInMaze1.jpg
 * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * Output: 1
 * Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
 * Initially, you are at the entrance cell [1,2].
 * - You can reach [1,0] by moving 2 steps left.
 * - You can reach [0,2] by moving 1 step up.
 * It is impossible to reach [2,3] from the entrance.
 * Thus, the nearest exit is [0,2], which is 1 step away.
 *
 * Example 2:
 * @link ../../../../resource/NearestExitFromEntranceInMaze2.jpg
 * Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
 * Output: 2
 * Explanation: There is 1 exit in this maze at [1,2].
 * [1,0] does not count as an exit since it is the entrance cell.
 * Initially, you are at the entrance cell [1,0].
 * - You can reach [1,2] by moving 2 steps right.
 * Thus, the nearest exit is [1,2], which is 2 steps away.
 *
 * Example 3:
 * @link ../../../../resource/NearestExitFromEntranceInMaze3.jpg
 * Input: maze = [[".","+"]], entrance = [0,0]
 * Output: -1
 * Explanation: There are no exits in this maze.
 *
 * Constraints:
 * 1. maze.length == m
 * 2. maze[i].length == n
 * 3. 1 <= m, n <= 100
 * 4. maze[i][j] is either '.' or '+'.
 * 5. entrance.length == 2
 * 6. 0 <= entrancerow < m
 * 7. 0 <= entrancecol < n
 * 8. entrance will always be an empty cell.
 */
public class NearestExitFromEntranceInMaze {
    public static void main(String[] args) {
        char[][] maze = new char[][]{
                {'+', '+', '.', '+'},
                {'.', '.', '.', '+'},
                {'+', '+', '+', '.'}
        };
        System.out.println(nearestExit(maze, new int[]{1, 2}));

        maze = new char[][]{
                {'+', '+', '+'},
                {'.', '.', '.'},
                {'+', '+', '+'}
        };
        System.out.println(nearestExit(maze, new int[]{1, 0}));
    }

    /**
     * BFS
     * 寻找最近的出口，从入口处开始进行 BFS，依次遍历所有可达位置
     */
    public static int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        visited[entrance[0]][entrance[1]] = true;

        int result = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int row = queue.peek()[0], col = queue.poll()[1];
                int[][] neighbors = new int[][]{{row + 1, col}, {row - 1, col}, {row, col + 1}, {row, col - 1}};
                for (int[] neighbor : neighbors) {
                    if (neighbor[0] < 0 || neighbor[0] >= m || neighbor[1] < 0 || neighbor[1] >= n
                            || maze[neighbor[0]][neighbor[1]] == '+' || visited[neighbor[0]][neighbor[1]]) {
                        continue;
                    }

                    // 到达边界，说明找到了出口
                    if (neighbor[0] == 0 || neighbor[0] == m - 1 || neighbor[1] == 0 || neighbor[1] == n - 1) {
                        return result;
                    }
                    visited[neighbor[0]][neighbor[1]] = true;
                    queue.offer(neighbor);
                }
            }
            result++;
        }
        return -1;
    }
}
