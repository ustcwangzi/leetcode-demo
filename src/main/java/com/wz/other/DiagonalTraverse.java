package com.wz.other;

import java.util.Arrays;

/**
 * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
 *
 * Example 1:
 * @link ../../../../resource/DiagonalTraverse.jpg
 * Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,4,7,5,3,6,8,9]
 *
 * Example 2:
 * Input: mat = [[1,2],[3,4]]
 * Output: [1,2,3,4]
 *
 * Constraints:
 * 1. m == mat.length
 * 2. n == mat[i].length
 * 3. 1 <= m, n <= 10^4
 * 4. 1 <= m * n <= 10^4
 * 5. -10^5 <= mat[i][j] <= 10^5
 */
public class DiagonalTraverse {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }

    /**
     * 向右上移动坐标加上 [-1,1]，向左下移动坐标加上 [1,-1]，向右上和左下两个对角线方向遍历的时候都会有越界的可能
     * 如果 col 超过了 n-1 的范围，那么 col 重置为 n-1，并且 row 自增 2，然后改变遍历的方向，例如遍历到 [3,0] 时需要跳到 [2,2]
     * 如果 row 超过了 m-1 的范围，那么 row 重置为 m-1，并且 col 自增 2，然后改变遍历的方向
     * 如果 row 小于 0，那么 row 重置0，然后改变遍历的方向
     * 如果 col 小于 0，那么 col 重置0，然后改变遍历的方向
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] result = new int[m * n];
        int row = 0, col = 0, flag = 0;
        int[][] dirs = new int[][]{{-1, 1}, {1, -1}};
        for (int i = 0; i < m * n; i++) {
            result[i] = mat[row][col];
            row += dirs[flag][0];
            col += dirs[flag][1];
            if (row > m - 1) {
                row = m - 1;
                col += 2;
                flag = 1 - flag;
            }
            if (col > n - 1) {
                col = n - 1;
                row += 2;
                flag = 1 - flag;
            }
            if (row < 0) {
                row = 0;
                flag = 1 - flag;
            }
            if (col < 0) {
                col = 0;
                flag = 1 - flag;
            }
        }
        return result;
    }
}
