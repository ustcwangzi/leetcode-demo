package com.wz.dfs;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
 * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
 * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 * Example 1:
 * @see ../../../../resource/PathWithMinimumEffort1.jpg
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 *
 * Example 2:
 * @see ../../../../resource/PathWithMinimumEffort2.jpg
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 *
 * Example 3:
 * @see ../../../../resource/PathWithMinimumEffort3.jpg
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 * Constraints:
 * 1. rows == heights.length
 * 2. columns == heights[i].length
 * 3. 1 <= rows, columns <= 100
 * 4. 1 <= heights[i][j] <= 106
 */
public class PathWithMinimumEffort {
    public static void main(String[] args) {
        int[][] heights = new int[][]{
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5},
        };
        System.out.println(minimumEffortPath(heights));

        heights = new int[][]{
                {1, 2, 1, 1, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1},
                {1, 1, 1, 2, 1},
        };
        System.out.println(minimumEffortPath(heights));
    }

    private static final int[][] DIRS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 二分法 + BFS
     * 首先给定一个代价 effort，然后判断它是否能够到达终点，若能则缩小 effort，若不能则放大 effort
     * 判断是否能够到达终点采用 BFS，对于每个坐标 (i,j) 都可以进行上下左右四个方向移动，若移动后的 (x,y) 满足 effort，则加入队列
     */
    public static int minimumEffortPath(int[][] heights) {
        int left = 0, right = (int) Math.pow(10, 6);
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (bfs(heights, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean bfs(int[][] heights, int effort) {
        int m = heights.length, n = heights[0].length;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        queue.add(new Pair<>(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> cur = queue.poll();
            int i = cur.getKey(), j = cur.getValue();
            // 到达终点
            if (i == m - 1 && j == n - 1) {
                return true;
            }
            // 对每个坐标进行上下左右四个方向移动
            for (int[] dir : DIRS) {
                int x = i + dir[0], y = j + dir[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }

                int diff = Math.abs(heights[x][y] - heights[i][j]);
                // 满足 effort 要求
                if (diff <= effort) {
                    queue.add(new Pair<>(x, y));
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }
}
