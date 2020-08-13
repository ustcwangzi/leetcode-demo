package com.wz.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * <p>
 * Example 1:
 * Input:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * Output: [1,2,3,6,9,8,7,4,5]
 * <p>
 * Example 2:
 * Input:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(spiralOrder(matrix));
        matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        System.out.println(spiralOrder(matrix));
    }

    /**
     * 记录上下上做四个边界位置，然后按照左到右、上到下、右到左、下到上的顺序循环，注意每一步进行边界的检查
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }

        // 上下左右四个边界
        int rowStart = 0, rowEnd = matrix.length - 1, colStart = 0, colEnd = matrix[0].length - 1;
        while (true) {
            // 从左到右，行不变
            for (int i = colStart; i <= colEnd; i++) {
                result.add(matrix[rowStart][i]);
            }
            if (++rowStart > rowEnd) {
                break;
            }

            // 从上到下，列不变
            for (int i = rowStart; i <= rowEnd; i++) {
                result.add(matrix[i][colEnd]);
            }
            if (--colEnd < colStart) {
                break;
            }

            // 从右到左，行不变
            for (int i = colEnd; i >= colStart; i--) {
                result.add(matrix[rowEnd][i]);
            }
            if (--rowEnd < rowStart) {
                break;
            }

            // 从下到上，列不变
            for (int i = rowEnd; i >= rowStart; i--) {
                result.add(matrix[i][colStart]);
            }
            if (++colStart > colEnd) {
                break;
            }
        }

        return result;
    }
}
