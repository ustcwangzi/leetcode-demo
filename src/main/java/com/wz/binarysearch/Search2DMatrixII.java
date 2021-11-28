package com.wz.binarysearch;

/**
 * Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
 * 1. Integers in each row are sorted in ascending from left to right.
 * 2. Integers in each column are sorted in ascending from top to bottom.
 *
 *
 * Example 1:
 * @link ../../../../resource/Search2DMatrixII.jpg
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
 * Output: true
 *
 * Example 2:
 * @link ../../../../resource/Search2DMatrixII.jpg
 * Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
 * Output: false
 *
 * Constraints:
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= n, m <= 300
 * 4. -10^9 <= matix[i][j] <= 10^9
 * 5. All the integers in each row are sorted in ascending order.
 * 6. All the integers in each column are sorted in ascending order.
 * 7. -10^9 <= target <= 10^9
 */
public class Search2DMatrixII {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println(searchMatrix(matrix, 5));
        System.out.println(searchMatrix(matrix, 20));
    }

    /**
     * 观察 matrix 可以发现：右上角的元素是一行中最大的，同时又是一列中最小的，比它大的元素一定在下面，比它小的元素一定在左边
     * 可以利用这个性质，从右上角开始，逐步往左、往下进行搜索，当前元素大于 target，则向左查询，小于 target，则向下查询
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
