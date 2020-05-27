package com.wz.lists;

/**
 * A robot is located at the top-left corner of a m x n grid.
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 * Now consider if some obstacles(num 1) are added to the grids. How many unique paths would there be?
 * <p>
 * Example 1:
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 */
public class UniquePathsII {
    public static void main(String[] args) {
        int[][] obstacleGrid = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));

        obstacleGrid = new int[][]{
                {0, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));

        obstacleGrid = new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0}
        };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    /**
     * 思路与{@link UniquePaths}类似
     * 只是计算dp[i][j]时，需要考虑obstacleGrid[i][j]是否是1，是1则直接跳过
     * 以及计算第一行和第一列时，前一个路径数是否为0，为0则直接跳过
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        // 第一列
        for (int i = 1; i < m; i++) {
            if (dp[i - 1][0] != 0 && obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            }
        }

        // 第一行
        for (int j = 1; j < n; j++) {
            if (dp[0][j - 1] != 0 && obstacleGrid[0][j] != 1) {
                dp[0][j] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
