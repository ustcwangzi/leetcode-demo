package com.wz.lists;

import java.util.Arrays;

/**
 * In MATLAB, there is a very useful function called 'reshape',
 * which can reshape a matrix into a new one with different size but keep its original data.
 * You're given a matrix represented by a two-dimensional array,
 * and two positive integers r and c representing the row number and column number of the wanted reshaped matrix, respectively.
 * The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as they were.
 * If the 'reshape' operation with given parameters is possible and legal,
 * output the new reshaped matrix; Otherwise, output the original matrix.
 *
 * Example 1:
 * Input:
 * nums =
 * [[1,2],
 *  [3,4]]
 * r = 1, c = 4
 * Output:
 * [[1,2,3,4]]
 * Explanation:
 * The row-traversing of nums is [1,2,3,4]. The new reshaped matrix is a 1 * 4 matrix, fill it row by row by using the previous list.
 *
 * Example 2:
 * Input:
 * nums =
 * [[1,2],
 *  [3,4]]
 * r = 2, c = 4
 * Output:
 * [[1,2],
 *  [3,4]]
 * Explanation:
 * There is no way to reshape a 2 * 2 matrix to a 2 * 4 matrix. So output the original matrix.
 */
public class ReshapeMatrix {
    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 2},
                {3, 4}
        };
        int[][] result = matrixReshape(nums, 1, 4);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }

        result = matrixReshape(nums, 2, 4);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 遍历原矩阵，然后将元素依次填充到新矩阵中
     */
    public static int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) {
            return nums;
        }

        int[][] result = new int[r][c];
        // 新矩阵的行和列
        int x = 0, y = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 列到达边界，进行换行
                if (y == c) {
                    x++;
                    y = 0;
                }
                result[x][y++] = nums[i][j];
            }
        }

        return result;
    }
}
