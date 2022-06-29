package com.wz.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and (0, 1).
 * The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to reach the lower right corner at (n-1, n-2) and (n-1, n-1).
 * In one move the snake can:
 * - Move one cell to the right if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
 * - Move down one cell if there are no blocked cells there. This move keeps the horizontal/vertical position of the snake as it is.
 * - Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In that case the snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).
 *   @link ../../../../resource/MinimumMovesToReachTargetWithRotations1.jpg
 * - Rotate counterclockwise if it's in a vertical position and the two cells to its right are both empty. In that case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).
 *   @link ../../../../resource/MinimumMovesToReachTargetWithRotations2.jpg
 * Return the minimum number of moves to reach the target.
 * If there is no way to reach the target, return -1.
 *
 * Example 1:
 * @link ../../../../resource/MinimumMovesToReachTargetWithRotations3.jpg
 * Input: grid = [[0,0,0,0,0,1],
 *                [1,1,0,0,1,0],
 *                [0,0,0,0,1,1],
 *                [0,0,1,0,1,0],
 *                [0,1,1,0,0,0],
 *                [0,1,1,0,0,0]]
 * Output: 11
 * Explanation:
 * One possible solution is [right, right, rotate clockwise, right, down, down, down, down, rotate counterclockwise, right, down].
 *
 * Example 2:
 * Input: grid = [[0,0,1,1,1,1],
 *                [0,0,0,0,1,1],
 *                [1,1,0,0,0,1],
 *                [1,1,1,0,0,1],
 *                [1,1,1,0,0,1],
 *                [1,1,1,0,0,0]]
 * Output: 9
 *
 * Constraints:
 * 1. 2 <= n <= 100
 * 2. 0 <= grid[i][j] <= 1
 * 3. It is guaranteed that the snake starts at empty cells.
 */
public class MinimumMovesToReachTargetWithRotations {
    public static void main(String[] args) {
        System.out.println(minimumMoves(new int[][]{
                {0, 0, 0, 0, 0, 1},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 0, 0}
        }));
        System.out.println(minimumMoves(new int[][]{
                {0, 0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 1},
                {1, 1, 1, 0, 0, 0}
        }));
    }

    /**
     * 本质上还是一道迷宫遍历的题目，只不过不再是简单的上下左右四个方向移动，而是变成更为复杂的移动方式
     * 该如何表示蛇的某一个状态，首先蛇是占两个格子，分蛇头和蛇尾，其次，蛇还有水平和竖直两种姿势，可以用 0 表示水平，1 表示竖直，
     * 还有就是蛇的位置也要记录，这里没必要同时记录两个位置，而是只用蛇头或蛇尾位置加上姿势，三个变量组成的状态即可，
     * 那么用蛇头还是蛇尾呢，这里记录蛇尾的位置，因为蛇尾在旋转操作时不会改变，能稍微简单一些
     * 还需要记录已经访问的位置，可以使用三维数组，但比较占用空间，还有一种方法就是直接利用 grid 数组来记录蛇的姿势，
     * 因为原来的 grid 数组只有 0 和 1，即只有最后一位，可以用第二位表示是否是竖直（通过'或'上2来改变状态），第三位表示是否是水平（通过'或'上4来改变状态）
     */
    public static int minimumMoves(int[][] grid) {
        int result = 0, n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0});
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1], dir = cur[2];
                if (x == n - 1 && y == n - 2) {
                    return result;
                }
                if ((grid[x][y] & (dir == 1 ? 2 : 4)) != 0) {
                    continue;
                }
                grid[x][y] |= (dir == 1 ? 2 : 4);
                if (canGoDown(grid, x, y, dir)) {
                    queue.offer(new int[]{x + 1, y, dir});
                }
                if (canGoRight(grid, x, y, dir)) {
                    queue.offer(new int[]{x, y + 1, dir});
                }
                if (canRotate(grid, x, y)) {
                    queue.offer(new int[]{x, y, dir == 0 ? 1 : 0});
                }
            }
            result++;
        }
        return -1;
    }

    /**
     * 若蛇是水平姿势，判断下面两个位置的 grid 值是否越界，且最低位是否为0，因为第二三位可能不为0，所以不能直接判断 grid 值是否为0，而是要'与'上1取出最低位
     * 若蛇是竖直姿势，判断下边一个位置是否越界，且最低位是否为0
     */
    private static boolean canGoDown(int[][] grid, int x, int y, int dir) {
        int n = grid.length;
        if (dir == 0) {
            return x + 1 < n && (grid[x + 1][y] & 1) == 0 && (grid[x + 1][y + 1] & 1) == 0;
        }
        return x + 2 < n && (grid[x + 2][y] & 1) == 0;
    }

    /**
     * 若蛇是水平姿势，判断右边一个位置是否越界，且最低位是否为0
     * 若蛇是竖直姿势，判断右边两个位置的 grid 值是否越界，且最低位是否为0
     */
    private static boolean canGoRight(int[][] grid, int x, int y, int dir) {
        int n = grid.length;
        if (dir == 0) {
            return y + 2 < n && (grid[x][y + 2] & 1) == 0;
        }
        return y + 1 < n && (grid[x][y + 1] & 1) == 0 && (grid[x + 1][y + 1] & 1) == 0;
    }

    /**
     * 不管蛇是水平还是竖直姿势，蛇尾的位置都不变，需要判断蛇尾的右边，下边，和右下边位置的 grid 值的最低位是否为0
     */
    private static boolean canRotate(int[][] grid, int x, int y) {
        int n = grid.length;
        return x + 1 < n && y + 1 < n && (grid[x + 1][y] & 1) == 0 && (grid[x][y + 1] & 1) == 0 && (grid[x + 1][y + 1] & 1) == 0;
    }
}
