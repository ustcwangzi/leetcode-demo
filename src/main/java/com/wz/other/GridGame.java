package com.wz.other;

import java.util.Arrays;

/**
 * You are given a 0-indexed 2D array grid of size 2 x n, where grid[r][c] represents the number of points at position (r, c) on the matrix. Two robots are playing a game on this matrix.
 * Both robots initially start at (0, 0) and want to reach (1, n-1). Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).
 * At the start of the game, the first robot moves from (0, 0) to (1, n-1), collecting all the points from the cells on its path.
 * For all cells (r, c) traversed on the path, grid[r][c] is set to 0.
 * Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path. Note that their paths may intersect with one another.
 * The first robot wants to minimize the number of points collected by the second robot.
 * In contrast, the second robot wants to maximize the number of points it collects. If both robots play optimally, return the number of points collected by the second robot.
 *
 * Example 1:
 * @link ../../../../resource/GridGame1.jpg
 * Input: grid = [[2,5,4],[1,5,1]]
 * Output: 4
 * Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
 * The cells visited by the first robot are set to 0.
 * The second robot will collect 0 + 0 + 4 + 0 = 4 points.
 *
 * Example 2:
 * @link ../../../../resource/GridGame2.jpg
 * Input: grid = [[3,3,1],[8,5,2]]
 * Output: 4
 * Explanation: The optimal path taken by the first robot is shown in red, and the optimal path taken by the second robot is shown in blue.
 * The cells visited by the first robot are set to 0.
 * The second robot will collect 0 + 3 + 1 + 0 = 4 points.
 *
 * Constraints:
 * 1. grid.length == 2
 * 2. n == grid[r].length
 * 3. 1 <= n <= 5 * 10^4
 * 4. 1 <= grid[r][c] <= 10^5
 */
public class GridGame {
    public static void main(String[] args) {
        System.out.println(gridGame(new int[][]{{2, 5, 4}, {1, 5, 1}}));
        System.out.println(gridGame(new int[][]{{3, 3, 1}, {8, 5, 2}}));
    }

    /**
     * 只有两行，因此机器人最多能够向下移动一次，所以 robot1 总共有 n 种选择，对于每一种选择，robot2 能够得到的分数为：
     * topSum：robot2 在第一行移动
     * bottomSum：robot2 在第二行移动
     * 即 robot2 的最终得分为 max{topSum, bottomSum}
     * 问题转换为 robot1 的 n 种选择中，哪种能够让 robot2 的最终得分最小 GridGame @link ../../../../resource/GridGame.jpg
     *
     * 注意：使用动态规划最大化 robot1 的得分，并将选择的点全部置为 0，再计算 robot2 的最大得分是不行的，如：
     * 10 50 50 30
     * 50 50 10 10
     * 最大化 robot1 的得分之后：
     * 00 00 00 00
     * 50 50 10 00
     * 此时 robot2 的得分为 110
     * 但如果 robot1 选择的路径是：
     * 00 00 50 30
     * 50 00 00 00
     * 此时 robot2 的得分为 80
     */
    public static long gridGame(int[][] grid) {
        long topSum = Arrays.stream(grid[0]).asLongStream().sum(), bottomSum = 0;
        long result = Long.MAX_VALUE;
        for (int i = 0; i < grid[0].length; i++) {
            topSum -= grid[0][i];
            result = Math.min(result, Math.max(topSum, bottomSum));
            bottomSum += grid[1][i];
        }
        return result;
    }
}
