package com.wz.dynamicprogramming;

/**
 * Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
 * You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) ,
 * and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
 * Return the maximum number of cherries collection using both robots  by following the rules below:
 * From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
 * When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
 * When both robots stay on the same cell, only one of them takes the cherries.
 * Both robots cannot move outside of the grid at any moment.
 * Both robots should reach the bottom row in the grid.
 *
 * Example 1:
 * @link ../../../../resource/CherryPickupII1.jpg
 * Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
 * Output: 24
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
 * Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
 * Total of cherries: 12 + 12 = 24.
 *
 * Example 2:
 * @link ../../../../resource/CherryPickupII2.jpg
 * Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
 * Output: 28
 * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
 * Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
 * Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
 * Total of cherries: 17 + 11 = 28.
 *
 * Constraints:
 * 1. rows == grid.length
 * 2. cols == grid[i].length
 * 3. 2 <= rows, cols <= 70
 * 4. 0 <= grid[i][j] <= 100
 */
public class CherryPickupII {
    public static void main(String[] args) {
        System.out.println(cherryPickup(new int[][]{
                {3, 1, 1},
                {2, 5, 1},
                {1, 5, 5},
                {2, 1, 1},
        }));
    }

    public static int cherryPickup(int[][] grid) {
        int n = grid[0].length;
        int[][] dp = new int[n][n], old = new int[n][n];
        for (int r = grid.length - 1; r >= 0; r--) {
            for (int c1 = Math.min(r, n - 1); c1 >= 0; c1--) {
                for (int c2 = Math.max(c1, n - 1 - r); c2 < n; c2++) {
                    int max = 0;
                    for (int i = c1 - 1; i <= c1 + 1; i++) {
                        for (int j = c2 - 1; j <= c2 + 1; j++) {
                            if (i >= 0 && j >= 0 && j < n && i <= j) {
                                max = Math.max(max, old[i][j]);
                            }
                        }
                    }
                    dp[c1][c2] = max + grid[r][c1] + (c1 == c2 ? 0 : grid[r][c2]);
                }
            }
            int[][] temp = dp;
            dp = old;
            old = temp;
        }
        return old[0][n - 1];
    }
}
