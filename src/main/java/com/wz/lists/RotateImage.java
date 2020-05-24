package com.wz.lists;

/**
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Note:
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * <p>
 * Example 1:
 * Given input matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * rotate the input matrix in-place such that it becomes:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        printMatrix(matrix);
        rotate(matrix);
        printMatrix(matrix);

        matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        printMatrix(matrix);
        rotate(matrix);
        printMatrix(matrix);
    }

    /**
     * 每次对一圈的元素进行替换，每次替换四个关联的位置
     * 如3*3的矩阵
     * (0,0)替换为(2,0)、(0,2)替换为(0,0)、(2,2)替换为(0,2)、(2,0)替换为(2,2)
     * (1,2)替换为(0,1)、(2,1)替换为(1,2)、(1,0)替换为(2,1)、(0,1)替换为(1,0)
     * 通过观察可以得到规律：(i,j)替换为(n-1-j,i)，利用该规律依次对元素进行替换即可
     * 遍历的时候令i为旋转的圈数，j为遍历的序号。使得j=i，j增大到n-1-i，然后根据四个点的序号关联完成替换
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = tmp;
            }
        }
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] array : matrix) {
            for (int num : array) {
                System.out.print(String.format("%3s", num));
            }
            System.out.println();
        }
        System.out.println();
    }
}
