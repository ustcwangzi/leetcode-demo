package com.wz.other;

/**
 * Given two n x n binary matrices mat and target, return true if it is possible to make mat equal to target by rotating mat in 90-degree increments, or false otherwise.
 *
 * Example 1:
 * @see ../../../../resource/DetermineWhetherMatrixCanBeObtainedByRotation1.jpg
 * Input: mat = [[0,1],[1,0]], target = [[1,0],[0,1]]
 * Output: true
 * Explanation: We can rotate mat 90 degrees clockwise to make mat equal target.
 *
 * Example 2:
 * @see ../../../../resource/DetermineWhetherMatrixCanBeObtainedByRotation2.jpg
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]], target = [[1,1,1],[0,1,0],[0,0,0]]
 * Output: true
 * Explanation: We can rotate mat 90 degrees clockwise two times to make mat equal target.
 *
 * Constraints:
 * 1. n == mat.length == target.length
 * 2. n == mat[i].length == target[i].length
 * 3. 1 <= n <= 10
 * 4. mat[i][j] and target[i][j] are either 0 or 1.
 */
public class DetermineWhetherMatrixCanBeObtainedByRotation {
    public static void main(String[] args) {

    }

    /**
     * 对原数组旋转90度之后和 target 进行比较，相等则直接返回 true，最多旋转 3 次
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        if (checkEqual(mat, target)) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            mat = rotation(mat);
            if (checkEqual(mat, target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 旋转 90 度
     * 先将数组整体转置，然后逐行反转
     */
    private static int[][] rotation(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = result[i][j];
                result[i][j] = result[i][n - 1 - j];
                result[i][n - 1 - j] = tmp;
            }
        }
        return result;
    }

    public boolean checkEqual(int[][] matrix, int[][] target) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
