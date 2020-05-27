package com.wz.lists;

import java.util.Arrays;

/**
 * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * Example:
 * Input: 3
 * Output:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 */
public class SpiralMatrixII {
    public static void main(String[] args) {
        int[][] result = generateMatrix(3);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));

        }
    }

    /**
     * 思路与{@link SpiralMatrix}类似
     */
    public static int[][] generateMatrix(int n) {
        if (n == 1) {
            return new int[][]{{1}};
        }

        int[][] result = new int[n][n];
        int rowStart = 0, rowEnd = n - 1, colStart = 0, colEnd = n - 1;
        int value = 1;
        while (true) {
            // 从左到右，行不变
            for (int i = colStart; i <= colEnd; i++) {
                result[rowStart][i] = value++;
            }
            if (++rowStart > rowEnd) {
                break;
            }

            // 从上到下，列不变
            for (int i = rowStart; i <= rowEnd; i++) {
                result[i][colEnd] = value++;
            }
            if (--colEnd < colStart) {
                break;
            }

            // 从右到左，行不变
            for (int i = colEnd; i >= colStart; i--) {
                result[rowEnd][i] = value++;
            }
            if (--rowEnd < rowStart) {
                break;
            }

            // 从下到上，列不变
            for (int i = rowEnd; i >= rowStart; i--) {
                result[i][colStart] = value++;
            }
            if (++colStart > colEnd) {
                break;
            }
        }
        return result;
    }
}
