package com.wz.array;

/**
 * Given a rows x cols matrix mat, where mat[i][j] is either 0 or 1, return the number of special positions in mat.
 * A position (i,j) is called special if mat[i][j] == 1 and all other elements in row i and column j are 0 (rows and columns are 0-indexed).
 *
 * Example 1:
 * Input: mat = [[1,0,0],
 *               [0,0,1],
 *               [1,0,0]]
 * Output: 1
 * Explanation: (1,2) is a special position because mat[1][2] == 1 and all other elements in row 1 and column 2 are 0.
 *
 * Example 2:
 * Input: mat = [[1,0,0],
 *               [0,1,0],
 *               [0,0,1]]
 * Output: 3
 * Explanation: (0,0), (1,1) and (2,2) are special positions.
 *
 * Constraints:
 * 1. rows == mat.length
 * 2. cols == mat[i].length
 * 3. 1 <= rows, cols <= 100
 * 4. mat[i][j] is 0 or 1.
 */
public class SpecialPositionsInBinaryMatrix {
    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1, 0, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        System.out.println(numSpecial(mat));
    }

    /**
     * 使用 row[] 和 col[] 分别记录矩阵每一行、每一列 1 的个数
     * 然后遍历矩阵，如果当前值为 1，并且对应的当前行、当前列 1 的个数也是 1，就说明满足条件
     */
    public static int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] row = new int[m], col = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i] += mat[i][j];
                col[j] += mat[i][j];
            }
        }

        int result = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && row[i] == 1 && col[j] == 1) {
                    result++;
                }
            }
        }
        return result;
    }
}
