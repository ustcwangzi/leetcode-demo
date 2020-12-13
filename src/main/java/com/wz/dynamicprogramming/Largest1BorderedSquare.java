package com.wz.dynamicprogramming;

/**
 * Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border,
 * or 0 if such a subgrid doesn't exist in the grid.
 *
 * Example 1:
 * Input: grid = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: 9
 *
 * Example 2:
 * Input: grid = [[1,1,0,0]]
 * Output: 1
 *
 * Constraints:
 * 1. 1 <= grid.length <= 100
 * 2. 1 <= grid[0].length <= 100
 * 3. grid[i][j] is 0 or 1
 */
public class Largest1BorderedSquare {
    public static void main(String[] args) {
        System.out.println(largest1BorderedSquare(new int[][]{
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        }));
    }

    /**
     * left[i][j] 表示第 i 行前 j 列数字之和，top[i][j] 表示第 j 列前 i 行数字之和，等同于位置 (i,j) 向左、向上的最大延伸长度
     * 然后对于每个可能的长度 k，判断四条边的区间和是不是都大于等于 k
     */
    public static int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m][n], top = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
                }
            }
        }

        for (int k = Math.min(m, n); k > 0; --k) {
            for (int i = 0; i < m - k + 1; ++i) {
                for (int j = 0; j < n - k + 1; ++j) {
                    if (top[i + k - 1][j] >= k && top[i + k - 1][j + k - 1] >= k
                            && left[i][j + k - 1] >= k && left[i + k - 1][j + k - 1] >= k) {
                        return k * k;
                    }
                }
            }
        }
        return 0;
    }
}
