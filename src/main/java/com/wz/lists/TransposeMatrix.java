package com.wz.lists;

import java.util.Arrays;

/**
 * Given a matrix A, return the transpose of A.
 * The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.
 *
 * Example 1:
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[1,4,7],[2,5,8],[3,6,9]]
 *
 * Example 2:
 * Input: [[1,2,3],[4,5,6]]
 * Output: [[1,4],[2,5],[3,6]]
 */
public class TransposeMatrix {
    public static void main(String[] args) {
        int[][] A = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        int[][] result = transpose(A);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }

        A = new int[][]{
                {1, 2, 3}, {4, 5, 6}
        };
        result = transpose(A);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 直接进行行列互换即可
     */
    public static int[][] transpose(int[][] A) {
        int m = A.length, n = A[0].length;
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = A[j][i];
            }
        }

        return result;
    }
}
