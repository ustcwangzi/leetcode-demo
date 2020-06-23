package com.wz.lists;

/**
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
 * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
 *
 * Example 1:
 * Input:
 * matrix = [
 *   [1,2,3,4],
 *   [5,1,2,3],
 *   [9,5,1,2]
 * ]
 * Output: True
 * Explanation:
 * In the above grid, the diagonals are:
 * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 * In each diagonal all elements are the same, so the answer is True.
 *
 * Example 2:
 * Input:
 * matrix = [
 *   [1,2],
 *   [2,2]
 * ]
 * Output: False
 * Explanation:
 * The diagonal "[1, 2]" has different elements.
 */
public class ToeplitzMatrix {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 1, 2, 3},
                {9, 5, 1, 2}
        };
        System.out.println(isToeplitzMatrix(matrix));

        matrix = new int[][]{
                {1, 2},
                {2, 2}
        };
        System.out.println(isToeplitzMatrix(matrix));
    }

    /**
     * 左上到右下的位置关系为 (i, j) -> (i+1, j+1)
     * 先从第一行开始遍历，对于每个位置(i, j)，逐个向(i+1, j+1)比较，这样右上侧的元素全部比较完毕
     * 再从第一列开始遍历，对于每个位置(i, j)，逐个向(i+1, j+1)比较，这样左下侧的元素全部比较完毕
     */
    public static boolean isToeplitzMatrix(int[][] matrix) {
        // 比较右上侧的元素
        for (int i = 0, j = 0; j < matrix[0].length - 1; j++) {
            int x = i + 1, y = j + 1;
            while (x < matrix.length && y < matrix[0].length) {
                if (matrix[x++][y++] != matrix[i][j]) {
                    return false;
                }
            }
        }

        // 比较左下侧的元素
        for (int i = 1, j = 0; i < matrix.length - 1; i++) {
            int x = i + 1, y = j + 1;
            while (x < matrix.length && y < matrix[0].length) {
                if (matrix[x++][y++] != matrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
