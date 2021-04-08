package com.wz.array;

/**
 * Given a square matrix mat, return the sum of the matrix diagonals.
 * Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.
 *
 * Example 1:
 * Input: mat = [[1,2,3],
 *               [4,5,6],
 *               [7,8,9]]
 * Output: 25
 * Explanation: Diagonals sum: 1 + 5 + 9 + 3 + 7 = 25
 * Notice that element mat[1][1] = 5 is counted only once.
 *
 * Example 2:
 * Input: mat = [[1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1],
 *               [1,1,1,1]]
 * Output: 8
 *
 * Constraints:
 * 1. n == mat.length == mat[i].length
 * 2. 1 <= n <= 100
 * 3. 1 <= mat[i][j] <= 100
 */
public class MatrixDiagonalSum {
    public static void main(String[] args) {
        System.out.println(diagonalSum(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
        }));

        System.out.println(diagonalSum(new int[][]{
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        }));
    }

    /**
     * 对角线元素之和
     * 将所有对角线元素相加，如果长度为奇数说明最中间的元素加了两次，需要减去
     */
    public static int diagonalSum(int[][] mat) {
        int sum = 0, n = mat.length;
        for (int i = 0; i < n; i++) {
            sum += mat[i][i];
            sum += mat[i][n - 1 - i];
        }

        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }

        return sum;
    }
}
